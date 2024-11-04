docker compose -f docker-compose-service.yml -p daijia-service down &&
sleep 10 &&
docker compose -f docker-compose-env.yml -p daijia-env down