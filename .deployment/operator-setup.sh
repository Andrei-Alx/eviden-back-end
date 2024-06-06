#!/bin/sh

# Add NGINX ingress controller
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml

# Deploy CRD definitions used by the MySQL operator
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-crds.yaml
# Deploy the MySQL operator for K8, which includes RBAC definitions.
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-operator.yaml
# Add Kafka cluster operator
kubectl create -f 'https://strimzi.io/install/latest?namespace=default' -n default
