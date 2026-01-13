# EJB-servidor-java-WS-API
# 🍴 API de Gerenciamento de Restaurante - Trabalho 3 (SD)

Este projeto consiste na implementação de um sistema distribuído para gerenciamento de um restaurante, utilizando uma arquitetura de **API REST**. O trabalho substitui a comunicação RMI/Sockets do trabalho anterior por um protocolo de requisição/resposta baseado em HTTP e JSON.

## 🚀 Requisitos Atendidos

- **Comunicação via API:** Implementada utilizando SparkJava (sem sockets ou RMI).
- **Multi-linguagem:** O serviço (servidor) foi desenvolvido em **Java**, enquanto os clientes foram implementados em **Python** e **Node.js**, atendendo ao requisito de pelo menos 2 linguagens diferentes.
- **Interoperabilidade:** Troca de dados padronizada via JSON.

---

## 🏗️ Arquitetura e Módulos

O sistema é composto por três serviços lógicos principais:
1.  **Restaurante:** Gestão de cardápio e criação de pedidos.
2.  **Dispatcher:** Logística de entrega e rastreamento em tempo real.
3.  **Avaliador:** Sistema de feedback e notas para os serviços prestados.



---

## 🛠️ Como Executar o Projeto

### 1. Servidor (Java)
O servidor centraliza a lógica de negócio e expõe os endpoints na porta `8080`.
- Localize o arquivo `ApiServer.java`.
- Execute a classe principal (necessário ter as dependências `spark-core` e `gson`).

### 2. Cliente 1 (Python)
Utiliza a biblioteca `requests` para interagir com a API.
```bash
# Ativar o ambiente virtual
source venv/bin/activate

# Executar o cliente
python cliente.py
