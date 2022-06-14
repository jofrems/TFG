## TFG - Els Col·lonitzadors de l'Illa

Benvingut!


Deploy path:

- url to heroku running


## Setup

There are two applications:

- Java Spring JPA backend (run in 8080)
- React Redux frontend (run in 3000)

The deploy merges both into one single war.

Setup Java BackEnd:

- Install Java v11

- Open the project with IntelliJ  
  (it will install all maven dependencies)

Manual Java Backend:

- Execute `./mvnw test`

Setup Javascript BackEnd:

- Install node v14 i yarn

- Execute `yarn`

## Develop Run

- Run the Application from intellij
  - use `-ea -client -Xverify:none -noverify` for faster test run
- Run `yarn start` from command line
- Open the browser in `http://localhost:3000`
  (the react opens a proxy with the backend and uses actual services)



