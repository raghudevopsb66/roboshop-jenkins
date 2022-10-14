def call() {
  env.SONAR_JAVA_PROPERTY = "-Dsonar.java.binaries=./target"
  node() {
    common.codeCheckOut()
    stage('Download Dependencies') {
      sh '''
        mvn clean package 
      '''
    }
    common.sonarCheck()
    common.uploadArtifact()
    common.makeAMI()
  }
}
