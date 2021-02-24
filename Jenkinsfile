pipeline {
    agent any

    stages {
        stage('Build Maven') {
            steps {
            // Run Maven on a Unix agent.
            sh "mvn clean install"
            }
        }
        stage('Build Docker') {
            steps {
            // Build the docker image
            sh "docker build -t blankpage-backend ."
            sh "docker stop blankpage-backend"
            sh "docker rm blankpage-backend"
            sh "docker run --name blankpage-backend -d -p 2222:2222 blankpage-backend"
            }
        }
   }
}