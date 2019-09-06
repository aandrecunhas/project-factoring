# Factoring Project

Web app desenvolvido em Grails/Groovy para calcular a taxa final e valor a receber de uma empresa que esteja realizando uma antecipação

## Instruções para execução com Docker

* Entrar no diretório da aplicação: ```cd ~/project-factoring.```
* Executar o comando:```docker-compose up -d```
* A aplicação deverá estar rodando em: http://localhost:8081/new-project/

## Instruções do sistema

1) Ao acessar o sistema será exibido a tela inicial, clique no botão "Cadastre sua Empresa"

2) Será exibida uma listagem de empresas cadastradas, clique no botão "Nova Empresa"

3) Preencha os dados do formulário e clique em "Criar"

4) Será exibida uma tela para cálcular os valores a receber, insira os campos necessários e clique em "Calcular"

5) Os valores de desconto e valor a receber serão exibidos na listagem logo abaixo

## Demais Informações do Sistema

As seguintes taxas foram cadastradas no BootStrap do sistema
 * 1,5% para empresas com faturamento mensal entre R$ 0,00 e R$ 49.999,00
 * 1,65% para empresas com faturamento mensal entre R$ 50.000,00 e R$ 100.000,00
 * 1,8% para empresas com faturamento mensal entre  R$ 100.001,00 e R$ R$ 1.000.000,00

## Requisitos Funcionais

**RF1** - O sistema deve cadastrar automaticamente taxas bases predefinidas, sendo que cada taxa deve ter um valor de faturamento minimo e valor de faturamento máximo.

**RF2** - O sistema deve permitir o cadastro de empresas com os seguintes campos: Nome da pessoa, Email, Nome da Empresa, Faturamento Mensal.

**RF2** - O sistema deve automaticamente encontrar uma taxa base predefinida para a empresa durante o seu cadastro, a taxa será definida de acordo com o faturamento mensal, que deve está entre o faturamento minimo e máximo da taxa base encontrada.

**RF3** - O sistema deve permitir o cálculo da taxa final de um titulo de acordo com o valor informado de prazo e valor original do titulo. Esse cálculo da taxa final deve ser taxaBase % a cada 10 dias.

**RF4** - Deverá ser exibido para o usuário as informações de valor a receber e desconto após inserção das informaçes do titulo.

## Modelo de Dados

## Testes Automatizados

Os seguintes testes foram aplicados

* Testes de Classe de Dominio

* Testes de Controller


## Trabalhos Futuros

* Aprimorar as regras de negócio
* Aprimorar testes automatizados
* Aprimorar o front-end
* Incluir novas funcionalidades, como o cadastro de taxa base

