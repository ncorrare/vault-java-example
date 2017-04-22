pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        withMaven(maven: 'maven-3.2.5') {
          sh 'mvn test'
        }
        
      }
    }
    stage('Compile') {
      steps {
        withMaven(maven: 'maven-3.2.5') {
          sh 'mvn compile'
        }
        
      }
    }
    stage('Package') {
      steps {
        withMaven(maven: 'maven-3.2.5') {
          sh 'mvn package'
        }
        
      }
    }
    stage('Notify') {
      steps {
        echo 'Build Successful!'
      }
    }
  }
  environment {
    mvnHome = 'maven-3.2.5'
  }
}