# base-api

Simple codebase to use as template for API in a given tech challenge.

# How to use this codebase

* `git clone https://github.com/ZolonTech/base-api`
* `cd base-api`
* `git remote add new-repo https://github.com/ZolonTech/<name of challenge api repo>`

Now, the new challenge repo will have an app that runs for local testing like so:

* `mvn package && java -jar target/gs-spring-boot-0.1.0.jar`

Build a docker image like so (after creating an artifact):

* `docker build -t <name of challenge> .`
