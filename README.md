# 🛒 DSCommerce API - O "Projeto Impossível"

![Java](https://img.shields.io/badge/java-17%2B-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Docker-blue)
![Status](https://img.shields.io/badge/Status-Em%20Construção%20🚧-orange)

## 🎯 Sobre o "Projeto Impossível"
Este repositório não é apenas um CRUD. O **"Projeto Impossível"** é uma iniciativa pessoal com o objetivo de construir uma API REST backend que não apenas funcione, mas que siga estritamente os **padrões industriais e arquiteturais de nível Sênior**. 

A proposta é evoluir iterativamente de um "Monolito Fundacional" robusto para uma arquitetura distribuída escalável. Cada endpoint, DTO e configuração é projetado com foco em segurança, precisão de negócio e clareza de contrato.

## 🚀 Estado Atual: Monolito Fundacional (Fase 1)
Atualmente, o projeto encontra-se em desenvolvimento ativo, estruturando a base do domínio de E-commerce (Produtos, Categorias, Pedidos, Pagamentos e Usuários). 

### ✨ Features Já Implementadas (Padrão Corporativo)
- **Autenticação e Segurança (JWT):** Controle de acesso baseado em perfis (Admin/Client) protegido contra Price Spoofing e interceptação de dados.
- **Documentação Cirúrgica (Swagger/OpenAPI):** Contratos de API limpos, utilizando Segregação de DTOs (`Request` e `Response`), anotações de ocultação (`READ_ONLY`) e injeção nativa de Token Bearer.
- **Tratamento Global de Exceções:** Uso de `@ControllerAdvice` para capturar erros de negócio (404, 403) e validações do Jakarta (422), retornando respostas JSON estruturadas.
- **Precisão Financeira:** Transição completa para `BigDecimal` no domínio de pedidos, garantindo exatidão matemática no cálculo de carrinhos de compras.
- **Arquitetura Moderna:** Uso extensivo de *Java Records* para DTOs, garantindo imutabilidade e performance.

## 🚧 Roadmap (Próximos Passos)
O projeto está em constante evolução. Abaixo estão as tecnologias e práticas que estão na fila de implementação:

- [x] Modelagem de Domínio e Relacionamentos JPA complexos
- [x] Segurança com Spring Security e OAuth2/JWT
- [x] Documentação interativa com Swagger
- [ ] **Testes Automatizados:** Cobertura de Controllers e Services com JUnit 5, Mockito e MockMvc.
- [ ] **Performance (Cache):** Integração com Redis para otimizar a busca de catálogo de produtos.
- [ ] **Mensageria:** Uso de RabbitMQ para processamento assíncrono de e-mails de confirmação de pedido.
- [ ] **CI/CD:** Pipelines de integração e entrega contínua (GitHub Actions).
- [ ] **Cloud & Containers:** Orquestração completa e deploy na nuvem.

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 17+
* **Framework:** Spring Boot 3
* **Banco de Dados:** PostgreSQL (via Docker)
* **Segurança:** Spring Security + JWT
* **Documentação:** Springdoc OpenAPI (Swagger UI)
* **Validação:** Jakarta Bean Validation

## ⚙️ Como Executar o Projeto Localmente

1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/dscommerce.git](https://github.com/seu-usuario/dscommerce.git)
