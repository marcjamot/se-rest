# Rest template

Template for a REST micro service written in Kotlin.

## Features

* Kotlin
* Quarkus
* PostgreSQL
* Stateless architecture
* Kubernetes deployment
* Docker compose for local development with live reload
* Production ready native Dockerfile

## Local development

* `docker-compose up`

The `docker-compose.yaml` file will connect the micro service to a PostgreSQL database for persistent storage.

Quarkus dev is set up for live reload and will notice file changes in `src` on new requests. However, if dependencies change in the `pom.xml` file, `docker-compose` needs to be re-run.

## Deployment

* `docker build -t <tag> .`

Provided docker image has two steps.

1. Compile the project sources to a native binary.
2. Run the binary on `registry.access.redhat.com/ubi8/ubi-minimal`. 

## Kubernetes

* `kubectl apply -f k8s/`

In the `k8s` directory, resources are provided to deploy the micro service to a Kubernetes cluster.

**Required changes**

* Update image
