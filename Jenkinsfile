pipeline{
    agent any
    parameters{
        string(name: 'credential')
    }

    stages{
        stage ('Cleansing'){
            steps{
                cleanWs()
            }
        }

        stage('Getting git') {
            steps{
                git branch: 'master',
                credentialsId: "${params.credential}",
                url: 'https://github.com/magalhaes-andre/gatling-poc'
            }
        }

        stage ('Running Docker Image'){
            steps{
                sh "docker run -d -p 8083:8080 magalhaesandre/rest-go-calc"
            }
        }

        stage ('Running Gatling Test'){
            steps{
                    sh "sbt gatling:test"
            }
        }
    }
}