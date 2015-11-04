CREATE TABLE AuthenticationInformation
  (
    id_system   INTEGER NOT NULL ,
    cod_subject INTEGER NOT NULL ,
    password    VARCHAR2 (25 CHAR) NOT NULL ,
    autenticado CHAR (1) NOT NULL
  ) ;
COMMENT ON COLUMN AuthenticationInformation.id_system
IS
  'Identificación del sistema.' ;
  COMMENT ON COLUMN AuthenticationInformation.cod_subject
IS
  'Código del subject dentro del sistema.' ;
  COMMENT ON COLUMN AuthenticationInformation.password
IS
  'Password del subject para en ese sistema.' ;
  COMMENT ON COLUMN AuthenticationInformation.autenticado
IS
  'Indica si el sujeto está autenticado en el sistema.' ;
  ALTER TABLE AuthenticationInformation ADD CONSTRAINT AuthenticationInformation_PK PRIMARY KEY ( id_system, cod_subject ) ;
