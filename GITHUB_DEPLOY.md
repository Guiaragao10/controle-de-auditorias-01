# 🚀 Deploy via GitHub (Automático)

Este guia mostra como usar o **GitHub Actions** para build e deploy automáticos.

---

## 📦 O que foi configurado

### ✅ GitHub Actions Workflows criados:

1. **`.github/workflows/deploy.yml`** - Build e testes automáticos
2. **`.github/workflows/docker.yml`** - Build e publicação de imagem Docker
3. **`.github/workflows/publish.yml`** - Publicação no GitHub Packages

---

## 🔄 Build Automático (Já Funcionando!)

### Quando você faz `git push`:

✅ **Build automático** com Maven  
✅ **Testes executados**  
✅ **JAR gerado** e disponível para download  
✅ **Docker image** publicada no GitHub Container Registry  

### Ver builds:

🔗 **https://github.com/Guiaragao10/controle-de-auditorias-01/actions**

---

## 🐳 Imagem Docker no GitHub

### Após o push, sua imagem estará disponível em:

```
ghcr.io/guiaragao10/controle-de-auditorias-01:latest
```

### Executar com Docker:

```bash
# Pull da imagem
docker pull ghcr.io/guiaragao10/controle-de-auditorias-01:latest

# Executar
docker run -p 8080:8080 ghcr.io/guiaragao10/controle-de-auditorias-01:latest
```

---

## 🌐 Deploy Automático para Cloud

### Opção 1: Railway + GitHub Actions

1. **Vá em:** https://railway.app
2. **New Project** → **Deploy from GitHub repo**
3. Selecione: `controle-de-auditorias-01`
4. ✅ **Railway detecta automaticamente os workflows!**
5. **Deploy automático** a cada push

### Opção 2: Render + GitHub

1. **Vá em:** https://render.com
2. **New Web Service** → Conecte GitHub
3. Selecione: `controle-de-auditorias-01`
4. Render usa o `render.yaml` automaticamente
5. ✅ **Deploy automático** configurado!

### Opção 3: Azure Web App + GitHub Actions

```yaml
# Adicione em deploy.yml (opcional)
- name: Deploy para Azure
  uses: azure/webapps-deploy@v2
  with:
    app-name: 'controle-auditorias'
    publish-profile: ${{ secrets.AZURE_WEBAPP_PUBLISH_PROFILE }}
    package: target/crudapp-0.0.1-SNAPSHOT.jar
```

---

## 📊 Status do Build

Adicione este badge no README.md:

```markdown
![Build Status](https://github.com/Guiaragao10/controle-de-auditorias-01/workflows/Deploy%20Spring%20Boot%20App/badge.svg)
```

Resultado:

![Build Status](https://github.com/Guiaragao10/controle-de-auditorias-01/workflows/Deploy%20Spring%20Boot%20App/badge.svg)

---

## 🔐 Secrets Necessários (Opcional)

### Para deploy automático, configure em:
**Settings → Secrets and variables → Actions**

| Secret | Descrição | Obrigatório |
|--------|-----------|-------------|
| `RAILWAY_TOKEN` | Token do Railway para deploy | ❌ Opcional |
| `AZURE_WEBAPP_PUBLISH_PROFILE` | Perfil Azure | ❌ Opcional |
| `RENDER_API_KEY` | API Key do Render | ❌ Opcional |

---

## 🎯 Como Funciona

### 1. Você faz um commit:

```bash
git add .
git commit -m "Nova feature"
git push origin master
```

### 2. GitHub Actions automaticamente:

✅ Faz checkout do código  
✅ Configura Java 21  
✅ Executa `./mvnw clean package`  
✅ Roda testes  
✅ Gera JAR otimizado  
✅ Cria imagem Docker  
✅ Publica no GitHub Container Registry  

### 3. Resultado:

🔗 **Build logs:** https://github.com/Guiaragao10/controle-de-auditorias-01/actions  
🐳 **Docker image:** ghcr.io/guiaragao10/controle-de-auditorias-01  
📦 **JAR artifact:** Disponível para download no Actions  

---

## 🚀 Deploy Manual via GitHub CLI

### Instalar GitHub CLI:

```powershell
# Windows
winget install GitHub.cli

# Verificar
gh --version
```

### Deploy direto do repositório:

```bash
# Login
gh auth login

# Ver workflows
gh workflow list

# Executar workflow manualmente
gh workflow run deploy.yml

# Ver status
gh run list
```

---

## 📦 Download do JAR compilado

### Via GitHub Actions:

1. Vá em: **Actions** → Último workflow com ✅
2. Scroll até **Artifacts**
3. Download: **crudapp-jar.zip**
4. Extraia e execute:

```bash
java -jar crudapp-0.0.1-SNAPSHOT.jar
```

---

## 🔄 Pipeline Completo

```
┌─────────────┐
│  Git Push   │
└──────┬──────┘
       │
       ▼
┌─────────────────────┐
│  GitHub Actions     │
│  - Build Maven      │
│  - Run Tests        │
│  - Create JAR       │
└──────┬──────────────┘
       │
       ├──────────────────┐
       │                  │
       ▼                  ▼
┌─────────────┐   ┌─────────────────┐
│  Artifacts  │   │  Docker Image   │
│  (JAR)      │   │  (GHCR)         │
└─────────────┘   └─────────────────┘
       │                  │
       └────────┬─────────┘
                │
                ▼
       ┌─────────────────┐
       │  Cloud Deploy   │
       │  (Automático)   │
       └─────────────────┘
```

---

## ✅ Verificar se está funcionando

### 1. Veja o último build:

```bash
gh run list --limit 5
```

### 2. Ver logs em tempo real:

```bash
gh run watch
```

### 3. Ver imagens Docker:

```bash
gh api user/packages
```

---

## 🎉 Próximos Passos

### Depois do push, você pode:

1. ✅ **Ver build automático** no GitHub Actions
2. ✅ **Baixar JAR** compilado dos Artifacts
3. ✅ **Usar Docker image** do GitHub Container Registry
4. ✅ **Deploy automático** em Railway/Render
5. ✅ **Monitorar** builds e deploys

---

## 🆘 Troubleshooting

### Build falhou?

```bash
# Ver logs detalhados
gh run view --log-failed

# Re-executar workflow
gh run rerun <run-id>
```

### Docker image não aparece?

- Verifique se o repositório está **público** ou
- Configure permissões: **Settings → Actions → General**
- Habilite: **Read and write permissions**

---

## 📚 Links Úteis

- 🔗 **Actions:** https://github.com/Guiaragao10/controle-de-auditorias-01/actions
- 🐳 **Packages:** https://github.com/Guiaragao10/controle-de-auditorias-01/pkgs/container/controle-de-auditorias-01
- 📖 **GitHub Actions Docs:** https://docs.github.com/actions

---

**🎯 RESUMO:** Agora a cada `git push`, seu projeto é automaticamente compilado, testado e disponibilizado como JAR e Docker image! 🚀
