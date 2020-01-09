# Rest template

Template for a REST micro service written in Kotlin.

## Features

* Kotlin
* Quarkus
* PostgreSQL
* OpenAPI v3 documentation generation
* Kubernetes deployment
* Docker compose for local development with live reload
* Production ready native Dockerfile

## Docs

OpenAPI v3 documentation is generated on `localhost:8080/docs.yaml` and Swagger GUI is available on `localhost:8080/docs-ui`. The UI is only available on dev.

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
