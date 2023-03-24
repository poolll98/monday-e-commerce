# Getting Started with Monday E-Commerce Frontend

## Development

### Configuring your IDE

We recommend using VS Code as an IDE.

Recommended Plugins are

- Prettier Code Formatter
- ES7+ React/Redux/React-Native snippets
- ESLint

You can configure VS Code to use Prettier as formatter and format when saving as follows:

1. Use [Ctrl]+[Shift]+[p]
2. Type "Preferences"
3. Select "Preferences: Open User Settings"
4. Search for "format"
5. Change "Editor: Format On Save" or "Editor: Format On Paste".
6. Search for "formatter"
7. Select "Prettier" as default formatter.

### Running the App

The react app can be run in a Docker container. Use the docker-compose.dev.yml file to run it on localhost:3000. This is the recommended setup for development, as it doesn't compile/minify anything and includes hot reload (any changes to the code are reflected immediately).

Dev container with hot reload (execute from within /Frontend):

`docker build -t my-app:latest -f Dockerfile.dev .`

`docker compose -f docker-compose.dev.yml up`

Or in one step:

`docker compose -f docker-compose.dev.yml up --build`

## Deployment

For deployment, a production container can be created: It uses a two-stage build to create the optimized app, and then copy it to an optimized image from where it is served.

Production container:

`docker build -t my-react-app:latest .`

`docker compose up`
