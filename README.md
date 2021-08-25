# bike-sharing api service

* Swagger is available by the path: http://localhost:8080/swagger-ui.html
* Heroku is available by the path: http://bikesharing-2.herokuapp.com/swagger-ui.html
* RabbitMQ web UI: http://localhost:15672/
* MailHog available at: http://localhost:8025/


* Start application with docker: $> docker-compose up --build


* Connection to dataBase in container:
1) $> docker exec -it {ContainerId} bash 
2) root@...:/# psql -d bikesharing -h localhost -p 5432 -U postgres
3) bikesharing=# \c