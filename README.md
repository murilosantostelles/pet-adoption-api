# 🐾 Pet Adoption API

> Sistema REST para gerenciamento de pets disponíveis para adoção

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)
![Docker](https://img.shields.io/badge/Docker-ready-blue)

---

## 📖 Sobre

API RESTful desenvolvida para facilitar o cadastro e busca de pets em abrigos de animais. Permite operações completas de CRUD, buscas por múltiplos critérios e validações robustas de dados.

## 🚀 Tecnologias

- **Java 21** - Linguagem de programação
- **Spring Boot 4.0.2** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **PostgreSQL** - Banco de dados relacional
- **Docker** - Containerização
- **Swagger/OpenAPI** - Documentação interativa
- **JUnit 5 + Mockito** - Testes unitários
- **Maven** - Gerenciamento de dependências

## ⚙️ Funcionalidades

- ✅ Cadastro de pets com validações
- ✅ Busca por múltiplos critérios (nome, tipo, idade, peso, raça, localização)
- ✅ Atualização de dados (exceto tipo e gênero)
- ✅ Remoção de registros
- ✅ Documentação via Swagger
- ✅ Transações garantidas com `@Transactional`

## 🏗️ Arquitetura
```
Controller → Service → Repository → Database
    ↓           ↓
   DTO       Entity
```

**Padrão REST com separação de responsabilidades:**
- **Controller**: Endpoints HTTP e conversão DTO ↔ Entity
- **Service**: Regras de negócio e transações
- **Repository**: Acesso a dados via Spring Data JPA
- **DTO**: Controle de entrada/saída da API

## 🔧 Como executar

### Pré-requisitos

- Java 21+
- Docker
- Maven

### Passo a passo

1. **Clone o repositório**
```bash
git clone https://github.com/seuusuario/pet-adoption-api.git
cd pet-adoption-api
```

2. **Inicie o banco de dados**
```bash
docker compose up -d
```

3. **Execute a aplicação**
```bash
./mvnw spring-boot:run
```

4. **Acesse a documentação**
```
http://localhost:8080/swagger-ui.html
```

## 📡 Endpoints principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/pets` | Cadastrar pet |
| `GET` | `/api/pets` | Listar todos |
| `GET` | `/api/pets/{id}` | Buscar por ID |
| `GET` | `/api/pets/search` | Buscar por critérios |
| `PUT` | `/api/pets/{id}` | Atualizar pet |
| `DELETE` | `/api/pets/{id}` | Remover pet |

## 🧪 Testes
```bash
./mvnw test
```

Cobertura de testes inclui:
- Operações CRUD
- Buscas por critérios
- Validações de entrada
- Cenários de erro

## 📚 Documentação

A documentação completa da API está disponível via Swagger UI após iniciar a aplicação:
```
http://localhost:8080/swagger-ui.html
```

## 🛠️ Configuração

### Banco de dados

Configure as credenciais em `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/petadoption
spring.datasource.username=admin
spring.datasource.password=admin123
```

## 📦 Modelo de dados

### Pet
- Nome completo (obrigatório)
- Tipo: CACHORRO | GATO
- Gênero: MACHO | FEMEA
- Idade (0-20 anos)
- Peso (0.5-60kg)
- Raça
- Endereço (rua, cidade, número)

---

**Desenvolvido por [Murilo Santos Telles](https://github.com/murilosantostelles)**
