package com.estudo.springboot.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import jakarta.persistence.*;

//Classe da entidade do banco de dados, com todo o mapeamento de JPA(Java persistence API), para a tabela ser gerada no postgree

//Entidade normal do banco de dados
@Entity //Entidade normal do banco de dados
@Table(name = "TB_PRODUCTS") //Table, referindo-se a tabela no banco de dados
public class ProductModel implements Serializable{
    private static final long serialVersionUID = 1L;

    //Criando os itens que vão pertencer a table TB_PRODUCTS
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO) //Esta linha é pra não se preocupar com o valor do id e ser auto_increment, ele se dar o valor sozinho de acordo com o banco
    private UUID idProduct; //UUID serve como identificador também, se o projeto usar de arquiteturas distribuidas. É uma boa prática e realmente necessário
    private String name;
    private BigDecimal value;

    //Criando getters e setters abaixo, pelo botão direito e Generate

    public UUID getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(UUID idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
