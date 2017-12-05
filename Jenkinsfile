pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'mvn install -DskipTests'
        archiveArtifacts(onlyIfSuccessful: true, artifacts: '**/target/*.jar')
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test -B || exit 0'
        sh 'mvn jacoco:report'
      }
      post {
        always {
          sh '[ -d server/target/site ] && cd server/target/site && zip -r coverage-server.zip jacoco && cd -; exit 0'
          junit(testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true)
          archiveArtifacts allowEmptyArchive: true, artifacts: '**/target/site/*.zip'
        }
      }
    }
  }
}
