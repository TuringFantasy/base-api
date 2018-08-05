# base-api

Simple codebase to use as template for API in a given tech challenge.

# How to use this codebase

* `git clone https://github.com/ZolonTech/base-api`
* `cd base-api`
* `git remote add new-repo https://github.com/ZolonTech/<name of challenge api repo>`

Now, the new challenge repo will have an app that runs for local testing like so:

* `mvn package && java -jar target/honeybadger-0.1.0.jar`

Build a docker image like so (after creating an artifact):

* `docker build -t <name of challenge> .`

Want to test out code coverage? Two steps:

1. `docker run --rm --name sonarqube -p 9000:9000 -p 9092:9092 sonarqube` <login is admin/admin>
2. In another window at the root of this repo, run `mvn sonar:sonar`, then vist http://localhost:9200 to view the honeybadger project code coverage

