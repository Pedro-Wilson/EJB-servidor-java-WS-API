package com.sd.server;

import com.sd.entities.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RestauranteImpl {
    
    private static AtomicInteger pedidoCounter = new AtomicInteger(1000);
    private Map<Integer, Object> pedidos;
    
    private Bebida[] cardapioBebidas;
    private Sobremesa[] cardapioSobremesas;
    private String[] cardapioComidas;
    
    public RestauranteImpl() {
        this.pedidos = new HashMap<>();
        inicializarCardapio();
    }
    
    private void inicializarCardapio() {
        this.cardapioComidas = new String[] {
            "Moqueca de Peixe (R$35,00)",
            "Feijoada Completa (R$38,00)",
            "Bife à Parmegiana (R$42,00)",
            "Lasanha à Bolonhesa (R$28,00)"
        };
        
        this.cardapioBebidas = new Bebida[] {
            new Bebida("Refrigerante Cola", "Gelado", 5.00, 350),
            new Bebida("Suco Natural", "Laranja", 7.50, 300)
        };
        
        this.cardapioSobremesas = new Sobremesa[] {
            new Sobremesa("Pudim", "Leite Condensado", 8.00, true),
            new Sobremesa("Mousse", "Maracujá", 11.00, false)
        };
    }
    
    // --- MÉTODOS DE PEDIDO ---

    /**
     * NOVO MÉTODO: Agora utiliza o extrairPreco para processar o ID da comida
     */
    public PedidoComida pedirComidaPorId(String nomeCliente, int idPrato, String observacao) {
        if (idPrato < 0 || idPrato >= cardapioComidas.length) {
            return null;
        }

        String descricaoPrato = cardapioComidas[idPrato];
        
        // AQUI o método extrairPreco é utilizado, removendo o warning!
        double preco = extrairPreco(descricaoPrato);
        
        int idPedido = gerarIdPedido();
        PedidoComida pedido = new PedidoComida(idPedido, nomeCliente, preco, descricaoPrato, observacao);
        
        pedido.setStatus("Recebido");
        this.pedidos.put(idPedido, pedido);
        iniciarTimerPedido(pedido);
        
        return pedido;
    }

    public PedidoBebida pedirBebidaPorId(String nomeCliente, int idBebida) {
        if (idBebida < 0 || idBebida >= cardapioBebidas.length) return null;
        
        Bebida b = cardapioBebidas[idBebida];
        PedidoBebida p = new PedidoBebida(gerarIdPedido(), nomeCliente, b.getPreco(), b.getNome(), b.getVolumeML());
        pedidos.put(p.getIdPedido(), p);
        iniciarTimerPedido(p);
        return p;
    }

    public PedidoSobremesa pedirSobremesaPorId(String nomeCliente, int idSobremesa) {
        if (idSobremesa < 0 || idSobremesa >= cardapioSobremesas.length) return null;
        
        Sobremesa s = cardapioSobremesas[idSobremesa];
        PedidoSobremesa p = new PedidoSobremesa(gerarIdPedido(), nomeCliente, s.getPreco(), s.getNome(), s.isTemLactose());
        pedidos.put(p.getIdPedido(), p);
        iniciarTimerPedido(p);
        return p;
    }

    // --- MÉTODOS DE CONSULTA ---

    public Bebida[] obterCardapioBebidas() { return this.cardapioBebidas; }
    public String[] obterCardapioComidas() { return this.cardapioComidas; }
    public Sobremesa[] obterCardapioSobremesas() { return this.cardapioSobremesas; }

    public String consultarPedido(int idPedido) {
        Object p = pedidos.get(idPedido);
        if (p instanceof Pedido) return "Status: " + ((Pedido) p).getStatus();
        return "Não encontrado";
    }

    // --- LÓGICA INTERNA ---

    private synchronized int gerarIdPedido() {
        return pedidoCounter.getAndIncrement();
    }

    private void iniciarTimerPedido(Pedido pedido) {
        new Thread(() -> {
            try {
                Thread.sleep(5000); 
                pedido.setStatus("Finalizado");
                Thread.sleep(3000);
                pedido.setStatus("A caminho");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }).start();
    }
    
    private double extrairPreco(String descricao) {
        try {
            // Regex para capturar o valor após "R$"
            String precoStr = descricao.replaceAll(".*\\(R\\$(\\d+,\\d{2})\\).*", "$1");
            return Double.parseDouble(precoStr.replace(",", "."));
        } catch (Exception e) { 
            return 0.0; 
        }
    }
}