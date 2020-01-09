# Rest template

Template for a REST micro service written in Kotlin.

Docker hub: https://hub.docker.com/r/mjam/se-rest

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

* Update secrets in `k8s/se-rest/00-secrets.yaml`.
* Make sure to **not** check in unencrypted secrets in the repository.
* Update image in `k8s/se-rest/20-deployment.yaml`.

## Local Kubernetes

Example set-up with k3s/k3d

* https://k3s.io/
* https://github.com/rancher/k3d

1. Create a cluster: `k3d create -n se-rest`.
2. Add cluster info: `export KUBECONFIG="$(k3d get-kubeconfig --name='se-rest')"`.
3. Apply secrets: `kubectl apply -f k8s/se-rest/00-secrets.yaml`
3. Set up PostgreSQL: `kubectl apply -f k8s/dev/00-postgres.yaml`
4. Apply all se-rest configs: `kubectl apply -f k8s/se-rest`.
5. Port forward: `kubectl port-forward svc/se-rest 8080:80`.
6. Curl: `curl http://localhost:8080/posts`.
