pipeline {
	agent {
		docker { 
			image 'maven:slim'
		}
	}
	environment {
		TEST = "None"
	}
	stages {
		stage('Test') {
			steps {
				echo "Running tests"
				sh 'mvn sonar:sonar'
			}
		}
		stage('Build') {
			steps {
				echo "building an artifact"
				sh 'mvn package'
			}
		}
		stage('Deploy - development') {
			when {
				branch 'dev'
			}
			steps {
				echo "Packaging development artifacts"
				sh 'mvn package'
				// deployment code for dev here
			}
		}
		stage('Deploy - production') {
			when {
				branch 'master'
			}
			steps {
				echo "Packaging production artifacts"
				sh 'mvn package'
				// deployment code for prod here
			}
		}
	}
}