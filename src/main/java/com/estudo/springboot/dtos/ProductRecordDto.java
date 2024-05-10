package com.estudo.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

//Record serve como uma criação facil e rápida de DTO(data transfer object), entre o servidor e o cliente, pedindo o que quiser, e encapusulando melhor as transferencias de informações
//Aqui é como se fosse o schemas do fastapi, aqui serve para vc escrever o que vc quer q o cliente mande de informação, aí você trata o dado, manda pra jpa no product model e depois salva no banco
//O que você for pedir aqui pro usuário, como nome, valor e etc. Tem que ter no model também
//Records são imutaveis, uma vez criado não tem como mudar mais os valores, por isso não tem setter, só getter
//Não precisa falar que é private, pois no models eles já estão privates
//@NotBLanck indicando que tal variável não pode ser null e como é string tem que ser NotBlank, e por ser decimal, é NotNull
public record ProductRecordDto(@NotBlank String name, @NotNull BigDecimal value) {

}
