## Criando imagem
```
docker build -t ubuntu-nginx . 
```

Obs: "ubuntu-nginx" vai será o nome da imagem


## Rodar uma imagem
Caso não tenha iniciado nginx pelo Dockerfile
```
docker run -d -p 8000:80 ubuntu-nginx /usr/sbin/nginx -g "daemon off;"
```
ou:
```
docker run -d --name nginx -p 8000:80 ubuntu-nginx
```
Obs: --name nginx seta o nome para [b]nginx[/b]