pipeline {
    agent any
    stages {
        stage('Build Web project') {
            steps {
                sh '''
                export MAVEN_HOME=/opt/apache-maven-3.6.1
                export PATH=$PATH:$MAVEN_HOME/bin
                mvn validate
                mvn compile
                mvn -Dmaven.test.skip=true package
                '''
            }
            post {
                success {
                    archiveArtifacts '**/*.war'
                    sh 'echo "Deploying the Web App to Staging"'
                    build job: 'DeployWebAppToStaging', parameters: [string(name: 'PREVIOUS_JOB', value: 'CICIDPipelineWebProject')]
                }
            }
        }
        stage('Test and Deploy WebApp to Prod') {
            steps {
                sh 'echo "Deploying the Web App to Prod"'
                build job: 'TestAndDeployToProd', parameters: [string(name: 'MASTER_JOB', value: 'CICIDPipelineWebProject')]
            }
        }
    }
}
