pipeline{
      agent{label 'Java'}
         stages{
		    stage('checkout'){
		      steps{
		        git "https://github.com/Madhav987/boxfuse-sample-java-war-hello.git"
			}
				
		}
          stage('test'){
		    steps{
			   echo ' unit testing by using juint'
			}
		}
		stage('building artifact'){
		 steps{
		   sh 'mvn clean package'
		 }
		 
		}
		stage('deploying in tomcat'){
		  steps{
		     sh 'sudo rsync -av /home/ec2-user/jenkins/workspace/Assignment_03/target/hello-1.0.war /home/ec2-user/tomcat/webapps/'
			  echo " successfully deployed "
			}
        }
    }
    
}	
