def call() {
  env.SONAR_JAVA_PROPERTY = ""
      node() {
    common.codeCheckOut()
    common.sonarCheck()
    common.uploadArtifact()
    common.makeAMI()
  }
}
