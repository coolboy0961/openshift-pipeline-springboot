# openshift-pipeline-springboot
- Step1: Git Push すると、Pipelineが実行され、springbootのアプリケーションがデプロイされて、インターネット経由でHello Worldを表示すること
- Step2: PostgreSQLとやりとりして、取得したデータを返すこと

# Step1の構築手順
## Giteaの作成
[手順](infra/gitea.md)
# truableshooting

## OpenShift Sandboxを利用する場合
結論として、Pipelineを構築することができないです。
- Admin権限がもらえない
- デフォルトにopenshift pipelineがインストールされているが、Cluster Taskがインストールされていない

従って、トラブルの「runAsNonRootのimageをrootでrunしようとしている」を解決することができない
### 解決済み
#### service account ''pipeline" が存在しない
failed to create task run pod "sample-pipeline-run-3-git-clone": translating TaskSpec to Pod: serviceaccounts "pipeline" not found. Maybe missing or invalid Task jiadchen-dev/git-clone

service account ''pipeline"を作成した
https://access.redhat.com/solutions/6962429

#### pipelineRunをいっぱい作ると、failed to create PVC
Failed to create PVC for PipelineRun jiadchen-dev/sample-pipeline-run-4 Workspaces correctly: failed to create PVC pvc-fd7a37da53: persistentvolumeclaims "pvc-fd7a37da53" is forbidden: exceeded quota: storage, requested: count/persistentvolumeclaims=1, used: count/persistentvolumeclaims=5, limited: count/persistentvolumeclaims=5

pipelineRunを削除して再度Createしたらうまくいった

#### runAsNonRootのimageをrootでrunしようとしている
Error: container has runAsNonRoot and image will run as root (pod: "sample-pipeline-run-4-git-clone-pod_jiadchen-dev(3f0e1591-9fba-48e0-8566-7cf7acce6874)", container: prepare)

Developer Sandbox for Red Hat OpenShift では Openshift pipelineが使えないことがわかった。
なぜかOpenshift Pipeline Operator がインストールされているが、一緒についてくるClusterTaskがない。
ClusterTaskのgit-cloneタスクを使わなければこのエラーを解決できません。（Developer Sandbox for Red Hat OpenShiftのadmin権限を取得できない）

それで社内のadmin権限がもらえるデモ環境でOpenshift Pipeline をインストールして、ClusterTaskのgit-cloneタスクを使うと、このエラーがなくなった

#### workspace binding 'source' does not match any declared workspace
buildタスクが実行されると、このエラーが出て、workspaceのsourceが見つからないようです。
こちらの[記事](https://github.com/tektoncd/pipeline/issues/5732)によると、taskのspecにsourceという名前のworkspaceを宣言する必要があることがわかった。

## 未解決