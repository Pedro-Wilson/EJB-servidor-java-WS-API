package com.sd.server; // 1. CORREÇÃO: Package ajustado para com.sd.server

import java.util.*;

/**
 * Implementação da lógica de avaliação de pedidos.
 * Removidas as heranças de UnicastRemoteObject e interfaces RMI.
 */
public class AvaliadorImpl { // 3. CORREÇÃO: Removido extends e implements RMI

    private Map<Integer, Integer> notas;
    private Map<Integer, String> comentarios;

    public AvaliadorImpl() {
        this.notas = new HashMap<>();
        this.comentarios = new HashMap<>();
    }

    /**
     * Registra a avaliação de um pedido.
     * Removido @Override e throws RemoteException.
     */
    public boolean avaliarPedido(int idPedido, int nota, String comentario) {
        if (nota < 1 || nota > 5) {
            return false;
        }
        
        notas.put(idPedido, nota);
        comentarios.put(idPedido, comentario);
        
        System.out.println("[AVALIADOR] Nova avaliação para pedido #" + idPedido + ": Nota " + nota);
        return true;
    }

    /**
     * Retorna o status/detalhe de uma avaliação específica.
     */
    public String obterAvaliacao(int idPedido) {
        if (!notas.containsKey(idPedido)) {
            return "Pedido não avaliado.";
        }
        return "Nota: " + notas.get(idPedido) + " | Comentário: " + comentarios.get(idPedido);
    }

    /**
     * Calcula a média de todas as avaliações registradas.
     */
    public double obterMediaAvaliacoes() {
        if (notas.isEmpty()) return 0.0;
        
        double soma = 0;
        for (int nota : notas.values()) {
            soma += nota;
        }
        return soma / notas.size();
    }

    public int obterQuantidadeAvaliacoes() {
        return notas.size();
    }

    public String obterMelhorComentario() {
        if (notas.isEmpty()) return "Nenhuma avaliação disponível.";
        
        int melhorId = -1;
        int maiorNota = -1;
        
        for (Map.Entry<Integer, Integer> entry : notas.entrySet()) {
            if (entry.getValue() > maiorNota) {
                maiorNota = entry.getValue();
                melhorId = entry.getKey();
            }
        }
        
        return comentarios.get(melhorId);
    }

    public boolean pedidoJaAvaliado(int idPedido) {
        return notas.containsKey(idPedido);
    }
}