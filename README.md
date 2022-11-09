# openshift-pipeline-springboot
Step1: Git Push すると、Pipelineが実行され、springbootのアプリケーションがデプロイされて、インターネット経由でHello Worldを表示すること
Step2: PostgreSQLとやりとりして、取得したデータを返すこと

# truableshooting

## 解決済み
### service account ''pipeline" が存在しない
failed to create task run pod "sample-pipeline-run-3-git-clone": translating TaskSpec to Pod: serviceaccounts "pipeline" not found. Maybe missing or invalid Task jiadchen-dev/git-clone

service account ''pipeline"を作成した
https://access.redhat.com/solutions/6962429

### pipelineRunをいっぱい作ると、failed to create PVC
Failed to create PVC for PipelineRun jiadchen-dev/sample-pipeline-run-4 Workspaces correctly: failed to create PVC pvc-fd7a37da53: persistentvolumeclaims "pvc-fd7a37da53" is forbidden: exceeded quota: storage, requested: count/persistentvolumeclaims=1, used: count/persistentvolumeclaims=5, limited: count/persistentvolumeclaims=5

pipelineRunを削除して再度Createしたらうまくいった

## 未解決
## runAsNonRootのimageをrootでrunしようとしている
Error: container has runAsNonRoot and image will run as root (pod: "sample-pipeline-run-4-git-clone-pod_jiadchen-dev(3f0e1591-9fba-48e0-8566-7cf7acce6874)", container: prepare)

```
bash-4.4 ~ $ oc exec -it sample-pipeline-run-4-git-clone-pod -- sh -c "id"
Defaulted container "step-clone" out of: step-clone, prepare (init), place-scripts (init)
Error from server (Forbidden): pods "sample-pipeline-run-4-git-clone-pod" is forbidden: exec operation is not allowed because the pod's security context exceeds your permissions: pods "sample-pipeline-run-4-git-clone-pod" is forbidden: unable to validate against any security context constraint: [provider "anyuid": Forbidden: not usable by user or serviceaccount, provider "pipelines-scc": Forbidden: not usable by user or serviceaccount, spec.containers[0].securityContext.runAsUser: Invalid value: 65532: must be in the ranges: [1013410000, 1013419999], provider "nonroot-v2": Forbidden: not usable by user or serviceaccount, provider "nonroot": Forbidden: not usable by user or serviceaccount, provider "pcap-dedicated-admins": Forbidden: not usable by user or serviceaccount, provider "hostmount-anyuid": Forbidden: not usable by user or serviceaccount, provider "log-collector-scc": Forbidden: not usable by user or serviceaccount, provider "machine-api-termination-handler": Forbidden: not usable by user or serviceaccount, provider "hostnetwork-v2": Forbidden: not usable by user or serviceaccount, provider "hostnetwork": Forbidden: not usable by user or serviceaccount, provider "hostaccess": Forbidden: not usable by user or serviceaccount, provider "splunkforwarder": Forbidden: not usable by user or serviceaccount, provider "node-exporter": Forbidden: not usable by user or serviceaccount, provider "privileged": Forbidden: not usable by user or serviceaccount]
```