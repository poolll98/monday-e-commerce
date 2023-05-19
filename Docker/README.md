To start the Application, navigate to the Docker folder of the project, then run:

"docker compose up"
or
"docker compose up -d", if you want to let the docker containers run in the background. 

MarketMate is now available at http:localhost:3001/

To start the Application in Development mode, with hot reload and mocking enabled, you can run:

docker compose -f docker-compose.dev.yml up"
or
"docker compose -f docker-compose.dev.yml up -d", if you want to let the docker containers run in the background.

MarketMate development version is now available at http:localhost:3000/

To stop, run: docker compose down
