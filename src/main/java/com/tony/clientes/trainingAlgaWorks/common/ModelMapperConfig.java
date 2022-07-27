package com.tony.clientes.trainingAlgaWorks.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration /*
                * esta anotação diz a Spring que: Esta classe é de configuração para o Beans e
                * metodos que definem Beans Spring, e anotar todos o Metodos criado com @Bean
                */
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {/*
                                       * o 1ª metodo será um q retorna o ModelMapper, justamento o que haviamos
                                       * injetado, para retornar uma instacia de ModelMapper.
                                       * a anotação @Bean diz para Spring q é para gerenciar este metodo em outras classes.
                                       */
        return new ModelMapper();

    }

}
