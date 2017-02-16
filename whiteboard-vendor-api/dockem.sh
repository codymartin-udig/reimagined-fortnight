docker service rm vendor-api;
docker build --tag 55.55.55.3/admin/vendor-api .;

docker login -u admin -p l3tm3@dm1n https://55.55.55.3;
docker push 55.55.55.3/admin/vendor-api;

docker service create --replicas 1 --network whiteboard_network --publish 8081:8081 --name vendor-api 55.55.55.3/admin/vendor-api;
