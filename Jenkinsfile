pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'softumxpartan/hulkstore:latest'
        DOCKER_CREDENTIALS_ID = 'Beg54inext#'
    }

    stages {
        stage('Checkout') {
            steps {
                // Chequea el código fuente desde el repositorio
                checkout scm
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', "${DOCKER_CREDENTIALS_ID}") {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }
    }

//     post {
//         always {
//             // Limpia las imágenes de Docker locales para liberar espacio
//             sh 'docker rmi ${DOCKER_IMAGE}:${env.BUILD_ID} || true'
//             sh 'docker rmi ${DOCKER_IMAGE}:latest || true'
//         }
//     }
}