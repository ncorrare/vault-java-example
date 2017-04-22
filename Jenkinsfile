pipeline {
  agent any
  stages {
    stage('Test') {
      def mvnHome = tool 'maven-3.2.5'
      steps {
        sh "${mvnHome}/bin/mvn test"
      }
    }
    stage('Compile') {
      def mvnHome = tool 'maven-3.2.5'
      steps {
        sh "${mvnHome}/bin/mvn compile"
      }
    }
    stage('Package') {
      def mvnHome = tool 'maven-3.2.5'
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
