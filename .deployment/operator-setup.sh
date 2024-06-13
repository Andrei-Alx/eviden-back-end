#!/bin/sh

# Add Longhorn storage
kubectl apply -f https://raw.githubusercontent.com/longhorn/longhorn/v1.6.2/deploy/longhorn.yaml
# Add NGINX ingress controller
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
# Add cert-manager for HTTPS
kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.15.0/cert-manager.yaml
## Add MetalLB LoadBalancer
kubectl apply -f https://raw.githubusercontent.com/metallb/metallb/v0.14.5/config/manifests/metallb-native.yaml
# Deploy CRD definitions used by the MySQL operator
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-crds.yaml
# Deploy the MySQL operator for K8, which includes RBAC definitions.
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-operator.yaml
# Add Kafka cluster operator
kubectl create -f 'https://strimzi.io/install/latest?namespace=default' -n default
