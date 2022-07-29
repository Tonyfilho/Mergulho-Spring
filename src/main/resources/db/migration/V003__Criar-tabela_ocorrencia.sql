CREATE TABLE ocorrencia (
    id bigint not null AUTO_INCREMENT,
    entrega_id bigint not null,
    descricao text not null,
    data_registro datetime not null,

    PRIMARY KEY (id)

);

ALTER TABLE ocorrencia ADD CONSTRAINT fk_ocorrencia_entrega FOREIGN KEY (entrega_id) REFERENCES entrega(id);