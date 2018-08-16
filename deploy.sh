#!/bin/bash
eval $(aws ecr get-login --region us-east-1 --no-include-email)
docker pull $2:$1
docker kill honeybadger-$1 || true
docker run --rm -d --name honeybadger-$1 -p 8080:8080 $2:$1