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
- Email Suporte: smartwallet.danielhabdias@gmail.com

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
| Front-end                       | **Next.js + TypeScript + MUI + Tailwind CSS** → dashboard, gráficos e formulários |
| Auth Service                     | **Spring Boot + JWT** → registro/login, roles, refresh token |
| Transactions Service             | **NestJS + TypeORM** → gerenciamento de dados principais     |
| Notifications Service            | **Python** → envio de notificações para clientes     |
| Analytics Service                | **Python** → analise de dados utilizando IA    |
| Banco relacional                 | **PostgreSQL** → contas, transações, metas                  |
| Banco não relacional             | **MongoDB** → logs, histórico de alterações, cache          |
| Mensageria                       | **RabbitMQ** → filas para eventos assíncronos      |
| Gateway                          | **Golang** → gerência requisições para os serviços e gerência filas |
| Contêinerização                  | **Docker + Docker Compose** → deploy padronizado de todos os serviços |

---

## 📁 Estrutura de pastas sugerida

```plaintext
SmartWallet/
│
├── Backend
|   ├── gateway/
|   │   ├── main.go
|   │   ├── go.mod
|   │   ├── internal/
|   │   │   ├── router.go
|   │   │   ├── proxy.go
|   │   │   ├── queue.go
|   │   │   └── config.go
|   │   ├── Dockerfile
|   │   └── .env
|   │
|   ├── services/
|   │   ├── auth-service/            # Spring Boot
|   │   ├── transactions-service/    # NestJS
|   │   ├── notifications-service/   # Python (envia notificações via email) 
|   │   └── analytics-service/       # Python (com inteligência artificial)   
|   |
|   └── infra/
|       └── docker-compose.yml       # unificado: gateway + services + db + mq
|
├── frontend/
│   ├── app/                      
│   │   ├── layout.tsx
│   │   ├── page.tsx
│   │   ├── dashboard/
│   │   │   └──  page.tsx
│   │   ├── settings/
│   │   │   └── page.tsx
│   │   ├── login/
│   │   │   └── page.tsx
│   │   └── api/                 
│   │       ├── auth/
│   │       │   └── route.ts
│   │       ├── transactions/
│   │       |   └── route.ts
│   │       ├── notifications/
│   │       |   └── route.ts
│   │       └── analytics/
│   │           └── route.ts
│   │
│   ├── src/
│   │   ├── assets/              # Ícones, imagens, fontes
│   │   ├── styles/              # Estilos globais (Tailwind, variáveis, etc.)
│   │   ├── lib/                 # Configs auxiliares (axios, jwt, etc.)
│   │   ├── utils/               # Funções utilitárias e helpers
│   │   ├── context/             # Contextos globais (AuthContext, ThemeContext)
│   │   ├── hooks/               # Hooks globais (useAuth, useFetch, etc.)
│   │   └── components/          # Atomic Design
│   │       ├── atoms/           # Elementos básicos: botões, inputs, ícones
│   │       ├── molecules/       # Combinações simples: card, input-group
│   │       └── organisms/       # Seções completas: navbar, sidebar, forms
│   │
│   ├── public/                  # Arquivos públicos (favicon, imagens estáticas)
│   ├── .env.local               # Variáveis de ambiente (NEXT_PUBLIC_API_URL, etc.)
│   ├── tailwind.config.ts
│   ├── next.config.ts
│   ├── tsconfig.json
│   └── package.json
|
└── README.md
```
---

# 🔄 Fluxo do Sistema SmartWallet

## 📘 Visão Geral

O **SmartWallet** segue uma arquitetura baseada em **microserviços** com um **gateway em Go** centralizando as requisições.  
Cada serviço é responsável por uma parte específica da aplicação, mantendo o sistema modular e fácil de escalar.

---

## ⚙️ Fluxo do BackEnd

1. **Front-end → Gateway (Go):**  
   O cliente faz requisições HTTP para o **gateway**, que identifica o destino e encaminha para o microserviço correto.

2. **Gateway → Microserviços:**  
   - Requisições comuns são **encaminhadas diretamente** (ex: login, listar transações).  
   - Requisições críticas podem ser **enfileiradas no RabbitMQ** antes de chegar ao serviço.

3. **Auth Service (Spring Boot):**  
   Gerencia **registro, login e autenticação JWT**.

4. **Transactions Service (NestJS):**  
   Cuida de **transações, contas, metas e categorias**, usando **PostgreSQL**.

5. **Notifications Service (Python):**  
   Escuta filas do **RabbitMQ** e envia **notificações simples** (como e-mails ou logs).

6. **Analytics Service (Python):**  
   Consome eventos da fila para gerar **relatórios ou métricas básicas**, armazenando no **MongoDB**.

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
go run ./gateway/main.go     # Golang Worker
```
Acesse o frontend em http://localhost:3000

---

💡 Possíveis melhorias futuras

  - Integração com APIs bancárias externas (Open Banking)
  - Dashboard com gráficos avançados e relatórios exportáveis
  - Mobile app integrado via API

---

SmartWallet combina microserviços, mensageria, bancos heterogêneos e autenticação segura, oferecendo um projeto profissional ideal para demonstrar suas habilidades full-stack e arquitetura moderna.
