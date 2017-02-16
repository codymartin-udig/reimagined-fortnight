docker service rm content;
docker build --tag 55.55.55.3/admin/content .;

docker login -u admin -p l3tm3@dm1n https://55.55.55.3;
docker push 55.55.55.3/admin/content;

docker service create --replicas 1 --network whiteboard_network --publish 800:80 --name content 55.55.55.3/admin/content;
