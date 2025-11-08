# CrudApp - Sistema de Gerenciamento de Projetos e Tarefas

## 🚀 Funcionalidades Implementadas

### ✅ Cadastro de Usuário Atualizado
- **Rota:** `POST /cadastro`
- **Funcionalidade:** Ao se cadastrar, o sistema:
  1. ✅ Criptografa a senha automaticamente usando BCrypt
  2. ✅ Salva o usuário no banco de dados
  3. ✅ Faz login automático após o cadastro
  4. ✅ Redireciona para a página de projetos (`/projects`)
  
### 👤 Usuários Padrão (Criados Automaticamente)

| Usuário | Senha | Perfil | Email |
|---------|-------|--------|-------|
| `admin` | `admin123` | ROLE_USER, ROLE_ADMIN | admin@crudapp.com |
| `user` | `user123` | ROLE_USER | user@crudapp.com |

## 🔧 Requisitos

- **Java 21** (OpenJDK Temurin instalado em: `C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot`)
- **Maven** (via wrapper incluído no projeto)

## 📦 Como Executar

### Opção 1: Script Batch (Windows)
```cmd
start.bat
```

### Opção 2: Script PowerShell
```powershell
.\start.ps1
```

### Opção 3: Comando Manual
```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
java -jar target\crudapp-0.0.1-SNAPSHOT.jar
```

### Opção 4: Maven Wrapper
```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
.\mvnw.cmd spring-boot:run
```

## 🌐 Acessar a Aplicação

- **Homepage:** http://localhost:8080
- **Login:** http://localhost:8080/login
- **Cadastro:** http://localhost:8080/cadastro
- **Projetos:** http://localhost:8080/projects (requer autenticação)
- **Tarefas:** http://localhost:8080/tasks (requer autenticação)
- **Console H2:** http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (vazio)

## 📝 Fluxo de Cadastro Atualizado

1. Usuário acessa `/cadastro`
2. Preenche: **Username**, **Email** e **Senha**
3. Clica em "Cadastrar"
4. Sistema:
   - Valida se o username já existe
   - Criptografa a senha com BCrypt
   - Salva no banco de dados H2 (em memória)
   - Faz login automático
   - Redireciona para `/projects`

## 🔐 Segurança

- **Senhas:** Criptografadas automaticamente com BCrypt
- **Autenticação:** Spring Security com sessão
- **Rotas Públicas:** `/`, `/login`, `/cadastro`, `/users/save`, `/h2-console/**`
- **Rotas Protegidas:** `/projects/**`, `/tasks/**`, `/api/**`

## 🗄️ Banco de Dados

- **Tipo:** H2 Database (em memória)
- **Configuração:** `spring.jpa.hibernate.ddl-auto=create-drop`
- **Nota:** Dados são perdidos ao reiniciar a aplicação

## 🔄 Recompilar o Projeto

```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
.\mvnw.cmd clean package -DskipTests
```

## 📂 Estrutura do Projeto

```
crudapp/
├── src/main/java/com/example/crudapp/
│   ├── config/
│   │   ├── DataInitializer.java      # Cria usuários padrão
│   │   └── SecurityConfig.java        # Configuração de segurança
│   ├── controller/
│   │   ├── SecurityController.java    # ✅ Login, Cadastro (POST /cadastro)
│   │   ├── ProjectController.java
│   │   ├── TaskController.java
│   │   └── UserController.java
│   ├── model/
│   │   ├── User.java
│   │   ├── Project.java
│   │   └── Task.java
│   ├── repository/
│   │   ├── UserRepository.java
│   │   ├── ProjectRepository.java
│   │   └── TaskRepository.java
│   └── service/
│       ├── UserService.java           # Criptografia de senha automática
│       ├── ProjectService.java
│       └── TaskService.java
└── src/main/resources/
    ├── application.properties
    └── templates/
        ├── cadastro.html              # ✅ Formulário atualizado
        ├── login.html
        ├── projects/
        └── tasks/
```

## ✨ Melhorias Implementadas

1. ✅ **Cadastro com login automático** - Usuário não precisa fazer login após cadastrar
2. ✅ **Validação de username duplicado** - Impede cadastro com username existente
3. ✅ **Mensagens de feedback** - Exibe sucesso/erro no cadastro
4. ✅ **Interface melhorada** - CSS responsivo na página de cadastro
5. ✅ **Senha criptografada** - BCrypt automático no UserService
6. ✅ **Redirecionamento inteligente** - Após cadastro vai direto para /projects

## 🐛 Troubleshooting

### Aplicação não inicia
- Verifique se a porta 8080 está livre
- Confirme que Java 21 está instalado
- Rode: `java -version` para verificar

### Erro ao compilar
- Pare processos Java: `Get-Process java | Stop-Process -Force`
- Recompile: `.\mvnw.cmd clean package -DskipTests`

### Login não funciona
- Use um dos usuários padrão: `admin / admin123` ou `user / user123`
- Ou cadastre um novo usuário em `/cadastro`

---

**✅ Sistema pronto para uso! Cadastre-se e comece a gerenciar projetos e tarefas!**
