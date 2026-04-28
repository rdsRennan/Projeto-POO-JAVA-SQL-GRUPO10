
# Projeto-POO-JAVA-SQL-GRUPO 10

# 📦 Sistema de Locação de Equipamentos

## 📖 Descrição

Este projeto consiste em um sistema de locação de equipamentos de informática, desenvolvido em **Java** com integração ao banco de dados **MySQL**.

O sistema permite gerenciar clientes, itens (notebooks e roteadores) e contratos de locação, aplicando conceitos de **Programação Orientada a Objetos (POO)** e persistência de dados.

---

## 🎯 Objetivo

Facilitar o controle de locações, garantindo organização, integridade dos dados e aplicação de regras de negócio como disponibilidade de itens e controle de contratos.

---

## ⚙️ Funcionalidades

* Cadastro de clientes
* Listagem e remoção de clientes
* Cadastro de itens
* Listagem e remoção de itens
* Criação de contratos de locação
* Listagem de contratos
* Registro de devolução de itens
* Controle de disponibilidade dos equipamentos

---

## 🧱 Tecnologias Utilizadas

* Java
* MySQL
* JDBC (Java Database Connectivity)
* NetBeans

---

## 🏗️ Arquitetura

O sistema segue uma arquitetura em camadas:

* **View:** Interface com o usuário (console)
* **Controller:** Controle do fluxo da aplicação
* **Service:** Regras de negócio
* **DAO:** Acesso aos dados
* **Database:** Banco de dados MySQL

---

## 🗄️ Estrutura do Projeto

```
Projeto/
│
├── src/                # Código-fonte Java
├── database/           # Scripts SQL
├── dist/               # Arquivos compilados
├── mysql-connector/    # Driver do MySQL
└── nbproject/          # Configurações do NetBeans
```

---

## ▶️ Como Executar

### 1. Criar o banco de dados

```sql
CREATE DATABASE locadora;
USE locadora;
```

### 2. Executar o script SQL

* Acesse a pasta `database/`
* Execute o arquivo `.sql` no MySQL

---

### 3. Configurar a conexão

Na classe de conexão:

```java
String url = "jdbc:mysql://localhost:3306/locadora";
String user = "root";
String password = "SUA_SENHA";
```

---

### 4. Executar o projeto

* Abra no NetBeans
* Clique em **Run**

---

## 👥 Atores do Sistema

* **Administrador:** gerencia clientes, itens e contratos
* **Atendente:** realiza locações e devoluções

---

## 📌 Regras de Negócio

* Um item só pode ser alugado se estiver disponível
* Um cliente deve possuir CPF único
* Um item não pode estar em dois contratos ativos
* Ao devolver, o item volta para disponível

---

## 🚀 Melhorias Futuras

* Interface gráfica (GUI)
* Relatórios de locação
* Sistema de multas por atraso
* Validações mais robustas

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.
