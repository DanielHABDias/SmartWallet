<h1 align="center">
  <strong>SmartWallet</strong>
</h1>

<p align="center">
  <img src="https://github.com/user-attachments/assets/f26c7d97-39a9-4295-a860-d9ca7b94792b" width="512" height="512" alt="LogoSmartWallet" />
</p>

[![Next.js](https://img.shields.io/badge/Frontend-Next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white)](https://nextjs.org/)
[![NestJS](https://img.shields.io/badge/Backend-NestJS-E0234E?style=for-the-badge&logo=nestjs&logoColor=white)](https://nestjs.com/)
[![Spring Boot](https://img.shields.io/badge/Auth-SpringBoot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/DB-PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![MongoDB](https://img.shields.io/badge/DB-MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)](https://www.mongodb.com/)
[![RabbitMQ](https://img.shields.io/badge/Messaging-RabbitMQ-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)](https://www.rabbitmq.com/)
[![Golang](https://img.shields.io/badge/Worker-Go-00ADD8?style=for-the-badge&logo=go&logoColor=white)](https://golang.org/)

---

## 👤 Autor

**Daniel Dias**  

- GitHub: [https://github.com/DanielHABDias](https://github.com/DanielHABDias)  
- LinkedIn: [https://www.linkedin.com/in/danieldias/](https://www.linkedin.com/in/danieldias/)  
- Portfolio: [https://www.danielhabdias.vercel.app/](https://www.danielhabdias.vercel.app/)  

---

## 🚀 Descrição

**SmartWallet** é um sistema de **Orçamento e Planejamento Financeiro Pessoal**, desenvolvido com **arquitetura moderna full-stack**.  
Ele permite que usuários registrem contas, transações, categorizem despesas e receitas, definam metas financeiras e recebam alertas inteligentes.

O projeto demonstra o uso de:

- **Microserviços** (Spring Boot Auth Service, NestJS Backend, Golang Worker)  
- **Mensageria** (RabbitMQ/Kafka)  
- **Bancos de dados heterogêneos** (PostgreSQL + MongoDB)  
- **Autenticação segura via JWT**  

---

## 🌟 Funcionalidades principais

- Registro e login de usuários com JWT  
- CRUD de **contas**, **transações**, **categorias** e **metas financeiras**  
- Dashboard interativo com gráficos e resumo financeiro  
- Alertas e notificações quando metas ou limites são ultrapassados  
- Logging de atividades e histórico de transações  
- Suporte a múltiplos bancos de dados (relacional e não relacional)  
- Microserviços separados para autenticação e backend principal  
- Mensageria assíncrona para eventos críticos  

---

## 🛠 Tecnologias utilizadas

| Camada / Função                 | Tecnologia e Propósito                                         |
|---------------------------------|---------------------------------------------------------------|
| Front-end                       | **Next.js + TypeScript + Tailwind CSS** → dashboard, gráficos e formulários |
| Auth Service                     | **Spring Boot + JWT** → registro/login, roles, refresh token |
| Backend CRUD                     | **NestJS + TypeORM** → gerenciamento de dados principais     |
| Banco relacional                 | **PostgreSQL** → contas, transações, metas                  |
| Banco não relacional             | **MongoDB** → logs, histórico de alterações, cache          |
| Mensageria                       | **RabbitMQ ou Kafka** → filas para eventos assíncronos      |
| Worker assíncrono                | **Golang** → consome fila, processa alertas e notificações |
| Contêinerização                  | **Docker + Docker Compose** → deploy padronizado de todos os serviços |

---

## 📁 Estrutura de pastas sugerida

```plaintext
SmartWallet/
├─ frontend/ (Next.js)
│  ├─ pages/
│  ├─ components/
│  ├─ services/ (API calls: Auth + Backend NestJS)
│  └─ styles/
├─ auth-service/ (Spring Boot)
│  ├─ controllers/
│  ├─ services/
│  ├─ dtos/
│  ├─ models/
│  └─ config/ (JWT, Security)
├─ api-backend/ (NestJS)
│  ├─ modules/
│  │  ├─ user/
│  │  ├─ account/
│  │  ├─ transaction/
│  │  ├─ category/
│  │  └─ goal/
│  ├─ common/
│  └─ main.ts
├─ golang-worker/
│  ├─ cmd/
│  └─ internal/
├─ docker-compose.yml
└─ README.md
```
---

🔄 **Fluxo do sistema**

1. **Autenticação:** Usuário envia login/register → **Spring Boot Auth Service** gera **JWT**
2. **Front-end:** Armazena **JWT** e envia requisições autenticadas para **NestJS backend**
3. **CRUD NestJS:** Processa **contas, transações, categorias e metas** → **PostgreSQL**
4. **Logs e histórico:** Dados auxiliares armazenados em **MongoDB**
5. **Mensageria:** Eventos críticos (ex.: **transação criada**, **meta atingida**) enviados para **RabbitMQ/Kafka**
6. **Worker Golang:** Consome eventos da fila e envia **alertas/e-mails** assíncronos

---

⚡ **Como executar o projeto**
```bash
# Clone o repositório
git clone "https://github.com/DanielHABDias/SmartWallet.git"

# Configure variáveis de ambiente

# Inicialize bancos e mensageria
docker-compose up

# Inicialize os serviços
./mvnw spring-boot:run       # Spring Boot Auth Service
npm run start:dev            # NestJS backend
npm run dev                  # Next.js frontend
go run ./cmd/worker/main.go  # Golang Worker
```
Acesse o frontend em http://localhost:3000

---

💡 Possíveis melhorias futuras

  - Integração com APIs bancárias externas (Open Banking)
  - Dashboard com gráficos avançados e relatórios exportáveis
  - Mobile app integrado via API

---

SmartWallet combina microserviços, mensageria, bancos heterogêneos e autenticação segura, oferecendo um projeto profissional ideal para demonstrar suas habilidades full-stack e arquitetura moderna.
