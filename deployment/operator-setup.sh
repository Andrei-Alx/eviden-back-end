#!/bin/sh

# Deploy CRD definitions used by the MySQL operator
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-crds.yaml
# Deploy the MySQL operator for K8, which includes RBAC definitions.
kubectl apply -f https://raw.githubusercontent.com/mysql/mysql-operator/trunk/deploy/deploy-operator.yaml

kubectl create -f 'https://strimzi.io/install/latest?namespace=default' -n default
# Apply the `Kafka` Cluster CR file
kubectl apply -f https://strimzi.io/examples/latest/kafka/kafka-persistent-single.yaml -n default
