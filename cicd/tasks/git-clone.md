# Install git-clone task
Web Terminalにて
```
curl https://raw.githubusercontent.com/tektoncd/catalog/main/task/git-clone/0.9/git-clone.yaml -o git-clone.yaml
oc apply -f git-clone.yaml
```