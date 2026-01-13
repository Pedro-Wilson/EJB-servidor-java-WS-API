package com.sd;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        // Define a porta onde o servidor vai rodar
        port(8080);

        // Exemplo de rota GET para teste (http://localhost:8080/status)
        get("/status", (req, res) -> "Servidor do Trabalho 3 operando via API!");

        // Exemplo de rota POST para receber dados (Reimplementação do Trabalho 2)
        post("/servico", (req, res) -> {
            String corpo = req.body();
            System.out.println("Recebido do cliente: " + corpo);
            return "Processado com sucesso!";
        });

        System.out.println("API iniciada em http://localhost:8080");
    }
}