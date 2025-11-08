# Sistema de Controle de Auditorias

Sistema completo de gerenciamento de projetos e tarefas com controle de usuários, desenvolvido com Spring Boot 3.3.5 e Spring Security.

## 🚀 Tecnologias

- **Java 21** (OpenJDK)
- **Spring Boot 3.3.5**
- **Spring Security 6.3.4**
- **Spring Data JPA**
- **Hibernate 6.5.3**
- **H2 Database** (em memória)
- **Thymeleaf** (template engine)
- **Maven 3.9.9**
- **Lombok**

## 📋 Funcionalidades

### 🔐 Autenticação e Segurança
- ✅ Sistema de login com Spring Security
- ✅ Cadastro de novos usuários
- ✅ **Login automático após cadastro**
- ✅ Senhas criptografadas com BCrypt
- ✅ Controle de acesso por roles (USER, ADMIN)
- ✅ Validação de username duplicado

### 👥 Gerenciamento de Usuários
- ✅ CRUD completo de usuários
- ✅ Usuários padrão pré-configurados
- ✅ Perfis de acesso diferenciados

### 📁 Gerenciamento de Projetos
- ✅ Criar, editar e excluir projetos
- ✅ Listar todos os projetos
- ✅ Controlar status (ativo/concluído)
- ✅ Datas de início e término

### ✅ Gerenciamento de Tarefas
- ✅ CRUD completo de tarefas
- ✅ Associar tarefas a projetos
- ✅ Atribuir tarefas a usuários
- ✅ Status: PENDING, IN_PROGRESS, COMPLETED

## 🔧 Pré-requisitos

- Java 21 ou superior
- Maven 3.6+ (ou usar o wrapper incluído)
- Git

## 📦 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/Guiaragao10/controle-de-auditorias-01.git
cd controle-de-auditorias-01
```

### 2. Configure o Java (se necessário)

**Windows:**
```powershell
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
```

**Linux/Mac:**
```bash
export JAVA_HOME=/path/to/jdk-21
export PATH=$JAVA_HOME/bin:$PATH
```

### 3. Compile o projeto

**Windows:**
```powershell
.\mvnw.cmd clean package -DskipTests
```

**Linux/Mac:**
```bash
./mvnw clean package -DskipTests
```

## 🚀 Executar a Aplicação

### Opção 1: Usando Maven (Recomendado)

**Windows:**
```powershell
.\start.ps1
```
ou
```powershell
.\mvnw.cmd spring-boot:run
```

**Linux/Mac:**
```bash
./mvnw spring-boot:run
```

### Opção 2: Executando o JAR

```bash
java -jar target/crudapp-0.0.1-SNAPSHOT.jar
```

### Opção 3: Background Job (Windows PowerShell)

```powershell
Start-Job -ScriptBlock { 
    param($jar) 
    & java -jar $jar 
} -ArgumentList (Resolve-Path "target\crudapp-0.0.1-SNAPSHOT.jar").Path
```

A aplicação estará disponível em: **http://localhost:8080**

## 👤 Usuários Padrão

O sistema cria automaticamente dois usuários para teste:

| Username | Senha | Perfil | Email |
|----------|-------|--------|-------|
| `admin` | `admin123` | ADMIN + USER | admin@crudapp.com |
| `user` | `user123` | USER | user@crudapp.com |

## 🌐 Rotas da Aplicação

### Rotas Públicas (sem autenticação)
- `GET /` - Página inicial
- `GET /login` - Página de login
- `GET /cadastro` - Formulário de cadastro
- `POST /cadastro` - Processar cadastro (auto-login)

### Rotas Protegidas (requer autenticação)
- `GET /projects` - Listar projetos
- `GET /projects/new` - Criar novo projeto
- `POST /projects/save` - Salvar projeto
- `GET /projects/edit/{id}` - Editar projeto
- `GET /projects/delete/{id}` - Excluir projeto

- `GET /tasks` - Listar tarefas
- `GET /tasks/new` - Criar nova tarefa
- `POST /tasks/save` - Salvar tarefa
- `GET /tasks/edit/{id}` - Editar tarefa
- `GET /tasks/delete/{id}` - Excluir tarefa

### Ferramentas de Desenvolvimento
- `GET /h2-console` - Console H2 Database
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (vazio)

## 📝 Fluxo de Uso

### 1️⃣ Cadastro de Novo Usuário

1. Acesse: http://localhost:8080/cadastro
2. Preencha:
   - **Username** (mínimo 3 caracteres)
   - **Email** (formato válido)
   - **Senha** (mínimo 6 caracteres)
3. Clique em **"Cadastrar"**
4. ✅ **Você será automaticamente logado e redirecionado para /projects**

### 2️⃣ Login com Usuário Existente

1. Acesse: http://localhost:8080/login
2. Digite username e senha
3. Clique em **"Entrar"**
4. Redirecionamento para página de projetos

### 3️⃣ Gerenciar Projetos e Tarefas

- Após login, você pode criar, editar e excluir projetos
- Associar tarefas aos projetos criados
- Atribuir tarefas a usuários
- Acompanhar status de conclusão

## 🗄️ Banco de Dados

O sistema utiliza **H2 Database** em memória para desenvolvimento:

- ✅ Configuração automática
- ✅ Dados resetados a cada reinicialização
- ✅ Console web disponível em `/h2-console`
- ✅ Tabelas criadas automaticamente (JPA)

### Estrutura das Tabelas

**users**
- id (PK)
- username (unique)
- email (unique)
- password (encrypted)
- roles
- active

**projects**
- id (PK)
- name
- description
- start_date
- end_date
- completed

**tasks**
- id (PK)
- title
- description
- status (ENUM)
- project_id (FK)
- assigned_user_id (FK)

## 🔒 Segurança

### Configurações Implementadas

- ✅ Senhas criptografadas com **BCrypt**
- ✅ Proteção CSRF desabilitada (para APIs)
- ✅ Frame options configurado para `sameOrigin`
- ✅ Controle de acesso baseado em roles
- ✅ Sessão gerenciada pelo Spring Security

### Encoder de Senha

```java
// DelegatingPasswordEncoder - suporta múltiplos algoritmos
PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
```

## 🛠️ Desenvolvimento

### Estrutura do Projeto

```
src/main/java/com/example/crudapp/
├── config/
│   ├── DataInitializer.java      # Inicializa usuários padrão
│   └── SecurityConfig.java        # Configuração Spring Security
├── controller/
│   ├── SecurityController.java   # Login e cadastro
│   ├── ProjectController.java    # CRUD projetos
│   ├── TaskController.java       # CRUD tarefas
│   └── UserController.java       # CRUD usuários
├── model/
│   ├── User.java                 # Entidade usuário
│   ├── Project.java              # Entidade projeto
│   └── Task.java                 # Entidade tarefa
├── repository/
│   ├── UserRepository.java
│   ├── ProjectRepository.java
│   └── TaskRepository.java
└── service/
    ├── UserService.java          # Lógica de negócio + UserDetailsService
    ├── ProjectService.java
    ├── TaskService.java
    └── BaseService.java
```

### Modificar Configurações

Edite `src/main/resources/application.properties`:

```properties
# Porta do servidor
server.port=8080

# Banco de dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## 🐛 Troubleshooting

### Porta 8080 já está em uso

```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -i :8080
kill -9 <PID>
```

### Erro ao compilar

```bash
# Limpar cache Maven
./mvnw clean

# Recompilar
./mvnw clean package -DskipTests
```

### Aplicação fecha sozinha

Use o método `Start-Job` ou execute via Maven Spring Boot plugin:

```powershell
.\mvnw.cmd spring-boot:run
```

## 📚 Documentação Adicional

- [README_CADASTRO.md](README_CADASTRO.md) - Detalhes da funcionalidade de cadastro
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Guiaragao10**
- GitHub: [@Guiaragao10](https://github.com/Guiaragao10)
- Repositório: [controle-de-auditorias-01](https://github.com/Guiaragao10/controle-de-auditorias-01)

---

⭐ **Se este projeto foi útil, considere dar uma estrela!**

📧 **Dúvidas ou sugestões?** Abra uma [issue](https://github.com/Guiaragao10/controle-de-auditorias-01/issues)
