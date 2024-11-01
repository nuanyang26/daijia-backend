docker compose -f docker-compose-service.yml down &&
sleep 10 &&
docker compose -f docker-compose-env.yml down
