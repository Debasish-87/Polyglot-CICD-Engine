---

# Polyglot CI/CD Platform

A universal **CI/CD platform for polyglot microservices** leveraging **Jenkins, GitHub Actions, and Tekton** to automate builds, tests, and deployments across multiple languages and frameworks.

Supports **Java, Node.js, and Python microservices** with seamless integration to **AWS EKS & ECR**.

---

## Key Features

* **Multi-Language Support:** Java, Node.js, Python.
* **CI/CD Automation:** Jenkins pipelines, GitHub Actions workflows, Tekton pipelines.
* **Containerization:** Docker images for all services.
* **Cloud Native Deployment:** Kubernetes manifests for EKS deployment.
* **Scalable & Modular:** Microservices architecture ready for production.
* **Infrastructure as Code:** Kubernetes manifests and deployment automation.
* **Observability Ready:** Integrates with CloudWatch, Prometheus, and other monitoring tools.

---



## Project Structure

```text
Polyglot-CICD-Platform/
├── .github/workflows/        # GitHub Actions CI/CD pipelines
├── k8s/                      # Kubernetes manifests for EKS deployment
├── scripts/                  # Utility scripts for automation
├── services/                 # Microservice directories
│   ├── java-app/
│   ├── node-app/
│   └── python-app/
├── tekton/                   # Tekton pipeline definitions
├── Dockerfile                # Sample Dockerfiles for multi-service builds
├── Jenkinsfile               # Jenkins pipeline configuration
├── Makefile                  # Build and deployment shortcuts
├── README.md                 # Documentation
└── LICENSE
```

---

## Prerequisites

* **Docker** (v20+)
* **kubectl** (v1.25+)
* **AWS CLI** (v2)
* **Eksctl** (v0.214+)
* **Tekton CLI (`tkn`)**
* **Maven** (for Java service)
* **Node.js & npm** (for Node service)
* **Python 3.x & pip** (for Python service)

---

## CI/CD Pipelines

### 1. GitHub Actions

* **Workflow:** `.github/workflows/ci-cd-multi-microservice.yml`
* Triggered on push or pull request to `main` branch.
* Steps:

  1. Checkout code
  2. Configure AWS credentials
  3. Build Docker images per service
  4. Push images to AWS ECR
  5. Deploy to EKS using `kubectl apply`

### 2. Jenkins

* Pipeline defined in `Jenkinsfile`.
* Supports parallel builds for all microservices.
* Deploys images to AWS ECR and updates Kubernetes manifests.

### 3. Tekton

* Tekton pipelines located in `tekton/`.
* Modular and declarative CI/CD tasks.
* Compatible with Tekton Hub resources for shared tasks.

---

## Build & Deploy Locally

### Java App

```bash
cd services/java-app
mvn clean package
docker build -t java-app:latest .
```

### Node.js App

```bash
cd services/node-app
npm install
npm test
docker build -t node-app:latest .
```

### Python App

```bash
cd services/python-app
pip install -r requirements.txt
docker build -t python-app:latest .
```

### Deploy to Kubernetes

```bash
kubectl apply -f k8s/java-app-deployment.yaml
kubectl apply -f k8s/node-app-deployment.yaml
kubectl apply -f k8s/python-app-deployment.yaml
kubectl apply -f k8s/multi-app-ingress.yaml
```

---

## AWS Integration

* **ECR:** Stores Docker images per microservice.
* **EKS:** Hosts microservices using Kubernetes.
* **CloudWatch / Prometheus:** Observability for apps & cluster.

**ECR URIs Example:**

```
java-app: 676509179952.dkr.ecr.ap-south-1.amazonaws.com/java-app
node-app: 676509179952.dkr.ecr.ap-south-1.amazonaws.com/node-app
python-app: 676509179952.dkr.ecr.ap-south-1.amazonaws.com/python-app
```

---

## Tekton CLI Commands

* `tkn pipeline list` — List available pipelines
* `tkn pipelinerun start <pipeline>` — Run a pipeline
* `tkn task list` — List available tasks
* `tkn taskrun describe <taskrun>` — Inspect a task run

> Configure `tkn` as a `kubectl` plugin for seamless CLI integration.

---

## Screenshots

### AWS EKS

![EKS Screenshot](https://github.com/user-attachments/assets/fef87f12-7da9-45fc-8dcd-64b82a9ebf5f)

### AWS ECR

![ECR Screenshot](https://github.com/user-attachments/assets/2aeadcdd-55dc-499c-bc00-292ae8fefd49)

### GitHub Actions

![GitHub Actions Screenshot](https://github.com/user-attachments/assets/2a6ea9b2-aacd-48d8-893a-f626094c733c)

### Java App Running

![Java App Screenshot](https://github.com/user-attachments/assets/e7f1ca29-932b-491c-81fd-840fbcd230b9)

### Node App Running

![Node App Screenshot](https://github.com/user-attachments/assets/75ac5cc4-b345-4a78-8853-52e8419dd6bc)

### Python App Running

![Python App Screenshot](https://github.com/user-attachments/assets/2b86aa47-ad17-4750-94e8-33318c57b3bf)

---

## Maintenance & Cleanup

### Delete Kubernetes Resources

```bash
kubectl delete deployment java-app node-app python-app
kubectl delete service java-app-service node-app-service python-app-service
kubectl delete ingress multi-app-ingress
```

### Delete AWS ECR Images

```bash
aws ecr batch-delete-image --repository-name java-app --image-ids imageTag=latest
aws ecr batch-delete-image --repository-name node-app --image-ids imageTag=latest
aws ecr batch-delete-image --repository-name python-app --image-ids imageTag=latest
```

### Delete EKS Cluster & Nodegroups

```bash
aws eks delete-nodegroup --cluster-name polyci-cluster --nodegroup-name <nodegroup>
aws eks wait nodegroup-deleted --cluster-name polyci-cluster --nodegroup-name <nodegroup>
aws eks delete-cluster --name polyci-cluster
```

---

## References

* [AWS EKS Documentation](https://docs.aws.amazon.com/eks/latest/userguide/what-is-eks.html)
* [AWS ECR Documentation](https://docs.aws.amazon.com/ecr/latest/userguide/what-is-ecr.html)
* [Tekton Pipelines](https://tekton.dev/docs/)
* [Kubernetes Documentation](https://kubernetes.io/docs/home/)

---

## License

Apache-2.0 © 2025 [Debasish-87](https://github.com/Debasish-87)



---
