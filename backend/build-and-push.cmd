./gradle build
aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin 809541497932.dkr.ecr.eu-central-1.amazonaws.com
docker build -t capgemini-challenge .
docker tag capgemini-challenge:latest 809541497932.dkr.ecr.eu-central-1.amazonaws.com/capgemini-challenge:latest
docker push 809541497932.dkr.ecr.eu-central-1.amazonaws.com/capgemini-challenge:latest
