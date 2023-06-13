pipeline {
  agent any

  environment {
    DOCKER_IMAGE = 'spring-books-api'
  }

  stages {
    stage('Build ') {
      steps {
        script {
          sh 'mvn clean package'
        }
      }
    }

    stage('Set global variables') {
      steps {
        script {
          REPOSITORY = sh(
            returnStdout: true,
            script: "aws ecr describe-repositories --repository-names ${DOCKER_IMAGE} | \
                     grep -oP '(?<=\"repositoryUri\": \")[^\"/]+'"
          ).trim()

          REGISTRY = "${REPOSITORY}/${DOCKER_IMAGE}"

          APP_VERSION  = sh(
            returnStdout: true,
            script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout'
          ).trim()

          IMAGE_TAG    = "${DOCKER_IMAGE}:${APP_VERSION}"
        }
      }
    }

    stage('Login to repository') {
      steps {
        script {
          sh("aws ecr get-login-password | \
             docker login --username AWS --password-stdin ${REPOSITORY}"
          )
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          def jarFile = "./target/app-${APP_VERSION}.jar" 

          sh "docker build -t ${IMAGE_TAG} --build-arg JAR_FILE=${jarFile} ."

          sh "docker tag ${IMAGE_TAG} ${REGISTRY}:${APP_VERSION}"
        }
      }
    }

    stage('Push Docker Image to ECR') {
      steps {
        script {
            sh "docker push ${REGISTRY}:${APP_VERSION}"
        }
      }
    }

  }
}
