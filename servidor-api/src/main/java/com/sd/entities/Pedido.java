package com.sd.entities; // Atualizado para o novo pacote

/**
 * Classe abstrata que representa um pedido genérico.
 * Base para os tipos específicos no Trabalho 3.
 * Removido Serializable para uso de representação externa JSON.
 */
public abstract class Pedido implements Avaliavel {
    // serialVersionUID removido (Conceito de RMI/Serialização Java)

    protected int idPedido;
    protected String nomeCliente;
    protected double preco;
    protected int nota;
    protected String comentario;
    protected String status; 
    private long tempoCriacao;

    /**
     * Construtor padrão (vazio) necessário para frameworks de JSON
     */
    public Pedido() {
        this.tempoCriacao = System.currentTimeMillis();
    }

    /**
     * Construtor completo
     */
    public Pedido(int id, String cliente, double preco) {
        this.idPedido = id;
        this.nomeCliente = cliente;
        this.preco = preco;
        this.nota = 0;
        this.comentario = "";
        this.tempoCriacao = System.currentTimeMillis();
    }

    // --- Getters e Setters ---
    // Essenciais para que os dados da classe pai apareçam no JSON

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getNota() { return nota; }
    public void setNota(int nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public long getTempoCriacao() { return tempoCriacao; }
    public void setTempoCriacao(long tempoCriacao) { this.tempoCriacao = tempoCriacao; }

    @Override
    public void avaliar(int nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Pedido #" + idPedido + " (Cliente: " + nomeCliente + 
               ", Preço: R$" + String.format("%.2f", preco) + 
               ", Nota: " + nota + "★)";
    }
}