// pipeline {
//     environment {
//         IMAGEN = "softumxpartan/hulkstore"
//         USUARIO = 'USER_DOCKERHUB'
//     }
//     agent any
//     stages {
//         stage('Clone') {
//             steps {
//                 git branch: "master", url: 'https://github.com/mayandor/hulkStoreTest.git'
//             }
//         }
pipeline {
    agent {
        docker {
            image 'maven:3.8.6-openjdk-11'
        }
    }
    environment {
        IMAGEN = "softumxpartan/hulkstore"
        USUARIO = 'USER_DOCKERHUB'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                script {
                    newApp = docker.build "$IMAGEN:$BUILD_NUMBER"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    docker.withRegistry( '', USUARIO ) {
                        newApp.push()
                    }
                }
            }
        }
        stage('Clean Up') {
            steps {
                sh "docker rmi $IMAGEN:$BUILD_NUMBER"
                }
        }
    }
}