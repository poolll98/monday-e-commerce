so either download the image from here and then do

docker load < mon-pg.tar
docker run --name mon-pg -p 5432:5432 -e POSTGRES_PASSWORD=xyz -d postgres
docker start mon-pg

or take it from docker hub

docker pull raul1doru2m/mon-team
