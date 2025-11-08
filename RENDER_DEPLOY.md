# 🚀 COLOCAR NO AR - Render.com

## ✅ SIGA ESTES PASSOS (10 minutos):

### **PASSO 1: Acesse o Render**
🔗 Abra: **https://render.com/**

### **PASSO 2: Login com GitHub**
- Clique **"Get Started for Free"**
- **"Sign in with GitHub"**
- Autorize o Render

### **PASSO 3: Novo Web Service**
- Dashboard → **"New +"**
- Clique **"Web Service"**

### **PASSO 4: Conectar Repositório**
- Procure: **`controle-de-auditorias-01`**
- Clique **"Connect"**

### **PASSO 5: Configuração Automática**
Render detecta o `render.yaml` automaticamente:
- ✅ Name: `controle-auditorias`
- ✅ Runtime: `Docker`
- ✅ Plan: `Free`

### **PASSO 6: Deploy!**
- Clique **"Create Web Service"**
- ✅ Deploy inicia automaticamente
- ⏱️ Aguarde 8-12 minutos

---

## 🌐 SUA URL PÚBLICA:

```
https://controle-auditorias.onrender.com
```

**Testar:**
- Login: `/login`
- Cadastro: `/cadastro`
- Projetos: `/projects`

---

## ⚠️ IMPORTANTE:

### **Render Free Tier:**
- ✅ **100% Gratuito**
- ⏸️ **Hiberna após 15 min** sem uso
- 🐌 **Primeira request** após hibernar: 60 segundos
- ✅ Depois funciona normal

### **Manter Ativo (Opcional):**
Use **UptimeRobot** (gratuito):
- https://uptimerobot.com
- Faz ping a cada 14 minutos
- Mantém app sempre "acordado"

---

## 📦 ARQUIVOS CONFIGURADOS:

### ✅ `render.yaml`
```yaml
services:
  - type: web
    name: controle-auditorias
    runtime: docker
    plan: free
    healthCheckPath: /login
```

### ✅ `Dockerfile`
Multi-stage build otimizado:
- Stage 1: Build com Maven
- Stage 2: Runtime com JRE apenas

### ✅ `application.properties`
```properties
server.port=${PORT:8080}
```

---

## 🔄 DEPLOY AUTOMÁTICO:

A cada `git push`:
1. Render detecta mudanças
2. Build automático
3. Deploy automático  
4. ✅ App atualizado!

---

## 🐛 PROBLEMAS?

### **Build demora muito:**
- Normal: 8-12 minutos
- Se timeout: Render retenta automaticamente

### **502 Bad Gateway:**
- App ainda está iniciando (aguarde 2 min)

### **Aplicação lenta:**
- Normal após hibernar (free tier)
- Primeira request demora ~60s

---

## ✅ CHECKLIST:

- [x] Código no GitHub
- [x] Dockerfile configurado
- [x] render.yaml pronto
- [x] Porta dinâmica configurada
- [ ] Conta no Render criada
- [ ] Deploy iniciado
- [ ] **APLICAÇÃO NO AR!**

---

## 🎯 AGORA É COM VOCÊ:

1. **Acesse:** https://render.com
2. **Login** com GitHub
3. **"New Web Service"**
4. **Conecte:** `controle-de-auditorias-01`
5. **"Create Web Service"**
6. ⏱️ **Aguarde 10 minutos**
7. 🎉 **NO AR!**

---

**URL Dashboard:** https://dashboard.render.com

**Sua app estará em:** `https://controle-auditorias.onrender.com`

**🚀 BOA SORTE!**
