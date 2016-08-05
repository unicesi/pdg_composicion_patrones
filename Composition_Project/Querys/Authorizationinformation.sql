CREATE TABLE Authorizationinformation
  (
    cod_subject INTEGER NOT NULL ,
    id_system   INTEGER NOT NULL ,
    tipo_acceso VARCHAR2 (20 CHAR) NOT NULL
  ) ;
COMMENT ON COLUMN Authorizationinformation.cod_subject
IS
  'C�digo del sujeto.' ;
  COMMENT ON COLUMN Authorizationinformation.id_system
IS
  'Identifiaci�n del sistema (Por ejemplo, identificaci�n del service locator).' ;
  COMMENT ON COLUMN Authorizationinformation.tipo_acceso
IS
  'Tipo de acceso. PERMITIDO- SI est� autorizado en el sistema. DENEGADO-Si no lo est�.
' ;
  ALTER TABLE Authorizationinformation ADD CONSTRAINT Authorizationinformation_PK PRIMARY KEY ( cod_subject, id_system ) ;
