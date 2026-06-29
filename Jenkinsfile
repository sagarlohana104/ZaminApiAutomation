pipeline {
    agent any

    stages {
        stage('Build & Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/cucumber-reports/**/*', allowEmptyArchive: true
        }
    }
}
