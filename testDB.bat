@echo off
docker run --name postgres_db -p 5436:5432 -e POSTGRES_DB=jdbc_teszt -e POSTGRES_USER=user -e POSTGRES_PASSWORD=abc123 -d postgres
