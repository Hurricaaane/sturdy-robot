properties([[$class: 'GitLabConnectionProperty', gitLabConnection: 'gitlab']])
pipeline {
    agent {
        label 'docker'
    }
    options {
        skipDefaultCheckout true
    }
    stages {
        stage('Clean workspace') {
            steps {
                deleteDir()
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'mzagar/jenkins-slave-jdk-maven-git'
                    reuseNode true
                }
            }
            steps {
                checkout scm
                sh 'ls'
                sh '''
                    mkdir -p maven_local_repo
                    cd api-bird; mvn -Dmaven.repo.local=./maven_local_repo clean package
                '''
            }
        }
        stage('Create Distributable') {
            steps {
                archive 'stdin-consumer/target/exercise-stdin.jar'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            step([$class: 'Mailer', notifyEveryUnstableBuild: true, recipients: 'automation@ha3.eu'])
        }
        success {
            updateGitlabCommitStatus name: 'jenkins', state: 'success'
        }
        failure {
            updateGitlabCommitStatus name: 'jenkins', state: 'failed'
        }
        unstable {
            updateGitlabCommitStatus name: 'jenkins', state: 'failed'
        }
    }
}
