# Opeartorのインストール
https://github.com/redhat-gpte-devopsautomation/gitea-operator

openshift の Web Terminalを使って下記コマンドを打ちます。
`oc apply -f https://raw.githubusercontent.com/redhat-gpte-devopsautomation/gitea-operator/master/catalog_source.yaml`
これでOperator HubでGiteaが現れます。

Gitea Operatorをインストールします。

# Gitea instanceを作成
Gitea用 Projectを作成
```
oc new-project gitea
```
![](asset/gitea.md_2022-11-14-12-00-47.png)
```yaml
apiVersion: gpte.opentlc.com/v1
kind: Gitea
metadata:
  name: simple-gitea
spec:
  giteaImageTag: latest
  giteaSsl: true
  giteaVolumeSize: 4Gi
  postgresqlVolumeSize: 4Gi
```

GiteaのWeb画面にアクセスします。
![](asset/gitea.md_2022-11-14-12-12-22.png)
