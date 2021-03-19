# Project One

## Description

A migration to kubernetes of Project Zero; a full-scale, backend, sales and inventory management system implemented in java using a PostgreSQL database.

## Technologies

* PostgreSQL - version 12.5
* Java JDK - version 1.8
* Spring-Boot - version 2.4.3
* Docker - version 20.10.5
* Kubernetes (EKS) - version 1.18.9
* FluentD - version 0.3.4
* Grafana - version 7.4.2
* nginx - version 0.44.0
* Loki - version 2.1.0

## Features
### Currently Available:
* Access control between Customers, Employees, and Admins.
* User account creation, modification, and deletion.
* Put items into a cart and perform checkout.
* View transaction and backorder history.
* Manage and restock products for sale and their manufacturer information.

### ToDo list:
* Implement entity relationship between models.
* Implement remaining employee services.
* Optionally add advice and/or custom annotations.
* Optionally create tables & models for recording sales (profits and costs).

## Getting Started
#### Have the following installed on a Windows 10 computer.
- [Docker Desktop](https://desktop.docker.com/win/stable/Docker%20Desktop%20Installer.exe)
  - Enable Kubernetes inside the application.
- [Postman](https://dl.pstmn.io/download/latest/win64)
- [GitBash](https://github.com/git-for-windows/git/releases/download/v2.31.0.windows.1/Git-2.31.0-64-bit.exe)


- Git clone this repository for the sql and yml files.
- Install locally or set up a Postgresql database through AWS.
  - Use files in SQL folder create the data and populate tables with sample data.
- Install / set up helm.
  - Add ingress-nginx, https://kubernetes.github.io/ingress-nginx and grafana, https://grafana.github.io/helm-charts to helm repo.
  - Run helm install ingress-nginx ingress-nginx/ingress/ngninx;
  - Run helm install grafana grafana/grafana -f grafanavalues.yml;
  - Run helm install loki grafana/loki -f lokivalues.yml;
- Create environment variables for DB_URL, DB_PASSWORD, and DB_USERNAME.
- Run the following commands to set up the application in Kubernetes (while kubernetes is running):
  - kubectl create secret generic jona-credentials --from-literal=url=$DB_URL --from-literal=username=$DB_USERNAME --from-literal=password=$DB_PASSWORD;
  - kubectl create configmap fluent-conf --from-file fluent.conf;
  - kubectl apply -f external-loki.yml;
  - kubectl apply -f p-one-service.yml;
  - kubectl apply -f p-one-ingress.yml;
  - kubectl apply -f p-one-deployment.yml;

## Usage
- Using Postman you can create requests to localhost:8080 with the routes provided in the controller classes.
  - Ex: A GET request to localhost:8080/inv will output all the items currently in the database's item table.
Note: All POST, PATCH, and most DELETE requests require Body data to be in raw JSON format.
- Use classes in the models folder to structure data sent in requests.
  - For models with similar names, only the ...Proto.java, prototype, classes are use when reading input from requests.
