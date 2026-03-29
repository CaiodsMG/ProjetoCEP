# Projeto CEP

API REST feita com Spring Boot para gerenciar clientes e preencher o endereço automaticamente a partir do CEP informado, usando a API do ViaCEP.

## Sobre o projeto

Esse projeto foi criado como prática de CRUD com Java e Spring. A ideia principal é simples: ao cadastrar um cliente, basta informar o nome e o CEP. O sistema consulta o ViaCEP, busca os dados do endereço e associa essas informações ao cliente.

Os dados ficam em um banco H2 em memória, o que facilita bastante para testar localmente sem precisar configurar um banco externo.

## O que a aplicação faz

- cadastra clientes
- lista todos os clientes
- busca cliente por ID
- atualiza nome e CEP de um cliente
- remove clientes
- busca clientes por nome
- busca clientes por UF
- busca clientes por cidade
- consulta o ViaCEP automaticamente quando precisa montar o endereço
- reutiliza endereços já salvos no banco para evitar consultas desnecessárias
- disponibiliza documentação da API com Swagger

## Tecnologias usadas

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- OpenFeign
- H2 Database
- Swagger / OpenAPI
- Maven

## Estrutura do projeto

```text
src/main/java/com/cep/projeto
|- Controller
|- Services
|- Repositories
|- Entities
|- Model
|- dtos
|- Exceptions
|- Configurations
```

## Como usar

Para testar o projeto, o primeiro passo é clonar este repositório para a sua máquina.

Você pode fazer isso pelo próprio GitHub, copiando o link do projeto na opção `Code`, e depois executando o comando abaixo no terminal:

```bash
git clone https://github.com/CaiodsMG/ProjetoCEP.git
```

Depois do clone, basta abrir a pasta do projeto na IDE de sua preferência, como IntelliJ ou VS Code. Com o projeto aberto, já é possível visualizar a estrutura, as classes principais e executar a aplicação localmente.

O sistema sobe por padrão em:

```text
http://localhost:8080
```

## Acessos úteis

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
- H2 Console: `http://localhost:8080/h2-console`

Para acessar o H2 Console:

- JDBC URL: `jdbc:h2:mem:meubanco;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE`
- User: `sa`
- Password: deixe em branco

## Dados iniciais

Quando a aplicação inicia, o arquivo `data.sql` insere alguns registros para facilitar os testes.

Clientes carregados inicialmente:

- João Silva
- Maria Souza
- Carlos Pereira

## Endpoints

### Listar clientes

```http
GET /clientes/listarTodos
```

### Buscar cliente por ID

```http
GET /clientes/buscarPor/{id}
```

### Buscar por nome

```http
GET /clientes/buscarPorNome?nome=Caio
```

### Buscar por UF

```http
GET /clientes/buscarPorUf?uf=DF
```

### Buscar por cidade

```http
GET /clientes/buscarPorCidade?localidade=Brasília
```

### Cadastrar cliente

```http
POST /clientes/Cadastrar
Content-Type: application/json
```

Exemplo:

```json
{
  "nome": "Caio Magalhães",
  "cep": "71931000"
}
```

### Atualizar cliente

```http
PUT /clientes/AtualizarPor/{id}
Content-Type: application/json
```

Exemplo:

```json
{
  "nome": "Caio Magalhães Atualizado",
  "cep": "01001000"
}
```

### Deletar cliente

```http
DELETE /clientes/DeletarPor/{id}
```

## Exemplo de resposta

```json
{
  "id": 1,
  "nome": "Caio Magalhães",
  "Endereço": {
    "cep": "71931000",
    "bairro": "Águas Claras",
    "localidade": "Brasília",
    "uf": "DF",
    "ddd": "61"
  }
}
```

## Regras da aplicação

- o cliente é salvo com nome e CEP
- se o CEP ainda não existir no banco local, a aplicação consulta o ViaCEP
- se o endereço já tiver sido consultado antes, ele é reaproveitado
- ao atualizar um cliente, também é possível trocar o CEP

## Tratamento de erros

O projeto trata alguns cenários comuns, como:

- cliente não encontrado
- CEP inválido ou não localizado
- parâmetro de rota com tipo incorreto
- erro de validação nos dados enviados
