pipeline {
    agent any
	tools{
	      maven 'local-maven'
		 }
    environment {
        TOMCAT_USER = 'tomcat'
        TOMCAT_HOST = 'http://13.127.75.155:9050/'
        TOMCAT_PORT = '9050'
        TOMCAT_WEBAPP_DIR = '/opt/tomcat/webapps/'

    }
    stages {
        stage('Build') {
            steps {
                // Generate artifact (e.g., a WAR file)
                sh 'mvn clean package'
                archiveArtifacts artifacts: 'target/*.war', allowEmptyArchive: true
            }
        }
        stage('Deploy') {
            steps {
                // Use rsync to deploy the artifact to the Tomcat server
                sh """
                rsync -avz -e 'ssh -p ${TOMCAT_PORT}' target/*.war ${TOMCAT_USER}@${TOMCAT_HOST}:${TOMCAT_WEBAPP_DIR}
                """
            }
        }
    }
    post {
        success {
            echo 'Deployment completed successfully.'
        }
        failure {
            echo 'Deployment failed.'
        }
    }
}