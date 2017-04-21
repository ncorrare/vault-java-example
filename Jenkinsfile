pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        def mvnHome = tool 'maven-3.2.5'
        sh "${mvnHome}/bin/mvn test"
      }
    }
    stage('Compile') {
      steps {
        def mvnHome = tool 'maven-3.2.5'
        sh "${mvnHome}/bin/mvn compile"
      }
    }
    stage('Package') {
      steps {
        def mvnHome = tool 'maven-3.2.5'
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
