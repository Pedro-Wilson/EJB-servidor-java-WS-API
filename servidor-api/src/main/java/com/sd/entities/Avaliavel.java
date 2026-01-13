package com.sd.entities; // Pacote atualizado para o Trabalho 3

/**
 * Interface para avaliação de pedidos.
 * Define o contrato para que Pedidos possam ser avaliados no sistema de delivery.
 */
public interface Avaliavel {
    /**
     * Avalia o pedido com uma nota e comentário
     * @param nota nota da avaliação (0-5)
     * @param comentario comentário do cliente
     */
    void avaliar(int nota, String comentario);

    /**
     * Obtém a nota da avaliação
     * @return nota do pedido
     */
    int getNota();

    /**
     * Obtém o comentário da avaliação
     * @return comentário do pedido
     */
    String getComentario();
}