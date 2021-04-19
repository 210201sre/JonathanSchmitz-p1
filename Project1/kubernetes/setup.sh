helm repo list;
helm install ingress-nginx ingress-nginx/ingress/nginx;
cd grafana-chart;
helm install grafana grafana/grafana -f values.yml;
# kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
cd ../loki-chart;
helm install loki grafana/loki -f values.yml;
cd ..;

#while docker-desktop is running:
mvn spring-boot:build-image;
docker tag project-one:0.0.1-SNAPSHOT sentrurion/project-one:latest;
docker push sentrurion/project-one:latest;

kubectl create ns jonathan-schmitz;
kubectl create -n jonathan-schmitz secret generic jona-credentials --from-literal=url=$DB_URL --from-literal=username=$DB_USERNAME --from-literal=password=$DB_PASSWORD;
kubectl create -n jonathan-schmitz configmap fluent-conf --from-file fluent.conf;
kubectl apply -n jonathan-schmitz -f external-loki.yml;
kubectl apply -n jonathan-schmitz -f p-one-service.yml;
kubectl apply -n jonathan-schmitz -f p-one-ingress.yml;
kubectl apply -n jonathan-schmitz -f p-one-deployment.yml;

