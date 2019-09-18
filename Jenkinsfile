pipeline {
    agent any
    stages {
        stage('Build Web App') {
            steps {
                sh '''
                mvn clean validate compile
                '''
            }
        }
        stage('Deploy To Staging') {
            steps {
                sh '''
                echo 'Skipping maven test'
                mvn -Dmaven.test.skip=true package
                '''
            }
            post {
                success {
                    archiveArtifacts 'target/*.war'
                    deploy adapters: [tomcat8(credentialsId: 'tomcat', path: '', url: 'http://localhost:8081')], contextPath: null, onFailure: false, war: '**/*.war'
                }
            }
        }
        stage('Test WebApp') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Deploy to Production') {
            steps {
                deploy adapters: [tomcat8(credentialsId: 'tomcat', path: '', url: 'http://34.87.107.19:8080')], contextPath: null, onFailure: false, war: '**/*.war'
            }
        }
    }
}
