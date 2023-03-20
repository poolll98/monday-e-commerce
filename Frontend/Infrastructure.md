## Available Containers

The react app can be run in a Docker container. Use the dev docker-compose file to run it on localhost:3000. This is the recommended setup for development, as it doesn't compile/minify anything and includes hot reload (any changes to the code are reflected immediately).

For deployment, a production container can be created: It uses a two-stage build to create the optimized app, and then copy it to an optimized image from where it is served.

## Run the containers

Dev container with hot reload (execute from within /Frontend):

`docker build -t my-app:latest -f Dockerfile.dev .`

`docker compose -f docker-compose.dev.yml up`

Or in one step:

`docker compose -f docker-compose.dev.yml up --build`

Production container:

`docker build -t my-react-app:latest .`

`docker compose up`