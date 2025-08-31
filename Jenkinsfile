pipeline {
    agent any
    environment {
        AWS_ACCOUNT_ID = '676509179952'
        AWS_REGION = 'ap-south-1'
        ECR_REPO = "${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/<your-repo>.git'
            }
        }
        stage('Build & Test') {
            parallel {
                stage('Java App') {
                    steps {
                        dir('java-app') {
                            sh 'mvn clean package'
                        }
                    }
                }
                stage('Node App') {
                    steps {
                        dir('node-app') {
                            sh 'npm install && npm test'
                        }
                    }
                }
                stage('Python App') {
                    steps {
                        dir('python-app') {
                            sh 'pip install -r requirements.txt'
                        }
                    }
                }
            }
        }
        stage('Build Docker Images') {
            parallel {
                stage('Java App') {
                    steps {
                        dir('java-app') {
                            sh """
                            docker build -t $ECR_REPO/java-app:latest .
                            aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REPO
                            docker push $ECR_REPO/java-app:latest
                            """
                        }
                    }
                }
                stage('Node App') {
                    steps {
                        dir('node-app') {
                            sh """
                            docker build -t $ECR_REPO/node-app:latest .
                            aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REPO
                            docker push $ECR_REPO/node-app:latest
                            """
                        }
                    }
                }
                stage('Python App') {
                    steps {
                        dir('python-app') {
                            sh """
                            docker build -t $ECR_REPO/python-app:latest .
                            aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REPO
                            docker push $ECR_REPO/python-app:latest
                            """
                        }
                    }
                }
            }
        }
        stage('Deploy to EKS') {
            steps {
                dir('k8s') {
                    sh """
                    kubectl apply -f java-app-deployment.yaml
                    kubectl apply -f node-app-deployment.yaml
                    kubectl apply -f python-app-deployment.yaml
                    kubectl apply -f multi-app-ingress.yaml
                    """
                }
            }
        }
    }
}
