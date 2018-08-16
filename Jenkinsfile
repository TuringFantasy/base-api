pipeline {
	agent any
	environment {
		TEST = "None"
		JENKINS_EC2_SSH = credentials('jenkins-ssh')
		AWS_ACCESS_KEY_ID = credentials('access-key-cli')
		AWS_SECRET_ACCESS_KEY = credentials('secret-key-cli')
		AWS_DEFAULT_REGION = 'us-east-1'
		DEV_URL = "ec2-18-206-153-41.compute-1.amazonaws.com"
		PROD_URL = "ec2-34-231-229-103.compute-1.amazonaws.com"
		ECR_REPO = "543447831277.dkr.ecr.us-east-1.amazonaws.com/virtual-hb-api"
		JENKINS_URL = "ec2-34-239-178-45.compute-1.amazonaws.com"
		RELEASE_BUCKET = "zolon-hb-releases"
	}
	stages {
		stage('Test') {
			agent {
				dockerfile { 
					filename 'Dockerfile.build'
				}
			}
			steps {
				echo "Running tests"
				sh 'mvn install -Djasypt.encryptor.password=honey'
				sh "mvn sonar:sonar -Djasypt.encryptor.password=honey -Dsonar.jdbc.url=jdbc:h2:tcp://$JENKINS_URL:9093/sonar -Dsonar.host.url=http://$JENKINS_URL:9200/"
			}
		}
		stage('Build') {
			agent {
				dockerfile { 
					filename 'Dockerfile.build'
				}
			}
			steps {
				echo "building an artifact"
				sh "mvn package -Djasypt.encryptor.password=honey -Dsonar.jdbc.url=jdbc:h2:tcp://$JENKINS_URL:9093/sonar -Dsonar.host.url=http://$JENKINS_URL:9200/"
			}
		}
		stage('Push dev artifact') {
			agent {
				dockerfile { 
					filename 'Dockerfile.build'
				}
			}
			when {
				branch 'dev'
			}
			steps {
				echo "Packaging development artifacts"
				sh 'mvn package -Djasypt.encryptor.password=honey'
				sh "aws s3 cp ./target/honeybadger-0.1.0.jar s3://$RELEASE_BUCKET/honeybadger-0.1.0.dev.jar"
			}
		}
		stage('Deploy - Dev') {
			agent { label 'master' }
			when {
				branch 'dev'
			}
			steps {
				echo "Deploying to Development"
				deployToEnv('dev', $ECR_REPO)
			}
		}
		stage('Push Prod artifact') {
			agent {
				dockerfile { 
					filename 'Dockerfile.build'
				}
			}
			when {
				branch 'master'
			}
			steps {
				echo "Packaging Production artifacts"
				sh 'mvn package -Djasypt.encryptor.password=honey'
				sh 'aws s3 cp ./target/honeybadger-0.1.0.jar s3://$RELEASE_BUCKET/honeybadger-0.1.0.prod.jar'
			}
		}
		stage('Deploy - Production') {
			agent { label 'master' }
			when {
				branch 'master'
			}
			steps {
				echo "Deploying to Production"
				deployToEnv('prod', $ECR_REPO)
			}
		}
	}
}

def deployToEnv(String targetEnv, String ecr_repo) {
	sh "aws s3 cp s3://zolon-hb-releases/honeybadger-0.1.0.${targetEnv}.jar ./target/honeybadger-0.1.0.jar"
	sh "docker build -t ${ecr_repo}:${targetEnv} ."
	sh 'eval $(aws ecr get-login --region us-east-1 --no-include-email)'
	sh "docker push ${ecr_repo}:${targetEnv}"
	if (targetEnv == "dev") {
		sh "scp -i $JENKINS_EC2_SSH -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ./deploy.sh ec2-user@$DEV_URL:/home/ec2-user"
		sh "ssh -i $JENKINS_EC2_SSH -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ec2-user@$DEV_URL '/bin/bash /home/ec2-user/deploy.sh ${targetEnv} ${ecr_repo}'"		
	} else {
		sh "scp -i $JENKINS_EC2_SSH -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ./deploy.sh ec2-user@$PROD_URL:/home/ec2-user"
		sh "ssh -i $JENKINS_EC2_SSH -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no ec2-user@$PROD_URL '/bin/bash /home/ec2-user/deploy.sh ${targetEnv} ${ecr_repo}'"
	}
}