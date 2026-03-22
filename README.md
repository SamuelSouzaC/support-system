# 🎯 Sistema de Suporte Técnico — POC

Sistema web para gerenciamento de chamados de suporte técnico, com autenticação JWT, controle de acesso por perfil e dashboard de métricas.

---

## 🌐 Acesso Online

| Serviço | URL |
|---------|-----|
| **Frontend** | https://frontend-lovat-pi-36.vercel.app |
| **Backend** | https://support-system-ipd6.onrender.com |

> ⚠️ O backend roda no plano gratuito do Render. Após 15 minutos sem uso ele "dorme" e pode demorar até 50 segundos para responder na primeira requisição.

---

## 🔑 Usuário Padrão

| Campo | Valor |
|-------|-------|
| Email | admin@suporte.com |
| Senha | admin123 |
| Perfil | ADMIN  |
---
Link de acesso ao sistema: https://frontend-lovat-pi-36.vercel.app

## 🏗️ Tecnologias

### Frontend
- Angular 20
- TypeScript
- JWT (via interceptor HTTP)
- Deploy: Vercel

### Backend
- Java 21
- Spring Boot 3.2
- Spring Security + JWT
- Spring Data JPA
- Deploy: Render (Docker)

### Banco de Dados
- PostgreSQL (Neon — cloud)

---

## 📦 Módulos

- **Autenticação** — login/logout com JWT e controle de acesso por perfil
- **Usuários** — criação e gestão de usuários com perfis (Acionamento, Analista, Admin)
- **Projetos** — criação de projetos pelo Admin
- **Chamados** — abertura, edição, atribuição, alteração de status e comentários
- **Dashboard** — métricas por perfil (chamados abertos, em andamento, tempo médio de resolução)

---

## 🗂️ Estrutura do Projeto

```
support-system/
├── backend/                          # Spring Boot
│   ├── Dockerfile
│   ├── pom.xml
│   └── src/main/java/com/suporte/system/
│       ├── controller/               # AuthController, ChamadoController...
│       ├── service/                  # AuthService, ChamadoService...
│       ├── repository/               # Repositórios JPA
│       ├── model/                    # Entidades (Usuario, Chamado, Projeto...)
│       ├── security/                 # JWT, Filtros, SecurityConfig
│       └── dto/                      # DTOs de entrada/saída
├── frontend/                         # Angular 20
│   └── src/app/
│       ├── pages/                    # login, dashboard, chamados, projetos, usuarios
│       ├── services/                 # auth, chamado, projeto, usuario
│       ├── guards/                   # authGuard, jwtInterceptor
│       └── models/                   # interfaces TypeScript
└── docs/
```

---

## 🚀 Como Rodar Localmente

### Pré-requisitos
- Java 21 → https://adoptium.net/temurin/releases/?version=21
- Maven → https://maven.apache.org/download.cgi
- Node.js 20 LTS → https://nodejs.org/en/download
- Angular CLI: `npm install -g @angular/cli`

### Backend

```bash
cd backend
# Configure o banco em src/main/resources/application.properties
mvn spring-boot:run
```

Rodará em: http://localhost:8080

### Frontend

```bash
cd frontend
npm install
ng serve
```

Rodará em: http://localhost:4200

---

## 🗄️ Banco de Dados

O banco está hospedado no **Neon** (PostgreSQL cloud).

Para inserir o usuário admin manualmente:

```sql
INSERT INTO usuarios (nome, email, senha, perfil, ativo, criado_em)
VALUES ('Admin', 'admin@suporte.com', '$2a$10$HASH_GERADO_PELO_BACKEND', 'ADMIN', true, NOW());
```

> Para gerar o hash correto acesse: `https://support-system-ipd6.onrender.com/api/auth/hash?senha=SUA_SENHA`

---

## 🔌 APIs Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /api/auth/login | Login |
| GET | /api/chamados | Listar chamados |
| POST | /api/chamados | Criar chamado |
| PUT | /api/chamados/{id} | Atualizar chamado |
| GET | /api/chamados/metricas | Métricas do dashboard |
| GET | /api/projetos | Listar projetos |
| POST | /api/projetos | Criar projeto |
| GET | /api/usuarios | Listar usuários (Admin) |
| POST | /api/usuarios | Criar usuário (Admin) |
| GET | /api/comentarios/chamado/{id} | Comentários do chamado |
| POST | /api/comentarios | Adicionar comentário |

---

## 🧪 Testes

```bash
# Backend
cd backend
mvn test

# Frontend
cd frontend
ng test
```

---

## ☁️ Deploy

### Frontend (Vercel)
```bash
cd frontend
vercel --prod --force
```

### Backend (Render)
O deploy é automático via GitHub. Qualquer push na branch `main` dispara um novo deploy no Render.

### Variáveis de Ambiente (Render)

| Variável | Descrição |
|----------|-----------|
| `SPRING_DATASOURCE_URL` | URL do banco PostgreSQL |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco |
| `JWT_SECRET` | Chave secreta para geração de tokens JWT |
