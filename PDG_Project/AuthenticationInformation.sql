CREATE TABLE AuthenticationInformation
  (
    cod_subject INTEGER NOT NULL ,
    password    VARCHAR2 (25) NOT NULL ,
    autenticado BLOB NOT NULL
  ) ;
ALTER TABLE AuthenticationInformation ADD CONSTRAINT AuthenticationInformation_PK PRIMARY KEY ( cod_subject ) ;
