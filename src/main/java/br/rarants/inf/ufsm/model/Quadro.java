package br.rarants.inf.ufsm.model;

import java.util.ArrayList;
import java.util.List;

public class Quadro {
    private int id;
    private String titulo;
    private String descricao;
    private Boolean publico;
    private Usuario usuario;
    private ArrayList<Etiqueta> etiquetaArrayList;
    private ArrayList<Coluna> colunaArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getPublico() {
        return publico;
    }

    public void setPublico(Boolean publico) {
        this.publico = publico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Etiqueta> getEtiquetaArrayList() {
        return etiquetaArrayList;
    }

    public void setEtiquetaArrayList(ArrayList<Etiqueta> etiquetaArrayList) {
        this.etiquetaArrayList = etiquetaArrayList;
    }

    public ArrayList<Coluna> getColunaArrayList() {
        return colunaArrayList;
    }

    public void setColunaArrayList(ArrayList<Coluna> colunaArrayList) {
        this.colunaArrayList = colunaArrayList;
    }

    @Override
    public String toString() {
        return "Quadros{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", publico=" + publico +
                ", usuario=" + usuario.toString() +
                ", etiquetaList=" + etiquetaArrayList +
                ", colunaArrayList=" + colunaArrayList +
                '}';
    }
}
