docker service rm app-api;
docker build --tag 55.55.55.3/admin/app-api .;

docker login -u admin -p l3tm3@dm1n https://55.55.55.3;
docker push 55.55.55.3/admin/app-api;

docker service create --replicas 1 --network whiteboard_network --publish 8080:8080 --name app-api 55.55.55.3/admin/app-api;
