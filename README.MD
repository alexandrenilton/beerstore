## Install postgresql on Docker-CE
```
docker run -p 5433:5432 --name beerdb -e POSTGRES_USER=beerstore -e POSTGRES_PASSWORD=beerstore -e POSTGRES_DB=beerstore -d postgres:10.5-alpine
```

For more information: 
https://hub.docker.com/_/postgres


## terraform

https://www.terraform.io/docs/providers/aws/r/vpc.html



## para conectar
```
ssh -i key/beerstore_key ec2-user@IP-Publico-da-instancia-na-aws-ec2
```
Ips publicos:
- Server-a 34.229.134.81
- Server-b 54.158.163.79
- Server-c 18.233.223.248

## iniciar modulos do terraform
```
terraform init
```

## Docker
```
docker pull openjdk:8u171-jdk-alpine3.8
docker build -t alexandrenilton/beerstore:0.1 .
```

Para rodar:
docker run -p 8080:8080 -rm alexandrenilton/beerstore:0.1

## Docker net
```
docker network create beer-net
docker network ls
```
Agora, bastar colocar seus containers dentro da mesma rede
```
docker network connect beer-net beerdb
docker network connect beer-net nginx
```
Proximo: Rodar container da app especificando a network
```
docker run -p 8080:8080 --rm --network beer-net -e APP_OPTIONS='--spring.datasource.url=jdbc:postgresql://beerdb:5432/beerstore' alexandrenilton/beerstore:0.2
```
Opção -d (no lugar do --rm) faz com que ele execute em background
```
docker run -p 8080:8080 -d --network beer-net -e APP_OPTIONS='--spring.datasource.url=jdbc:postgresql://beerdb:5432/beerstore' alexandrenilton/beerstore:0.2
```
Subir no hub.docker.com

```
docker push alexandrenilton/beerstore:0.2
```