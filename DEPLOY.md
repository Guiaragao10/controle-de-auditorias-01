# 🚀 Guia de Deploy - Controle de Auditorias

Este guia mostra como colocar seu projeto **NO AR** em diferentes plataformas de cloud gratuitas.

---

## 🌐 Opção 1: Railway (RECOMENDADO - Mais Fácil)

### Passos:

1. **Acesse:** https://railway.app
2. **Faça login** com sua conta GitHub
3. Clique em **"New Project"**
4. Selecione **"Deploy from GitHub repo"**
5. Escolha o repositório: **`controle-de-auditorias-01`**
6. Railway detectará automaticamente que é Java/Maven
7. Aguarde o build e deploy automático (5-10 minutos)
8. ✅ **Pronto!** Railway fornecerá uma URL pública

### Configurações Automáticas:
- ✅ Build: `./mvnw clean package -DskipTests`
- ✅ Start: `java -jar target/crudapp-0.0.1-SNAPSHOT.jar`
- ✅ Porta: Configurada automaticamente via `$PORT`

### Verificar Deploy:
```bash
# Sua aplicação estará em:
https://seu-projeto.up.railway.app
```

---

## 🟣 Opção 2: Render (Gratuito)

### Passos:

1. **Acesse:** https://render.com
2. **Faça login** com GitHub
3. Clique em **"New +"** → **"Web Service"**
4. Conecte seu repositório GitHub: **`controle-de-auditorias-01`**
5. Configure:
   - **Name:** `controle-auditorias`
   - **Environment:** `Java`
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/crudapp-0.0.1-SNAPSHOT.jar`
   - **Instance Type:** `Free`
6. Clique em **"Create Web Service"**
7. Aguarde o deploy (10-15 minutos na primeira vez)

### Variáveis de Ambiente (Opcional):
```
JAVA_OPTS=-Xmx512m -Xms256m
```

### URL Pública:
```
https://controle-auditorias.onrender.com
```

**⚠️ IMPORTANTE:** Render free tier hiberna após 15 minutos de inatividade. Primeira requisição pode demorar 30-60 segundos.

---

## 🟦 Opção 3: Azure App Service (Crédito Gratuito)

### Pré-requisitos:
- Conta Azure (crédito gratuito de $200 para novos usuários)
- Azure CLI instalado

### Comandos:

```bash
# 1. Login no Azure
az login

# 2. Criar Resource Group
az group create --name rg-auditorias --location brazilsouth

# 3. Criar App Service Plan (Free Tier)
az appservice plan create \
  --name plan-auditorias \
  --resource-group rg-auditorias \
  --sku F1 \
  --is-linux

# 4. Criar Web App
az webapp create \
  --name controle-auditorias-app \
  --resource-group rg-auditorias \
  --plan plan-auditorias \
  --runtime "JAVA:21-java21"

# 5. Deploy do JAR
az webapp deploy \
  --resource-group rg-auditorias \
  --name controle-auditorias-app \
  --src-path target/crudapp-0.0.1-SNAPSHOT.jar \
  --type jar

# 6. Verificar URL
az webapp show \
  --name controle-auditorias-app \
  --resource-group rg-auditorias \
  --query defaultHostName \
  --output tsv
```

### URL Pública:
```
https://controle-auditorias-app.azurewebsites.net
```

---

## 🐳 Opção 4: Docker + Fly.io

### Passos:

1. **Instalar Fly CLI:**
```bash
# Windows (PowerShell)
iwr https://fly.io/install.ps1 -useb | iex

# Linux/Mac
curl -L https://fly.io/install.sh | sh
```

2. **Login no Fly.io:**
```bash
fly auth login
```

3. **Deploy:**
```bash
cd c:\Users\marci\Downloads\crudapp\crudapp

# Inicializar aplicação Fly
fly launch --name controle-auditorias

# Deploy
fly deploy
```

4. **Abrir aplicação:**
```bash
fly open
```

### URL Pública:
```
https://controle-auditorias.fly.dev
```

---

## ☁️ Opção 5: Heroku (Requer Cartão)

### Pré-requisitos:
- Conta Heroku (eco dynos custam $5/mês)
- Heroku CLI instalado

### Comandos:

```bash
# 1. Login no Heroku
heroku login

# 2. Criar aplicação
heroku create controle-auditorias

# 3. Deploy
git push heroku master

# 4. Abrir aplicação
heroku open

# 5. Ver logs
heroku logs --tail
```

### URL Pública:
```
https://controle-auditorias.herokuapp.com
```

---

## 📊 Comparação de Plataformas

| Plataforma | Custo | Build Time | Cold Start | Limite |
|------------|-------|------------|------------|--------|
| **Railway** | ✅ Gratuito | ~5 min | Rápido | 500h/mês |
| **Render** | ✅ Gratuito | ~10 min | Lento (60s) | Ilimitado |
| **Azure** | 💰 Crédito $200 | ~8 min | Médio | Depende do plano |
| **Fly.io** | ✅ Gratuito | ~7 min | Rápido | 3 apps |
| **Heroku** | 💰 $5/mês | ~6 min | Médio | Depende do plano |

---

## 🔒 Segurança para Produção

### ⚠️ IMPORTANTE: Antes de colocar no ar, considere:

1. **Trocar H2 para PostgreSQL:**
```properties
# application.properties para produção
spring.datasource.url=${DATABASE_URL}
spring.jpa.hibernate.ddl-auto=update
```

2. **Adicionar HTTPS (obrigatório para produção)**
   - Railway, Render e Fly.io já fornecem HTTPS automático

3. **Habilitar CSRF:**
```java
// SecurityConfig.java
.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
```

4. **Senhas fortes para usuários padrão:**
```java
// DataInitializer.java - Usar variáveis de ambiente
String adminPassword = System.getenv("ADMIN_PASSWORD");
```

---

## 🧪 Testar Deploy Local com Docker

```bash
# 1. Build do JAR
./mvnw clean package -DskipTests

# 2. Build da imagem Docker
docker build -t controle-auditorias .

# 3. Executar container
docker run -p 8080:8080 controle-auditorias

# 4. Acessar
http://localhost:8080
```

---

## 📝 Checklist Pré-Deploy

- [x] ✅ Código commitado no GitHub
- [x] ✅ README.md atualizado
- [x] ✅ `server.port=${PORT:8080}` configurado
- [x] ✅ Procfile criado
- [x] ✅ Dockerfile criado
- [x] ✅ render.yaml criado
- [ ] ⏳ Escolher plataforma de deploy
- [ ] ⏳ Criar conta na plataforma
- [ ] ⏳ Conectar repositório GitHub
- [ ] ⏳ Aguardar build e deploy
- [ ] ⏳ Testar aplicação online

---

## 🆘 Troubleshooting

### Build falha no cloud:

**Problema:** `mvnw: Permission denied`

**Solução:** Tornar executável
```bash
git update-index --chmod=+x mvnw
git add mvnw
git commit -m "Fix mvnw permissions"
git push
```

### Aplicação não inicia:

**Problema:** Porta não configurada

**Solução:** Verificar se `application.properties` tem:
```properties
server.port=${PORT:8080}
```

### Timeout no Render:

**Problema:** Render free tier hiberna

**Solução:** Usar serviço de "ping" como:
- https://uptimerobot.com (gratuito)
- Fazer ping a cada 14 minutos

---

## ✅ RECOMENDAÇÃO FINAL

**Para colocar NO AR rapidamente:**

1. Use **Railway.app** (mais fácil e rápido)
2. Faça login com GitHub
3. Clique em "Deploy from GitHub repo"
4. Selecione seu repositório
5. Aguarde 5-10 minutos
6. ✅ **Aplicação no ar com URL pública!**

**URL esperada:** `https://controle-auditorias-production.up.railway.app`

---

## 🎉 Próximos Passos Após Deploy

1. Testar todas as funcionalidades online
2. Compartilhar URL com usuários
3. Monitorar logs e erros
4. Considerar migrar para PostgreSQL
5. Configurar domínio customizado (opcional)

---

**📧 Dúvidas?** Abra uma issue no repositório!

**⭐ Deploy bem-sucedido?** Compartilhe a URL nos comentários!
