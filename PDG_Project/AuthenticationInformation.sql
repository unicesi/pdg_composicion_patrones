CREATE TABLE Authenticationinformation
  (
    cod_subject INTEGER NOT NULL ,
    password    VARCHAR2 (300 BYTE) NOT NULL ,
    autenticado CHAR (1) NOT NULL
  ) ;
COMMENT ON COLUMN Authenticationinformation.cod_subject
IS
  'Código del sujeto autenticado.' ;
  COMMENT ON COLUMN Authenticationinformation.password
IS
  'Password encriptado del sujeto autenticado.' ;
  COMMENT ON COLUMN Authenticationinformation.autenticado
IS
  'Indica si este sujeto está autenticado en el sistema o no. 0-No lo está. 1- Si lo está.' ;
  ALTER TABLE Authenticationinformation ADD CONSTRAINT Authenticationinformation_PK PRIMARY KEY ( cod_subject ) ;
