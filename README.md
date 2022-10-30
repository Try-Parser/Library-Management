# Setup

## Installation of Docker

- [Click here to get Docker](https://www.docker.com/products/docker-desktop)

## Project Clone

- `git clone https://github.com/Try-Parser/Library-Management.git`
- `cd Library Management`
- `docker-compose up` to stop press `CTR + C`

### Clean the setup and start the image files and run 

- `docker volume prune` to delete hidden volumes
- `docker rm $(docker ps -a -q)` to delete all containers in the docker 
- `docker rmi $(docker images -a -q)` to delete all images for the rebuild
- `docker-compose up` will rebuild all images that will be used to the container

## How to run the project

goto the `/Library Management` root of the project folder then type `docker-compose up -d --remove-orphans` for running all containers containing each environment.

- `docker-compose up -d --remove-orphans` : removing orphans while running daemonize environment
  - `docker-compose down` : which stop the running daemonize environment it only works with daemonize environment
  
- `docker-compose up --remove-orphans` : running the environment without daemon which you can look the logs of each env.

### Host and Ports 

- `client` : `localhost:8080` 
- `api` : `localhost:8081`

### Default user and password for the login page

- `Email` : `frank@gmail.com`
- `Password` : `a`
