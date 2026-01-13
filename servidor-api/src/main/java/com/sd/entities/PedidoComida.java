package com.sd.entities; // Ajustado para o novo pacote

/**
 * Representa um pedido de comida.
 * No Trabalho 3, estende Pedido para fornecer dados via API REST (JSON).
 */
public class PedidoComida extends Pedido {
    // serialVersionUID removido para conformidade com o novo protocolo

    private String nomePrato;
    private String observacoes;

    /**
     * Construtor padrão necessário para desserialização JSON
     */
    public PedidoComida() {
        super();
    }

    /**
     * Construtor do pedido de comida
     * @param id identificador único do pedido
     * @param cliente nome do cliente
     * @param preco preço do prato
     * @param nomePrato nome do prato
     * @param observacoes observações especiais
     */
    public PedidoComida(int id, String cliente, double preco, String nomePrato, String observacoes) {
        super(id, cliente, preco);
        this.nomePrato = nomePrato;
        this.observacoes = observacoes;
    }

    // Getters e Setters (Essenciais para o SparkJava/Gson)

    public String getNomePrato() {
        return nomePrato;
    }

    public void setNomePrato(String nomePrato) {
        this.nomePrato = nomePrato;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "[COMIDA] " + super.toString() + " - Prato: " + nomePrato + 
               (observacoes != null && !observacoes.isEmpty() ? " (Obs: " + observacoes + ")" : "");
    }
}