# 🛒 DSCommerce API - O "Projeto Impossível"

![Java](https://img.shields.io/badge/java-17%2B-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Railway-blue)
![Status](https://img.shields.io/badge/Status-Em%20Produção%20🚀-brightgreen)

## 🎯 Sobre o "Projeto Impossível"
Este repositório não é apenas um CRUD. O **"Projeto Impossível"** é uma iniciativa construída para ser uma API REST backend que segue estritamente os **padrões industriais e arquiteturais de nível Sênior**. 

A proposta é evoluir iterativamente de um "Monolito Fundacional" robusto para uma arquitetura distribuída escalável. Cada endpoint, DTO e configuração foi projetado com foco em segurança, precisão de negócio e clareza de contrato.

---

## ☁️ Ambiente de Produção & Testes

O sistema encontra-se hospedado na nuvem e disponível para testes reais, utilizando a infraestrutura do **Railway**.

**🔗 URL Base (Domínio Público):**
`https://dscommerce-production-63cf.up.railway.app`

### 📥 1. Importando a Collection do Postman
Para facilitar a avaliação da API, disponibilizei uma *Collection* completa do Postman com todas as requisições, variáveis e scripts de automação já configurados.

1. Faça o download do arquivo JSON da coleção que está neste repositório: [Baixar Postman Collection](./postman/DSCommerce.postman_collection.json).
2. Abra o seu Postman.
3. Clique em **Import** no menu superior esquerdo.
4. Arraste e solte o arquivo `DSCommerce.postman_collection.json` baixado.
5. Crie um ambiente (*Environment*) no Postman chamado `DSCommerce - Prod` e adicione a variável `host` com o valor da URL base acima.

### 🔐 2. Credenciais de Teste
O banco de dados de produção já possui usuários semeados (*seeded*) para que você possa testar as diferentes permissões e regras de negócio da API. Utilize as credenciais abaixo no endpoint de Login:

| E-mail (Usuário) | Senha | Perfil (Role) | Permissões |
| :--- | :--- | :--- | :--- |
| `alex@gmail.com` | `123456` | **ADMIN** | Acesso total. Pode cadastrar, editar e deletar produtos, gerenciar categorias e visualizar qualquer pedido. |
| `maria@gmail.com` | `123456` | **CLIENT** | Acesso restrito. Pode visualizar o catálogo, criar o próprio carrinho/pedido e visualizar apenas as suas próprias compras. |

### ⚙️ 3. Fluxo de Autenticação Inteligente
A coleção do Postman fornecida possui automação nativa. Siga este fluxo:
1. Abra a pasta **Auth** e selecione a requisição `POST Login`.
2. No *Body*, preencha com o e-mail e senha de um dos usuários acima.
3. Clique em **Send**.
4. **Magia:** Um script embutido na coleção capturará o `accessToken` gerado na resposta e o salvará automaticamente na variável global `{{token}}`. Você não precisa copiar e colar o token manualmente para testar as outras rotas protegidas (Products, Orders, Users).

---

## 🚀 Estado Atual: Monolito Fundacional (Fase 1)
Atualmente, o projeto consolidou a base do domínio de E-commerce (Produtos, Categorias, Pedidos, Pagamentos e Usuários). 

### ✨ Features Implementadas (Padrão Corporativo)
* **Segurança Profunda (Spring Security + OAuth2/JWT):** Controle de acesso rigoroso baseado em *Roles* (Admin/Client), protegido contra interceptação e *Price Spoofing*.
* **Documentação Cirúrgica (Swagger/OpenAPI):** Contratos de API limpos, utilizando Segregação de DTOs (`Request` e `Response`), e anotações de ocultação (`READ_ONLY`).
* **Tratamento Global de Exceções:** Uso de `@ControllerAdvice` para capturar erros de negócio (404, 403), falhas de integridade referencial e validações do Jakarta (422), retornando respostas JSON padronizadas (RFC 7807).
* **Precisão Financeira:** Transição completa para `BigDecimal` no domínio financeiro, garantindo exatidão matemática no cálculo de fechamento de pedidos.
* **Arquitetura Moderna:** Uso extensivo de *Java Records* para DTOs, garantindo imutabilidade, código limpo e alta performance.

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java 21
* **Framework:** Spring Boot 3.x
* **Banco de Dados:** PostgreSQL (Docker local / Railway Cloud)
* **Mapeamento Objeto-Relacional:** Hibernate / Spring Data JPA
* **Segurança:** Spring Security, OAuth2 Resource Server, JWT
* **Documentação:** Springdoc OpenAPI (Swagger UI)
* **Validação:** Jakarta Bean Validation

---

## 🚧 Roadmap (Próximos Passos)
O desenvolvimento é iterativo. As próximas atualizações focarão em resiliência e performance:

- [x] Modelagem de Domínio e Relacionamentos JPA complexos
- [x] Autenticação e Autorização (JWT/OAuth2)
- [x] Deploy Cloud & Database Hostiing (Railway)
- [ ] **Testes Automatizados:** Cobertura de Controllers e Services com JUnit 5, Mockito e MockMvc.
- [ ] **Performance (Cache):** Integração com Redis para otimizar a busca de catálogo de produtos em massa.
- [ ] **Mensageria:** Implementação de RabbitMQ para processamento assíncrono de e-mails de confirmação de pedido.
- [ ] **CI/CD:** Pipelines de integração e entrega contínua via GitHub Actions.

---

## 💻 Como Executar o Projeto Localmente

1. Clone o repositório:
   ```bash
   git clone [https://github.com/seu-usuario/dscommerce.git](https://github.com/seu-usuario/dscommerce.git)
