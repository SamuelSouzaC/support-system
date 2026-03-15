# Sistema de Suporte Técnico — POC

## Pré-requisitos
- Java 21 (https://adoptium.net/temurin/releases/?version=21)
- Maven (https://maven.apache.org/download.cgi)
- Node.js 20 LTS (https://nodejs.org/en/download)
- Angular CLI: `npm install -g @angular/cli`
- PostgreSQL (Neon: https://neon.tech ou Supabase: https://supabase.com)

## Como rodar

### Backend
```bash
cd backend
# Edite src/main/resources/application.properties com sua URL do banco
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

## Usuário admin inicial
Insira no banco manualmente (senha já encodada para "admin123"):
```sql
INSERT INTO usuarios (nome, email, senha, perfil, ativo)
VALUES ('Admin', 'admin@suporte.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'ADMIN', true);
```

## APIs disponíveis
- POST /api/auth/login
- GET/POST /api/chamados
- PUT /api/chamados/{id}
- GET /api/chamados/metricas
- GET/POST /api/projetos
- GET/POST/PUT/DELETE /api/usuarios
- GET/POST /api/comentarios
