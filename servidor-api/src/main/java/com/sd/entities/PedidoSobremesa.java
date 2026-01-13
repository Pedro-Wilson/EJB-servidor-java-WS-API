package com.sd.entities; // Ajustado para o novo pacote de entidades

/**
 * Representa um pedido de sobremesa.
 * No Trabalho 3, estende a classe Pedido para fornecer dados específicos via API REST.
 */
public class PedidoSobremesa extends Pedido {
    // serialVersionUID removido pois não usamos mais RMI/Serialização binária

    private String nomeSobremesa;
    private boolean contemLactose;

    /**
     * Construtor padrão necessário para serialização/desserialização JSON
     */
    public PedidoSobremesa() {
        super();
    }

    /**
     * Construtor do pedido de sobremesa
     * @param id identificador único do pedido
     * @param cliente nome do cliente
     * @param preco preço da sobremesa
     * @param nomeSobremesa nome da sobremesa
     * @param contemLactose indica se a sobremesa contém lactose
     */
    public PedidoSobremesa(int id, String cliente, double preco, String nomeSobremesa, boolean contemLactose) {
        super(id, cliente, preco);
        this.nomeSobremesa = nomeSobremesa;
        this.contemLactose = contemLactose;
    }

    // Getters e Setters (Essenciais para o funcionamento da API)

    public String getNomeSobremesa() {
        return nomeSobremesa;
    }

    public void setNomeSobremesa(String nomeSobremesa) {
        this.nomeSobremesa = nomeSobremesa;
    }

    public boolean isContemLactose() {
        return contemLactose;
    }

    public void setContemLactose(boolean contemLactose) {
        this.contemLactose = contemLactose;
    }

    @Override
    public String toString() {
        return "[SOBREMESA] " + super.toString() + " - Sobremesa: " + nomeSobremesa + 
               (contemLactose ? " (⚠️ com lactose)" : " (sem lactose)");
    }
}