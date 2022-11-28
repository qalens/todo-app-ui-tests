# Running application Locally
## Via Docker: 
1. Login to github docker repository via command `echo '<Your Github Personal Access Token>' | docker login ghcr.io -u <github username> --password-stdin`
2. run docker command to run Application `docker run -p 3000:3000 ghcr.io/qalens/todo-app:main`

## Via Actual Code
1. Clone Github [Repo](https://github.com/qalens/todo-app.git)
2. Follow Instructions mentioned in Readme of Repo

# Running Tests Via Command line
`./gradlew clean test`