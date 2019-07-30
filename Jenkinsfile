pipeline{
    agent any
    stages {
        stage('Build Web project') {
            steps {
                echo 'Building Maven Web project'
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
                }
            }
        }
        stage('Deploy to Staging') {
            steps {
                build job: 'DeployJunitWebAppToStaging', parameters: [string(name: 'MASTER_JOB', value: 'CICIDPipelineforJunitWebApp')]
            }
        }
        stage('Test the Webapp') {
            steps {
                echo 'Testing the webapp'
                sh '''
                export MAVEN_HOME=/opt/apache-maven-3.6.1
                export PATH=$PATH:$MAVEN_HOME/bin
                mvn test
                '''
            }
        }
        stage('Deploy to Production') {
            steps {
                build job: 'DeployJunitWebAppToProd', parameters: [string(name: 'MASTER_JOB', value: 'CICIDPipelineforJunitWebApp')]
            }
        }
    }
}
