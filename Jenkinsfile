pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}