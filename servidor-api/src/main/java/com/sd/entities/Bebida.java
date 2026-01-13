package com.sd.entities; // Pacote atualizado para o Trabalho 3

/**
 * Representa uma bebida no cardápio do restaurante.
 * No Trabalho 3, serve como modelo para os dados enviados via API.
 */
public class Bebida {
    // serialVersionUID removido (não usamos mais serialização RMI)

    private String nome;
    private String descricao;
    private double preco;
    private int volumeML;

    /**
     * Construtor vazio (obrigatório para bibliotecas JSON)
     */
    public Bebida() {}

    /**
     * Construtor da bebida
     */
    public Bebida(String nome, String descricao, double preco, int volumeML) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.volumeML = volumeML;
    }

    // --- Getters e Setters (Essenciais para a API) ---

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getVolumeML() {
        return volumeML;
    }

    public void setVolumeML(int volumeML) {
        this.volumeML = volumeML;
    }

    @Override
    public String toString() {
        return nome + " - " + descricao + " (R$" + String.format("%.2f", preco) + " / " + volumeML + "ml)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Bebida bebida = (Bebida) obj;
        return nome != null && nome.equalsIgnoreCase(bebida.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.toLowerCase().hashCode() : 0;
    }
}