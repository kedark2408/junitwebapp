pipeline{
    agent any
    stages {
        stage('Build Web project') {
            steps {
                echo 'Building Maven Web project'
                    sh '''
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
        stage('Deploy to Tomcat') {
            steps {
                build job: 'DeployWebprojectToTomcat', parameters: [string(name: 'MASTER_JOB', value: 'BuildWebProject')]
            }
        }
    }
}
