CREATE TABLE Derecho
  (
    id_subject            INTEGER NOT NULL ,
    name_protected_object VARCHAR2 (30 CHAR) NOT NULL ,
    tipo_acceso           VARCHAR2 (20 CHAR) NOT NULL
  ) ;
COMMENT ON COLUMN Derecho.id_subject
IS
  'Identificación del sujeto.' ;
  COMMENT ON COLUMN Derecho.name_protected_object
IS
  'Nombre del business object protegido.' ;
  COMMENT ON COLUMN Derecho.tipo_acceso
IS
  'PERMITIDO-Si está el sujeto permitido para acceder a los servicios del bussiness object protegido.
DENEGADO-Si no está permitido el sujeto para acceder a los servicios del business object protegido.' ;
  ALTER TABLE Derecho ADD CONSTRAINT Derecho_PK PRIMARY KEY ( name_protected_object, id_subject ) ;