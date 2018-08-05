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

			}
		}
		stage('Build') {
			steps {

			}
		}
		stage('Deploy - development') {
			when {
				branch 'dev'
			}
			steps {
				echo "Packaging development artifacts"
			}
		}
		stage('Deploy - production') {
			when {
				branch 'master'
			}
			steps {
				echo "Packaging production artifacts"
			}
		}
	}
}