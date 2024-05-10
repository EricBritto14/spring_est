package com.estudo.springboot.controllers;

import com.estudo.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController //Aqui poderia ser @Controller, caso não estivessemos fazendo uma APIRest. Mas como é APIRest, e para ficar mais claro pro Spring qual deve ser o comportamento do Bean, restcontroller vai ser melhor
public class ProductController {

    @Autowired
    ProductRepository productRepository; //Criando o "objeto" desta interface para ter acesso aos métodos jpa quando necessidade no nosso controller

}
