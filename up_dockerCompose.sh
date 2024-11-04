docker compose -f docker-compose-env.yml -p daijia-env up -d &&
sleep 20 &&
docker compose -f docker-compose-service.yml -p daijia-service up -d
