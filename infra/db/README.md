Databaseの動作確認
```
oc project sample-application
oc apply https://github.com/coolboy0961/openshift-pipeline-springboot/blob/main/infra/db/postgres-menu.yaml
oc apply https://github.com/coolboy0961/openshift-pipeline-springboot/blob/main/infra/db/postgresql-client.yaml
```

postgresql-client pod の terminalに入って、psqlコマンドでDBへコネクションを貼ってみる
`psql -h menudb -p 5432 -d menu -U menu`