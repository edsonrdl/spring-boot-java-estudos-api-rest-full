# Projeto de API RESTful com Spring Boot

Este é um exemplo de projeto de API RESTful desenvolvido com o framework Spring Boot, usando a linguagem Java e um banco de dados MySQL. O projeto também inclui o uso da ferramenta HeidiSQL para gerenciamento do banco de dados.

## Visão Geral

Este projeto tem como objetivo criar uma API RESTful para [descreva brevemente o propósito da API, por exemplo, "gerenciamento de tarefas"]. A API permite que os usuários [explique as principais funcionalidades da API, como criar, ler, atualizar e excluir recursos].

## Tecnologias Utilizadas

- **Spring Boot:** Utilizamos o Spring Boot para criar a estrutura da nossa aplicação web de forma rápida e eficiente.

- **Java:** A linguagem de programação principal do projeto.

- **MySQL:** O banco de dados relacional MySQL é usado para armazenar e gerenciar os dados da aplicação.

- **HeidiSQL:** O HeidiSQL é uma ferramenta de código aberto que usamos para administrar o banco de dados MySQL. Facilita a criação de tabelas, consultas SQL e a visualização de dados.
- Docker compose:** Ferramenta para subir imagens 

## Configuração

Para executar este projeto em sua máquina local, siga estas etapas:

1. Clone o repositório para sua máquina:

git clone  edsonrdl/api-restful-spring-boot-java

ou

utilize o github desktop e adicione para fazer o clone do repositório colocando a url
git repo clone edsonrdl/api-restful-spring-boot-java

2. Configure o banco de dados MySQL:

- Certifique-se de ter o MySQL instalado e em execução em sua máquina.
- Utilize o HeidiSQL ou outra ferramenta de gerenciamento de banco de dados para criar um novo banco de dados e tabelas conforme definido no projeto.

3. Configure as propriedades do banco de dados no arquivo `application.properties` ou `application.yml`:

- spring.datasource.url=jdbc:mysql://localhost:3306/nome-do-banco
  
- spring.datasource.username=${DB_USERNAME_MYSQL_SPRINGBOOT_JAVA}
  
- spring.datasource.password=${DB_PASSWORD_MYSQL}

- A criação de variáveis de ambiente varia dependendo do sistema operacional que você está usando. Abaixo, vou explicar como criar variáveis de ambiente em sistemas Windows.
  
- No Windows:
  
- Abra o "Painel de Controle" e vá para "Sistema e Segurança" (ou "Sistema" em algumas versões).
  
- Clique em "Sistema" para abrir a janela de informações do sistema.
  
- No painel esquerdo, clique em "Configurações avançadas do sistema".
  
- Na guia "Avançado", clique no botão "Variáveis de Ambiente" na parte inferior.
  
- Na seção "Variáveis do Sistema", clique em "Novo" para criar uma nova variável de sistema. Você pode dar um nome à variável (por exemplo, DB_USERNAME_MYSQL_SPRINGBOOT_JAVA) e atribuir o valor apropriado (por exemplo, o nome de usuário do banco de dados).
  
- Repita o processo para criar outra variável de sistema para a senha (por exemplo, DB_PASSWORD_MYSQL).
  
- Depois de criar as variáveis, clique em "OK" para fechar as janelas de configuração.




4. Execute o aplicativo Spring Boot:
 mvn clean install




6. Acesse a API localmente em:

http://localhost:8080

7. Iniciar a aplicação utilizando Docker-Compose:
$ docker-compose up

## Uso

Descreva aqui como usar a API, incluindo exemplos de endpoints, solicitações e respostas.

## Contribuindo

Se desejar contribuir para este projeto, siga estas etapas:

1. Faça um fork do projeto
2. Crie uma nova branch (`git checkout -b feature/nova-feature`)
3. Faça commit de suas alterações (`git commit -am 'Adicione uma nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request


---

Espero que este README o ajude a compreender e configurar o projeto. Se você tiver alguma dúvida  ou problema, não hesite em entrar em contato conosco.
