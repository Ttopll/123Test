pipeline {
	agent any

    // 1. 定义构建参数
    parameters {
		choice(name: 'SERVICE_NAME',
               // 这里列出了若依所有的可运行服务
               choices: [
                   'ruoyi-gateway',   // 网关 [8080]
                   'ruoyi-auth',      // 认证中心 [9200]
                   'ruoyi-system',    // 系统模块 [9201] (在 ruoyi-modules 下)
                   'ruoyi-job',       // 定时任务 [9203] (在 ruoyi-modules 下)
                   'ruoyi-file',      // 文件服务 [9300] (在 ruoyi-modules 下)
                   'ruoyi-gen',       // 代码生成 [9202] (在 ruoyi-modules 下)
                   'ruoyi-monitor'    // 监控中心 [9100] (在 ruoyi-visual 下)
               ],
               description: '请选择本次要部署的微服务模块')
    }

    environment {
		// Git 配置 (请修改这里)
        CREDENTIAL_ID = 'finance'  //  Gitee 凭据ID
        GIT_URL = 'https://github.com/Ttopll/123Test.git' // 仓库地址

        // 部署根目录
        BASE_DEPLOY_PATH = '/home/jenkins/deploy/ruoyi-cloud'

        // JVM 内存限制 (关键！防止阿里云服务器卡死)
        // -Xms128m: 最小内存, -Xmx512m: 最大内存
        JAVA_OPTS = "-Xms128m -Xmx512m"
    }

    stages {
		// --- 第一阶段：初始化配置 ---
        stage('Initialize') {
			steps {
				script {
					// 根据选择的服务，自动映射 端口 和 Jar包路径
                    // 注意：若依的 Jar 包版本号在 pom.xml 里是 3.6.7
                    // 这里我们用 *.jar 模糊匹配，以后你升级版本也不用改脚本
                    switch(params.SERVICE_NAME) {

					// 1. 网关模块
                        case 'ruoyi-gateway':
                            env.APP_PORT = '8080'
                            env.JAR_SOURCE = 'ruoyi-gateway/target/ruoyi-gateway.jar'
                            env.JAR_NAME = 'ruoyi-gateway.jar'
                            break

                        // 2. 认证中心
                        case 'ruoyi-auth':
                            env.APP_PORT = '9200'
                            env.JAR_SOURCE = 'ruoyi-auth/target/ruoyi-auth.jar'
                            env.JAR_NAME = 'ruoyi-auth.jar'
                            break

                        // 3. 系统模块 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-system':
                            env.APP_PORT = '9201'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-system/target/ruoyi-system.jar'
                            env.JAR_NAME = 'ruoyi-system.jar'
                            break

                        // 4. 定时任务 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-job':
                            env.APP_PORT = '9203'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-job/target/ruoyi-job.jar'
                            env.JAR_NAME = 'ruoyi-job.jar'
                            break

                        // 5. 文件服务 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-file':
                            env.APP_PORT = '9300'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-file/target/ruoyi-file.jar'
                            env.JAR_NAME = 'ruoyi-file.jar'
                            break

                        // 6. 代码生成 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-gen':
                            env.APP_PORT = '9202'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-gen/target/ruoyi-gen.jar'
                            env.JAR_NAME = 'ruoyi-gen.jar'
                            break

                        // 7. 监控中心 (注意路径在 ruoyi-visual 下)
                        case 'ruoyi-monitor':
                            env.APP_PORT = '9100'
                            // 注意：ruoyi-visual 下面的模块名可能叫 ruoyi-visual-monitor
                            env.JAR_SOURCE = 'ruoyi-visual/ruoyi-visual-monitor/target/ruoyi-visual-monitor.jar'
                            env.JAR_NAME = 'ruoyi-monitor.jar'
                            break

                        default:
                            error "未知的服务: ${params.SERVICE_NAME}"
                    }

                    echo "------------------------------------------------"
                    echo ">>> 本次构建服务: ${params.SERVICE_NAME}"
                    echo ">>> 服务端口: ${env.APP_PORT}"
                    echo ">>> Jar包源路径: ${env.JAR_SOURCE}"
                    echo "------------------------------------------------"
                }
            }
        }


        // --- 第三阶段：构建项目 ---
        stage('Build') {
			steps {
				echo ">>> 正在构建项目 (这可能需要几分钟)..."
                sh 'mvn clean package -DskipTests'
                sh "ls -l ${JAR_SOURCE} || echo '⚠️ Jar包路径不存在，请检查路径配置'"
            }
        }

        // --- 第四阶段：部署 ---
        stage('Deploy') {
			steps {
				script {
					env.DEPLOY_DIR = "${BASE_DEPLOY_PATH}/${params.SERVICE_NAME}"
                    // 核心修改1：添加超时参数，关闭心跳检测
                    withEnv(['JENKINS_NODE_COOKIE=dontKillMe', "${DURABLE_TASK_TIMEOUT}"]) {
						sh '''
                            set -e  # 开启严格模式，任意命令失败立即退出
                            echo ">>> 准备部署 ${SERVICE_NAME} ... [步骤1/5]"
                            # 1. 创建部署目录（添加输出）
                            echo ">>> 步骤1/5：创建部署目录 ${DEPLOY_DIR} ..."
                            mkdir -p ${DEPLOY_DIR}
                            echo ">>> 步骤1/5：目录创建完成"

                            # 2. 检查并停止旧进程（添加输出）
                            echo ">>> 步骤2/5：检查端口 ${APP_PORT} 占用情况 ..."
                            pid=$(lsof -t -i:${APP_PORT}) || true
                            if [ -n "$pid" ]; then
                                echo ">>> 步骤2/5：发现旧进程 PID: $pid，正在停止..."
                                kill -9 $pid
                                sleep 1  # 等待进程退出
                                echo ">>> 步骤2/5：旧进程已停止"
                            else
                                echo ">>> 步骤2/5：端口 ${APP_PORT} 未被占用"
                            fi

                            # 3. 拷贝Jar包（添加输出）
                            echo ">>> 步骤3/5：检查Jar包 ${JAR_SOURCE} 是否存在 ..."
                            if [ ! -f ${JAR_SOURCE} ]; then
                                echo "❌ 步骤3/5：Jar包文件 ${JAR_SOURCE} 不存在！"
                                exit 1
                            fi
                            echo ">>> 步骤3/5：删除旧Jar包 ${DEPLOY_DIR}/${JAR_NAME} ..."
                            rm -f ${DEPLOY_DIR}/${JAR_NAME}
                            echo ">>> 步骤3/5：拷贝新Jar包到部署目录 ..."
                            cp ${JAR_SOURCE} ${DEPLOY_DIR}/${JAR_NAME}
                            echo ">>> 步骤3/5：Jar包拷贝完成"

                            # 4. 启动服务（核心修改2：优化nohup，脱离Jenkins进程树）
                            echo ">>> 步骤4/5：切换到部署目录 ${DEPLOY_DIR} ..."
                            cd ${DEPLOY_DIR}
                            echo ">>> 步骤4/5：启动服务（内存参数：${JAVA_OPTS}）..."
                            # 用nohup + setsid 彻底脱离Jenkins进程树，避免被误杀
                            nohup setsid java ${JAVA_OPTS} -jar ${JAR_NAME} --server.port=${APP_PORT} > app.log 2>&1 &
                            sleep 2  # 等待服务启动（短延时）
                            echo ">>> 步骤4/5：启动命令已发送"

                            # 5. 验证启动（添加输出，完成心跳）
                            echo ">>> 步骤5/5：验证服务是否启动 ..."
                            new_pid=$(lsof -t -i:${APP_PORT}) || true
                            if [ -n "$new_pid" ]; then
                                echo "✅ ${SERVICE_NAME} 部署成功！PID: $new_pid"
                                echo "✅ 日志查看命令：tail -f ${DEPLOY_DIR}/app.log"
                            else
                                echo "⚠️ ${SERVICE_NAME} 启动命令已执行，但端口 ${APP_PORT} 未检测到进程！"
                                echo "⚠️ 请查看日志：cat ${DEPLOY_DIR}/app.log"
                            fi
                        '''
                    }
                }
            }
        }
    }
}