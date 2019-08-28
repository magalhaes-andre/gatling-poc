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
                url: 'https://github.com/magalhaes-andre/jts.devops.2019.1'
            }
        }

        stage ('Running Docker Image'){
            steps{
                sh "docker run -d -p 8083:8080 magalhaesandre/rest-go-calc"
            }
        }

        stage ('Running Gatling Test'){
            steps{
                dir("andre-magalhaes/tema_16/"){
                    sh "sbt gatling:test"
                }
            }
        }
    }
}