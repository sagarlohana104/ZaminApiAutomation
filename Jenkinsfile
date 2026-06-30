// Declarative Jenkins Pipeline
pipeline {

    // Run the pipeline on any available Jenkins agent
    agent any

    stages {

        // Stage 1: Fetch the latest source code from the GitHub repository
        stage('Checkout') {
            steps {
                // Clone the project repository
                git 'https://github.com/sagarlohana104/ZaminApiAutomation.git'
            }
        }

        // Stage 2: Build the project and execute test cases
        stage('Build & Run Tests') {
            steps {
                // Clean previous build artifacts and run Maven tests
                bat 'mvn clean test'
            }
        }
    }
}
