package com.estudo.springboot.controllers;

import com.estudo.springboot.dtos.ProductRecordDto;
import com.estudo.springboot.models.ProductModel;
import com.estudo.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController //Aqui poderia ser @Controller, caso não estivessemos fazendo uma APIRest. Mas como é APIRest, e para ficar mais claro pro Spring qual deve ser o comportamento do Bean, restcontroller vai ser melhor
public class ProductController {

    @Autowired
    ProductRepository productRepository; //Criando o "objeto" desta interface para ter acesso aos métodos jpa quando necessidade no nosso controller

    //Passando o Post para postar, na uri /products quando chamar no site ou no teste da API
    @PostMapping("/products")
    //Este método de post vai receber o que do corpo da solicitação? Então temos que colocar o @RequestBody para identificar o que vai vim do corpo, e o valor, que no caso é o DTO que é condizente com o JPA da criação do banco de dados
    //@Valid serve para fazer a validação e confirme o que está vindo
    //E o @Valid tem que ser no controller mesmo, não no DTO se não lá não vai fazer validação nenhuma
    public ResponseEntity<ProductModel> saveNewProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        //var é para a criação de uma variável sem tipo pode se dizer.
        var productModel = new ProductModel(); //Iniciando um objeto de ProductModel(banco de dados) pois é este objeto que vamos salvar no banco, não o DTO em si. O DTO só serve para o usuário passar os valores, ai vem pro controller e faz as validações com o @Valid, depois passa para o objeto de model, e assim salva no model real.
        BeanUtils.copyProperties(productRecordDto, productModel); //O BeanUtils.copyProperties, serve para pegar o DTO depois de fazer a validação e etc, e transformar no objeto de model para salvar no banco de dados model do banco
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products") //Enderaçamento para pegar o get também com o post
    public ResponseEntity<List<ProductModel>> getAllProducts(){ //Indicando que vai retornar uma lista
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable(value="id") UUID id){
        Optional<ProductModel> productO = productRepository.findById(id); //Linha do objeto para procurar o produto pelo id no banco de dados
        if(productO.isEmpty()){ //if para verificar se está vazio
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    @GetMapping("/products/produto_name/{nome}")
    public ResponseEntity<Object> getProductByName(@RequestParam(value="name") String name){
        Optional<ProductModel> produtoByName = productRepository.findByName(name);
        if(produtoByName.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto de nome {} não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtoByName.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id,
                                                @RequestBody @Valid ProductRecordDto productRecordDto){
        Optional<ProductModel> product0 = productRepository.findById(id); //Linha do objeto para procurar o produto pelo id no banco de dados
        if(product0.isEmpty()){//if para verificar se está vazio
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Inexistente para atualização!");
        }
        var productModel = product0.get(); //Criando uma variável nova mas com o produto que foi pego pelo product0 lá em cima e não estava vazio.
        BeanUtils.copyProperties(productRecordDto, productModel); //Transcrevendo a variável productModel acima, para o DTO criado para atualizar no banco de dados
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel)); //Salvando no banco e retornando OK para o usuário
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteObject(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product0 = productRepository.findById(id);
        if(product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Inexistente para o delete!");
        }
        productRepository.delete(product0.get()); //Linha para deletar o que o product0 pegar se existir o id que for passado
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
    }
}
