package com.sd.server; // 1. CORREÇÃO: Package ajustado

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementação da lógica de despacho e rastreamento.
 * Removido: herança de UnicastRemoteObject e DispatcherRemote.
 */
public class DispatcherImpl { // 2. CORREÇÃO: Classe simples (POJO)

    // Armazenamento de rastreamento (ID Pedido -> Status da Entrega)
    private Map<Integer, String> rastreamento;
    private Map<Integer, String> detalhesEntrega;

    public DispatcherImpl() {
        this.rastreamento = new ConcurrentHashMap<>();
        this.detalhesEntrega = new ConcurrentHashMap<>();
    }

    /**
     * Inicia o processo de despacho de um pedido.
     */
    public boolean despacharPedido(int idPedido, String endereco, String entregador) {
        rastreamento.put(idPedido, "Em preparação para saída");
        detalhesEntrega.put(idPedido, "Entregador: " + entregador + " | Destino: " + endereco);
        
        System.out.println("[DISPATCHER] Pedido #" + idPedido + " despachado para " + entregador);
        
        // Simulação de mudança de status automática
        simularMovimentacao(idPedido);
        
        return true;
    }

    /**
     * Consulta o status atual da entrega.
     */
    public String rastrearPedido(int idPedido) {
        return rastreamento.getOrDefault(idPedido, "Pedido não encontrado no sistema de logística");
    }

    /**
     * Obtém informações detalhadas sobre quem entrega e para onde.
     */
    public String obterDetalhesRastreamento(int idPedido) {
        if (!detalhesEntrega.containsKey(idPedido)) {
            return "Sem detalhes para o pedido #" + idPedido;
        }
        return "Status: " + rastrearPedido(idPedido) + " | " + detalhesEntrega.get(idPedido);
    }

    /**
     * Registra manualmente que o pedido foi entregue.
     */
    public boolean registrarEntrega(int idPedido, String horario) {
        if (rastreamento.containsKey(idPedido)) {
            rastreamento.put(idPedido, "Entregue em: " + horario);
            return true;
        }
        return false;
    }

    public int obterQuantidadePedidosPendentes() {
        int pendentes = 0;
        for (String status : rastreamento.values()) {
            if (!status.startsWith("Entregue")) {
                pendentes++;
            }
        }
        return pendentes;
    }

    /**
     * Simula a logística mudando o status após alguns segundos.
     */
    private void simularMovimentacao(int idPedido) {
        new Thread(() -> {
            try {
                Thread.sleep(10000); // 10 segundos
                rastreamento.put(idPedido, "Saiu para entrega");
                Thread.sleep(15000); // +15 segundos
                rastreamento.put(idPedido, "Entregue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}