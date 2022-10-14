def call() {

  env.SONAR_JAVA_PROPERTY = ""
  node() {

    common.codeCheckOut()

      stage('Download Dependencies') {
        sh '''
            npm install
          '''
      }

    common.sonarCheck()
    common.uploadArtifact()
    common.makeAMI()
  }

}