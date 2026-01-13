package com.sd.server;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.sd.entities.*;
import java.util.HashMap;
import java.util.Map;

public class ApiServer {
    private static Gson gson = new Gson();
    
    private static RestauranteImpl restaurante = new RestauranteImpl();
    private static AvaliadorImpl avaliador = new AvaliadorImpl();
    private static DispatcherImpl dispatcher = new DispatcherImpl();

    public static void main(String[] args) {
        port(8080);

        System.out.println("--------------------------------------------");
        System.out.println("SERVIDOR RESTAURANTE API INICIADO NA PORTA 8080");
        System.out.println("--------------------------------------------");

        // 🍎 Rotas de Cardápio
        get("/cardapio/comidas", (req, res) -> restaurante.obterCardapioComidas(), gson::toJson);
        get("/cardapio/bebidas", (req, res) -> restaurante.obterCardapioBebidas(), gson::toJson);
        get("/cardapio/sobremesas", (req, res) -> restaurante.obterCardapioSobremesas(), gson::toJson);

        // 🛒 Rota de Pedido
        post("/pedir/comida", (req, res) -> {
            String cliente = req.queryParams("cliente");
            int idPrato = Integer.parseInt(req.queryParams("id"));
            String obs = req.queryParamOrDefault("obs", "Sem observações");
            
            PedidoComida pedido = restaurante.pedirComidaPorId(cliente, idPrato, obs);
            
            if (pedido != null) {
                dispatcher.despacharPedido(pedido.getIdPedido(), "Endereço Padrão", "Entregador 01");
                return pedido;
            }
            
            res.status(404);
            Map<String, String> erro = new HashMap<>();
            erro.put("erro", "Prato não encontrado");
            return erro;
        }, gson::toJson);

        // 📍 Rota de Status (Corrigida para retornar JSON)
        get("/pedido/:id/status", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            String statusRestaurante = restaurante.consultarPedido(id);
            String statusLogistica = dispatcher.rastrearPedido(id);
            
            Map<String, String> statusMap = new HashMap<>();
            statusMap.put("idPedido", String.valueOf(id));
            statusMap.put("statusRestaurante", statusRestaurante);
            statusMap.put("statusLogistica", statusLogistica);
            
            return statusMap;
        }, gson::toJson);

        // ⭐ Rota de Avaliação
        post("/avaliar/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            int nota = Integer.parseInt(req.queryParams("nota"));
            String comentario = req.queryParams("comentario");
            
            boolean sucesso = avaliador.avaliarPedido(id, nota, comentario);
            
            Map<String, String> resposta = new HashMap<>();
            resposta.put("mensagem", sucesso ? "Avaliação registrada!" : "Erro ao avaliar.");
            return resposta;
        }, gson::toJson);

        // 🛠️ Filtro Global de Resposta
        after((req, res) -> {
            res.type("application/json");
        });
    }
}