CREATE TABLE entrega (
   id bigint NOT null AUTO_INCREMENT,
    cliente_id bigint NOT null,
    taxa decimal(10,2) NOT null,
    status varchar(20)  NOT null,
    data_pedido datetime NOT null,
    data_finalizado datetime,    
    destinatario_nome varchar(60) NOT null,
    destinatario_logradouro varchar(250) NOT null,
    destinatario_numero varchar(30) NOT null,
    destinatario_complemento varchar(60) NOT null,
    destinatario_bairro varchar(30) NOT null,    
    PRIMARY KEY (id)
);
ALTER TABLE entrega ADD CONSTRAINT fk_entrega_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id);