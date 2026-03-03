pipeline {
    agent any

    tools {
        // 这里的名称必须和 Jenkins "全局工具配置" (Global Tool Configuration) 中设置的一致
        maven 'maven3'
        jdk 'jdk8'
    }

    parameters {
        choice(name: 'SERVICE_NAME',
               choices: [
                   'ruoyi-gateway',
                   'ruoyi-auth',
                   'ruoyi-modules-system', // 注意：若依微服务版系统模块通常叫这个
                   'ruoyi-modules-job',
                   'ruoyi-modules-file',
                   'ruoyi-modules-gen',
                   'ruoyi-visual-monitor'
               ],
               description: '选择要部署的模块')
    }

    environment {
        // 项目部署的根目录 (请确保 Jenkins 用户有权限写入此目录)
        // 建议先在服务器执行：mkdir -p /data/projects/ruoyi-cloud && chmod 777 /data/projects/ruoyi-cloud
        DEPLOY_ROOT = '/data/projects/ruoyi-cloud'

        // Gitee 凭证ID (在 Jenkins 凭证管理里添加)
        GIT_CREDENTIAL_ID = 'your-gitee-credentials-id'
        // Gitee 仓库地址
        GIT_URL = 'https://gitee.com/your-group/your-repo.git'
    }

    stages {
        stage('初始化配置') {
            steps {
                script {
                    // 初始化通用变量
                    def selectedService = params.SERVICE_NAME

                    switch(selectedService) {
                        case 'ruoyi-gateway':
                            env.JAR_PATH = "ruoyi-gateway/target/ruoyi-gateway.jar"
                            env.JAR_NAME = "ruoyi-gateway.jar"
                            env.SERVER_PORT = "8888" // ⚠️强烈建议修改，避免和Jenkins 8080冲突
                            break
                        case 'ruoyi-auth':
                            env.JAR_PATH = "ruoyi-auth/target/ruoyi-auth.jar"
                            env.JAR_NAME = "ruoyi-auth.jar"
                            env.SERVER_PORT = "9200"
                            break
                        case 'ruoyi-modules-system':
                            env.JAR_PATH = "ruoyi-modules/ruoyi-system/target/ruoyi-modules-system.jar"
                            env.JAR_NAME = "ruoyi-modules-system.jar"
                            env.SERVER_PORT = "9201"
                            break
                        case 'ruoyi-modules-job':
                            env.JAR_PATH = "ruoyi-modules/ruoyi-job/target/ruoyi-modules-job.jar"
                            env.JAR_NAME = "ruoyi-modules-job.jar"
                            env.SERVER_PORT = "9203"
                            break
                        case 'ruoyi-modules-file':
                            env.JAR_PATH = "ruoyi-modules/ruoyi-file/target/ruoyi-modules-file.jar"
                            env.JAR_NAME = "ruoyi-modules-file.jar"
                            env.SERVER_PORT = "9300"
                            break
                         case 'ruoyi-visual-monitor':
                            env.JAR_PATH = "ruoyi-visual/ruoyi-visual-monitor/target/ruoyi-visual-monitor.jar"
                            env.JAR_NAME = "ruoyi-visual-monitor.jar"
                            env.SERVER_PORT = "9100"
                            break
                        default:
                            error "未知的服务模块: ${selectedService}"
                    }

                    echo "----------------------------------------------------"
                    echo "构建模块: ${params.SERVICE_NAME}"
                    echo "目标端口: ${env.SERVER_PORT}"
                    echo "Jar包路径: ${env.JAR_PATH}"
                    echo "----------------------------------------------------"
                }
            }
        }

        stage('拉取代码') {
            steps {
                checkout([$class: 'GitSCM',
                          branches: [[name: '*/master']], // 默认拉取 master 分支
                          userRemoteConfigs: [[credentialsId: "${env.GIT_CREDENTIAL_ID}", url: "${env.GIT_URL}"]]])
            }
        }

        stage('Maven构建') {
            steps {
                echo ">>> 开始编译打包..."
                // -pl 指定单独构建某个模块及其依赖，比构建整个项目快
                // -am also make dependents
                sh "mvn clean package -Dmaven.test.skip=true"
            }
        }

        stage('部署启动') {
            steps {
                script {
                    // 定义部署的具体目录
                    def targetDir = "${env.DEPLOY_ROOT}/${params.SERVICE_NAME}"

                    // 使用 JENKINS_NODE_COOKIE 确保脚本退出后，后台进程(java)不被 Jenkins 杀掉
                    withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                        sh """
                            echo ">>> [1/4] 准备部署目录: ${targetDir}"
                            mkdir -p ${targetDir}
                            mkdir -p ${targetDir}/logs

                            echo ">>> [2/4] 检查端口 ${env.SERVER_PORT} 占用..."
                            # 获取占用该端口的 PID
                            pid=\$(netstat -nlp | grep ":${env.SERVER_PORT}" | awk '{print \$7}' | awk -F"/" '{ print \$1 }')

                            if [ -n "\$pid" ]; then
                                echo ">>> 发现旧进程 PID=\$pid，正在停止..."
                                kill -9 \$pid
                                sleep 2
                            else
                                echo ">>> 端口未被占用，无需停止。"
                            fi

                            echo ">>> [3/4] 复制 Jar 包..."
                            if [ ! -f "${env.JAR_PATH}" ]; then
                                echo "❌ 错误：Jar包未找到: ${env.JAR_PATH}"
                                exit 1
                            fi

                            # 删除旧包，复制新包
                            rm -f ${targetDir}/${env.JAR_NAME}
                            cp ${env.JAR_PATH} ${targetDir}/${env.JAR_NAME}

                            echo ">>> [4/4] 启动服务..."
                            cd ${targetDir}

                            # 启动命令 (内存限制设小一点，防止阿里云卡死)
                            nohup java -jar -Xms128m -Xmx256m ${env.JAR_NAME} \
                                --server.port=${env.SERVER_PORT} \
                                --spring.profiles.active=prod \
                                > logs/app.log 2>&1 &

                            echo "✅ 服务已启动，正在后台运行。"
                            echo "✅ 请使用命令查看日志: tail -f ${targetDir}/logs/app.log"
                        """
                    }
                }
            }
        }
    }
}