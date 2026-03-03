pipeline {
	agent any
    parameters {
		choice(name: 'SERVICE_NAME',
               choices: [
                   'ruoyi-gateway',   // 网关 [8080]
                   'ruoyi-auth',      // 认证中心 [9200]
                   'ruoyi-system',    // 系统模块 [9201]
                   'ruoyi-job',       // 定时任务 [9203]
                   'ruoyi-file',      // 文件服务 [9300]
                   'ruoyi-gen',       // 代码生成 [9202]
                   'ruoyi-monitor'    // 监控中心 [9100]
               ],
               description: '请选择本次要部署的微服务模块')
    }
    environment {
        CREDENTIAL_ID = 'finance'
        GIT_URL = 'https://github.com/Ttopll/123Test.git'
        BASE_DEPLOY_PATH = '/home/jenkins/deploy/ruoyi-cloud'
        JAVA_OPTS = "-Xms128m -Xmx512m"
        // 新增：服务启动验证超时时间（秒）
        START_TIMEOUT = 10
    }

    // 新增：构建前检查依赖
    post {
        failure {
            echo "❌ 构建/部署失败！请检查以下内容："
            echo "1. Maven构建日志：是否有编译错误"
            echo "2. Jar包路径：${JAR_SOURCE} 是否存在"
            echo "3. 服务器依赖：是否安装lsof（执行 sudo yum install lsof -y）"
            echo "4. 端口占用：${APP_PORT} 是否被其他进程占用"
        }
        success {
            echo "✅ 部署成功！服务信息："
            echo "服务名称：${SERVICE_NAME}"
            echo "部署目录：${DEPLOY_DIR}"
            echo "日志路径：${DEPLOY_DIR}/app.log"
        }
    }

    stages {
        stage('Initialize') {
			steps {
				script {
                    switch(params.SERVICE_NAME) {
                        case 'ruoyi-gateway':
                            env.APP_PORT = '8080'
                            env.JAR_SOURCE = 'ruoyi-gateway/target/ruoyi-gateway.jar'
                            env.JAR_NAME = 'ruoyi-gateway.jar'
                            break
                        case 'ruoyi-auth':
                            env.APP_PORT = '9200'
                            env.JAR_SOURCE = 'ruoyi-auth/target/ruoyi-auth.jar'
                            env.JAR_NAME = 'ruoyi-auth.jar'
                            break
                        case 'ruoyi-system':
                            env.APP_PORT = '9201'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-system/target/ruoyi-system.jar'
                            env.JAR_NAME = 'ruoyi-system.jar'
                            break
                        case 'ruoyi-job':
                            env.APP_PORT = '9203'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-job/target/ruoyi-job.jar'
                            env.JAR_NAME = 'ruoyi-job.jar'
                            break
                        case 'ruoyi-file':
                            env.APP_PORT = '9300'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-file/target/ruoyi-file.jar'
                            env.JAR_NAME = 'ruoyi-file.jar'
                            break
                        case 'ruoyi-gen':
                            env.APP_PORT = '9202'
                            env.JAR_SOURCE = 'ruoyi-modules/ruoyi-gen/target/ruoyi-gen.jar'
                            env.JAR_NAME = 'ruoyi-gen.jar'
                            break
                        case 'ruoyi-monitor':
                            env.APP_PORT = '9100'
                            env.JAR_SOURCE = 'ruoyi-visual/ruoyi-visual-monitor/target/ruoyi-visual-monitor.jar'
                            // 修复：JAR_NAME和源文件保持一致，避免启动路径错误
                            env.JAR_NAME = 'ruoyi-visual-monitor.jar'
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

        // 新增：依赖检查阶段
        stage('Check Dependencies') {
            steps {
                echo ">>> 检查服务器依赖（lsof）..."
                sh '''
                    # 正确逻辑：先检查lsof是否存在
                    if command -v lsof &> /dev/null; then
                        echo "✅ lsof依赖检查完成"
                    else
                        echo "❌ lsof未安装，请手动执行：sudo yum install lsof -y"
                        exit 1
                    fi
                '''
            }
        }

        stage('Build') {
			steps {
				echo ">>> 正在构建项目 (这可能需要几分钟)..."
                // 新增：构建失败立即终止，输出详细日志
                sh 'mvn clean package -DskipTests || { echo "❌ Maven构建失败！"; exit 1; }'
                // 新增：验证Jar包是否生成，失败则终止
                sh """
                    if [ ! -f ${JAR_SOURCE} ]; then
                        echo "❌ Jar包未生成：${JAR_SOURCE}";
                        exit 1;
                    else
                        ls -l ${JAR_SOURCE};
                        echo "✅ Jar包生成成功"
                    fi
                """
            }
        }

        stage('Deploy') {
            steps {
                script {
                    env.DEPLOY_DIR = "${BASE_DEPLOY_PATH}/${params.SERVICE_NAME}"
                    withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                        sh '''
                            set -e
                            # 优化心跳函数：非阻塞，每0.1秒输出一次
                            keep_alive() {
                                local step_name=$1
                                local stop_flag="/tmp/keep_alive_${$}.stop"
                                # 后台持续输出心跳，直到检测到停止文件
                                (
                                    while [ ! -f "$stop_flag" ]; do
                                        echo "🫀 部署中（步骤：${step_name}）- $(date +%Y%m%d_%H%M%S_%3N)"
                                        sleep 0.1
                                    done
                                    rm -f "$stop_flag"
                                ) &
                                echo $!  # 返回后台进程PID
                            }

                            # 停止心跳函数
                            stop_keep_alive() {
                                local pid=$1
                                local stop_flag="/tmp/keep_alive_${pid}.stop"
                                touch "$stop_flag"
                                wait $pid 2>/dev/null || true
                            }

                            echo ">>> 准备部署 ${SERVICE_NAME} ... [步骤1/5]"
                            # 步骤1：创建部署目录（启动高频心跳）
                            HB_PID=$(keep_alive "创建部署目录")
                            echo ">>> 步骤1/5：创建部署目录 ${DEPLOY_DIR} ..."
                            mkdir -p ${DEPLOY_DIR}
                            echo ">>> 步骤1/5：目录创建完成 ✔️"
                            stop_keep_alive $HB_PID

                            # 步骤2：检查并停止旧进程（核心修复：用循环替代sleep 1）
                            HB_PID=$(keep_alive "停止旧进程")
                            echo ">>> 步骤2/5：检查端口 ${APP_PORT} 占用情况 ..."
                            pid=$(lsof -t -i:${APP_PORT}) || true
                            if [ -n "$pid" ]; then
                                echo ">>> 步骤2/5：发现旧进程 PID: $pid，正在停止..."
                                kill -9 $pid
                                echo ">>> 步骤2/5：旧进程 $pid 已发送终止信号 ✔️"
                                # 替代sleep 1：循环10次（每次0.1秒），每次都有心跳输出
                                for ((i=1; i<=10; i++)); do
                                    echo ">>> 步骤2/5：等待进程退出（第${i}/10次）..."
                                    sleep 0.1
                                done
                                echo ">>> 步骤2/5：等待1秒完成 ✔️"
                                echo ">>> 步骤2/5：旧进程已停止 ✔️"
                            else
                                echo ">>> 步骤2/5：端口 ${APP_PORT} 未被占用 ✔️"
                            fi
                            stop_keep_alive $HB_PID

                            # 步骤3：拷贝Jar包
                            HB_PID=$(keep_alive "拷贝Jar包")
                            echo ">>> 步骤3/5：检查Jar包 ${JAR_SOURCE} 是否存在 ..."
                            if [ ! -f ${JAR_SOURCE} ]; then
                                echo "❌ 步骤3/5：Jar包文件 ${JAR_SOURCE} 不存在！"
                                stop_keep_alive $HB_PID
                                exit 1
                            fi
                            echo ">>> 步骤3/5：删除旧Jar包 ${DEPLOY_DIR}/${JAR_NAME} ..."
                            rm -f ${DEPLOY_DIR}/${JAR_NAME}
                            echo ">>> 步骤3/5：拷贝新Jar包到部署目录 ..."
                            cp ${JAR_SOURCE} ${DEPLOY_DIR}/${JAR_NAME}
                            echo ">>> 步骤3/5：Jar包拷贝完成 ✔️"
                            stop_keep_alive $HB_PID

                            # 步骤4：启动服务
                            HB_PID=$(keep_alive "启动服务")
                            echo ">>> 步骤4/5：切换到部署目录 ${DEPLOY_DIR} ..."
                            cd ${DEPLOY_DIR}
                            echo ">>> 步骤4/5：启动服务（内存参数：${JAVA_OPTS}）..."
                            nohup setsid java ${JAVA_OPTS} -jar ${JAR_NAME} --server.port=${APP_PORT} > app.log 2>&1 &
                            echo ">>> 步骤4/5：启动命令已发送 ✔️"
                            stop_keep_alive $HB_PID

                            # 步骤5：验证启动（替代sleep 2：循环20次，每次0.1秒）
                            HB_PID=$(keep_alive "验证启动")
                            echo ">>> 步骤5/5：验证服务是否启动（超时${START_TIMEOUT}秒）..."
                            start_time=$(date +%s)
                            new_pid=""
                            while [ $(( $(date +%s) - start_time )) -lt ${START_TIMEOUT} ]; do
                                new_pid=$(lsof -t -i:${APP_PORT}) || true
                                if [ -n "$new_pid" ]; then
                                    break
                                fi
                                echo ">>> 步骤5/5：等待服务启动...（已等待$(( $(date +%s) - start_time ))秒）"
                                # 每次等待0.1秒，高频输出避免超时
                                sleep 0.1
                            done
                            stop_keep_alive $HB_PID

                            # 最终验证
                            if [ -n "$new_pid" ]; then
                                echo "✅ ${SERVICE_NAME} 部署成功！PID: $new_pid"
                                echo "✅ 日志查看命令：tail -f ${DEPLOY_DIR}/app.log"
                            else
                                echo "⚠️ ${SERVICE_NAME} 启动超时（${START_TIMEOUT}秒）！"
                                echo "⚠️ 请查看日志定位问题：cat ${DEPLOY_DIR}/app.log"
                            fi
                        '''
                    }
                }
            }
        }
    }
}