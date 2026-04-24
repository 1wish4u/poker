pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21'
        }
    }

    stages {
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}