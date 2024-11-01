docker compose -f docker-compose-env.yml up -d &&
sleep 20 &&
docker compose -f docker-compose-service.yml  up -d
