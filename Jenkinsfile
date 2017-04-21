pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh '''mvn test
'''
      }
    }
    stage('Compile') {
      steps {
        sh 'mvn compile'
      }
    }
    stage('Package') {
      steps {
        sh 'mvn package'
      }
    }
    stage('Success!') {
      steps {
        echo 'Build Successful!'
      }
    }
  }
}