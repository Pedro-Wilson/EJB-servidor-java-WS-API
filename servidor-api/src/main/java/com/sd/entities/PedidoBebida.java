package com.sd.entities; // Atualizado para o novo pacote

/**
 * Representa um pedido de bebida.
 * Estende Pedido para fornecer dados específicos via API REST no Trabalho 3.
 */
public class PedidoBebida extends Pedido {
    // serialVersionUID removido (não usamos mais serialização binária do RMI)

    private String nomeBebida;
    private int volumeMl;

    /**
     * Construtor padrão necessário para bibliotecas de JSON (ex: Gson/Jackson)
     */
    public PedidoBebida() {
        super();
    }

    /**
     * Construtor do pedido de bebida
     * @param id identificador único do pedido
     * @param cliente nome do cliente
     * @param preco preço da bebida
     * @param nomeBebida nome da bebida
     * @param volumeMl volume em mililitros
     */
    public PedidoBebida(int id, String cliente, double preco, String nomeBebida, int volumeMl) {
        super(id, cliente, preco);
        this.nomeBebida = nomeBebida;
        this.volumeMl = volumeMl;
    }

    // Getters e Setters (Fundamentais para a conversão de/para JSON na API)

    public String getNomeBebida() {
        return nomeBebida;
    }

    public void setNomeBebida(String nomeBebida) {
        this.nomeBebida = nomeBebida;
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(int volumeMl) {
        this.volumeMl = volumeMl;
    }

    @Override
    public String toString() {
        return "[BEBIDA] " + super.toString() + " - Bebida: " + nomeBebida + " (" + volumeMl + "ml)";
    }
}