docker service rm api-gateway;
docker build --tag 55.55.55.3/admin/api-gateway .;

docker login -u admin -p l3tm3@dm1n https://55.55.55.3;
docker push 55.55.55.3/admin/api-gateway;

docker service create --replicas 1 --network whiteboard_network --publish 80:8000 --name api-gateway 55.55.55.3/admin/api-gateway;
