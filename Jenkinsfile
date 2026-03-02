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
                            env.JAR_SOURCE = 'ruoyi-gateway/target/ruoyi-gateway-*.jar'
                            env.JAR_NAME = 'ruoyi-gateway.jar'
                            break

                        // 2. 认证中心
                        case 'ruoyi-auth':
                            env.APP_PORT = '9200'
                            env.JAR_SOURCE = 'ruoyi-auth/target/ruoyi-auth-*.jar'
                            env.JAR_NAME = 'ruoyi-auth.jar'
                            break

                        // 3. 系统模块 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-system':
                            env.APP_PORT = '9201'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-system/target/ruoyi-system-*.jar'
                            env.JAR_NAME = 'ruoyi-system.jar'
                            break

                        // 4. 定时任务 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-job':
                            env.APP_PORT = '9203'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-job/target/ruoyi-job-*.jar'
                            env.JAR_NAME = 'ruoyi-job.jar'
                            break

                        // 5. 文件服务 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-file':
                            env.APP_PORT = '9300'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-file/target/ruoyi-file-*.jar'
                            env.JAR_NAME = 'ruoyi-file.jar'
                            break

                        // 6. 代码生成 (注意路径在 ruoyi-modules 下)
                        case 'ruoyi-gen':
                            env.APP_PORT = '9202'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-gen/target/ruoyi-gen-*.jar'
                            env.JAR_NAME = 'ruoyi-gen.jar'
                            break

                        // 7. 监控中心 (注意路径在 ruoyi-visual 下)
                        case 'ruoyi-monitor':
                            env.APP_PORT = '9100'
                            // 注意：ruoyi-visual 下面的模块名可能叫 ruoyi-visual-monitor
                            env.JAR_SOURCE = 'ruoyi-visual/ruoyi-visual-monitor/target/ruoyi-visual-monitor-*.jar'
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
                // 在根目录执行 maven 打包，这样会自动安装 common 和 api 模块到本地仓库
                // 否则单独构建子模块会报错找不到依赖
                sh 'mvn clean package -DskipTests'
            }
        }

        // --- 第四阶段：部署 ---
        stage('Deploy') {
			steps {
				script {
					env.DEPLOY_DIR = "${BASE_DEPLOY_PATH}/${params.SERVICE_NAME}"

                    withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
						sh '''
                            echo ">>> 准备部署 ${SERVICE_NAME} ..."

                            # 1. 创建目录
                            mkdir -p ${DEPLOY_DIR}

                            # 2. 检查并停止旧进程
                            echo ">>> 检查端口 ${APP_PORT} ..."
                            pid=$(lsof -t -i:${APP_PORT}) || true
                            if [ -n "$pid" ]; then
                                echo ">>> 发现旧进程 PID: $pid，正在停止..."
                                kill -9 $pid
                            else
                                echo ">>> 端口 ${APP_PORT} 未被占用"
                            fi

                            # 3. 拷贝 Jar 包 (使用 cp 拷贝，覆盖旧文件)
                            # 注意：JAR_SOURCE 里包含了通配符 *，shell 会自动识别
                            echo ">>> 正在拷贝 Jar 包..."
                            rm -f ${DEPLOY_DIR}/${JAR_NAME}
                            cp ${JAR_SOURCE} ${DEPLOY_DIR}/${JAR_NAME}

                            # 4. 启动服务
                            cd ${DEPLOY_DIR}
                            echo ">>> 正在启动服务 (内存限制: ${JAVA_OPTS})..."

                            # 启动命令：后台运行 + 限制内存 + 指定端口 + 日志输出
                            nohup java ${JAVA_OPTS} -jar ${JAR_NAME} --server.port=${APP_PORT} > app.log 2>&1 &

                            echo ">>> ${SERVICE_NAME} 启动命令已发送，请稍后检查 app.log"
                        '''
                    }
                }
            }
        }
    }
}