pipeline {
  agent any
  environment {
    mvnHome = tool 'maven-3.2.5'
  }
  stages {
    stage('Test') {
      steps {
        sh "${mvnHome}/bin/mvn test"
      }
    }
    stage('Compile') {
      steps {
        sh "${mvnHome}/bin/mvn compile"
      }
    }
    stage('Package') {
      steps {
        sh "${mvnHome}/bin/mvn package"
      }
    }
    stage('Success!') {
      steps {
        echo 'Build Successful!'
      }
    }
  }
}
