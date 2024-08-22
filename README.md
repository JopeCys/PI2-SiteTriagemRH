# App Triagem RH

## Descrição
Este projeto é um sistema de triagem de currículos desenvolvido em Java utilizando o framework Spring Boot. Ele permite o gerenciamento de vagas de emprego, currículos, candidatos, e funcionários de recursos humanos.

## Funcionalidades
- Cadastro e listagem de vagas de emprego.
- Cadastro e autenticação de candidatos.
- Associação de currículos às vagas de emprego.
- Gerenciamento de funcionários de recursos humanos e gerentes de departamento de vagas.

## Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **MySQL**
- **Thymeleaf**
- **Bootstrap**
- **JUnit e Mockito** para testes
- **k6** para testes de carga

## Arquitetura do Projeto
- **Modelos (`models`)**: Contém as classes que representam as entidades no banco de dados.
- **Repositórios (`repository`)**: Interfaces que lidam com a persistência de dados, estendendo `JpaRepository`.
- **Controladores (`controllers`)**: Classes que lidam com as requisições HTTP e coordenam as respostas.
- **Templates**: Páginas HTML processadas pelo Thymeleaf.

## Pré-requisitos
- **Java 17**
- **MySQL**
- **Maven**

## Configuração do Banco de Dados
Certifique-se de que o MySQL está rodando e que você tem um banco de dados disponível para a aplicação. Configure o `application.properties` para apontar para o banco de dados correto:

## Link para o relatório das Medições dos Testes de Carga
- [1ª e 2ª Medição dos Testes de Carga](https://docs.google.com/document/d/1YGqVkOvFD8hMST3ecu_SIfiIhmUaqhdp0bIca86ZSsM/edit?usp=sharing)
