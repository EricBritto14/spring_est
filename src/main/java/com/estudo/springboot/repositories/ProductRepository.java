package com.estudo.springboot.repositories;

import com.estudo.springboot.models.ProductModel;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
//Spring tem 4 arrobas necessários saber, para saber como será o tratamento do Bean de acordo com o service.
// @Component, quando temos uma classe mais genérica podemos utilizar o @Component para mostrar pro Bean que vai ser gerenciado por ele
//@Service para mostrar para o Spring que ela vai ser um Bean do tipo service que vai ser gerenciado para ele. O service serve para quando for lidar com regras de negócio
//@Repository podemos motrar para o Spring que está classe/Interface vai ser um Bean do tipo Repository que vai transações com base de dados
//@Controller mostra pro Spring que vai ser uma classe que terá endpoints

@Repository //Este @Repository não é 100% necessário pois embaixo já puxamos o JpaRepository, assim o spring já entende que aqui vai tratar dados, mas por boa prática e mais facil entendimento, é bom deixar!
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {   //Importando a JPARepository para facilitar e dar acesso a tudo necessário, e dentro dela passando a classe Model que vamos trabalhar, e o identificador, no caso o UUID

}
