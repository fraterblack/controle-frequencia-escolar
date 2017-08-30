# Gerenciador de Frequência (betha)
Aplicação Java Web apresentado no final do projeto Cidadãos Ligados na Rede (Java Web Avançado)

## Recursos
- Cadastro de Usuários
- Cadastro de Alunos
- Cadastro de Turmas
- Matrículas
- Chamadas
- Relatório de Frequência

## Criação do Banco de Dados
Arquivo SQL para criação das tabelas e inserção de registros inicias, 
[disponíveis aqui](https://github.com/fraterblack/projeto-java-curso/tree/master/resources).

## Acesso ao Sistema
Ao rodar o SQL, 3 usuários iniciais serão criados:
- **Administrado**: Tem acesso a todo o sistema

Login: *admin* e Senha: *12345*
- **Supervisor**: Tem acesso a todo o sistema. Exceto o cadastro de novos usuários

Login: *supervisor* e Senha: *12345*
- **Professor** - Tem acesso restrio a chamadas e relatórios de frequências

Login: *professor* e Senha: *12345*

## Outras informações
Para conexão ao banco de dados, é necessário uma conexão JDBC Datasources, nomeada como **gerenciadorDS**

Algumas informações sobre tecnologias utilizadas:
- Jdk 8
- Jre 8
- Banco de dados MySQL
- WildFly 8.2 (Quando testado no WildFly 10, houve uma exception com a biblioteca dom4j)
- Bibliotecas utilizadas no projeto, estão [disponíveis aqui](https://github.com/fraterblack/projeto-java-curso/tree/master/resources/libs).
