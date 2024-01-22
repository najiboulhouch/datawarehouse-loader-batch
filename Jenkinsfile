pipeline {
    agent any 
    parameters {
        string(name: 'VERSION' , defaultValue: '' , description: 'version to deploy on prod')
        choise(name: 'VERSION' , choises: ['1.1.0' , '1.2.0' , "1.3.0"] , description: '')
        booleanParam(name: 'executeTests' , defaultValue: true, description : '')
    }
    tools {
        maven "Maven"
    }
    environment {
        NEW_VERSION = '1.3.0'
        SERVER_CREDENTIALS = creadentials('server-credential')
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                echo "building version ${NEW_VERSION}"
            }
        }
        stage('Test') {
            when {
                expression {
                    params.executeTests
                }
            }
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
                withCredentials {[
                    usernamePassword( creadentials('server-credential') , usernameVariable : USER , passwordVariable : PWD)
                ]} {
                    sh "some script ${USER} ${PWD}"
                }
            }
        }
    }
}
