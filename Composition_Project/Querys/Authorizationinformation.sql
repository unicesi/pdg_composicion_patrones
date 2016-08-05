CREATE TABLE Authorizationinformation
  (
    cod_subject INTEGER NOT NULL ,
    id_system   INTEGER NOT NULL ,
    tipo_acceso VARCHAR2 (20 CHAR) NOT NULL
  ) ;
COMMENT ON COLUMN Authorizationinformation.cod_subject
IS
  'Código del sujeto.' ;
  COMMENT ON COLUMN Authorizationinformation.id_system
IS
  'Identifiación del sistema (Por ejemplo, identificación del service locator).' ;
  COMMENT ON COLUMN Authorizationinformation.tipo_acceso
IS
  'Tipo de acceso. PERMITIDO- SI está autorizado en el sistema. DENEGADO-Si no lo está.
' ;
  ALTER TABLE Authorizationinformation ADD CONSTRAINT Authorizationinformation_PK PRIMARY KEY ( cod_subject, id_system ) ;
