def call() {
  if(!env.terraPath) {
    env.terraPath = "immutable"
  }
  pipeline {
    agent any

    options {
      ansiColor('xterm')
    }

    parameters {
      choice(name: 'ENV', choices: ['', 'dev', 'prod'], description: 'Pick Env')
    }

    stages {

      stage('Terraform Apply') {
        steps {
          sh '''
          cd ${terraPath}
          terrafile 
          terraform init -backend-config=env/${ENV}-backend.tfvars
          terraform apply -auto-approve -var-file=env/${ENV}.tfvars
        '''
        }
      }

    }

    post {
      always {
        cleanWs()
      }
    }

  }

}



//
//def call() {
//
//  node() {
//
//    properties([
//        parameters([
//            [$class: 'ChoiceParameterDefinition',
//             choices: '\ndev\nprod',
//             name: 'ENV',
//             description: "Pick Env"
//            ],
//        ]),
//    ])
//
//    ansiColor('xterm') {
//      stage('Terraform Apply') {
//        sh '''
//          cd mutable
//          terrafile
//          terraform init -backend-config=env/${ENV}-backend.tfvars
//          terraform apply -auto-approve -var-file=env/${ENV}.tfvars
//        '''
//      }
//    }
//
//  }
//
//}