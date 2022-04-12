-- #Created By: Franklin Furtado
-- #Timestamp: 2021.07.07 15:42 
-- 

-- -----------------------------------------------------
-- Table `sigp_catequese`.`utilitario_pais`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`utilitario_pais` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_pais` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`utilitario_provincia`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`utilitario_provincia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_provincia` VARCHAR(45) NOT NULL,
  `id_pais` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`utilitario_municipio`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`utilitario_municipio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_municipio` VARCHAR(45) NOT NULL,
  `id_provincia` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`utilitario_distrito`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`utilitario_distrito` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_distrito` VARCHAR(45) NOT NULL,
  `id_municipio` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`utilitario_endereco`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`utilitario_endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bairro` VARCHAR(100) NULL,
  `rua` VARCHAR(100) NULL,
  `id_distrito` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_contactos`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_contactos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NULL,
  `telefone_contacto_emergencia1` VARCHAR(45) NULL,
  `nome_contacto_emergencia1` VARCHAR(45) NULL,
  `grau_parentesco_contacto_emergencia1` VARCHAR(45) NULL,
  `telefone_contacto_emergencia2` VARCHAR(45) NULL,
  `nome_contacto_emergencia2` VARCHAR(45) NULL,
  `numero_telefone1` VARCHAR(45) NULL,
  `numero_telefone2` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`geral_dados_pessoais`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`geral_dados_pessoais` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `nome_completo` VARCHAR(255) NOT NULL,
  `data_nascimento` DATE NOT NULL,
  `nome_pai` VARCHAR(45) NULL,
  `nome_mae` VARCHAR(45) NULL,
  `id_endereco` INT NULL,
  `id_contactos` INT NULL,
  `id_foto_pessoa` BIGINT(20) NULL,
  `id_grupo_movimento` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `sigp_catequese`.`geral_foto_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sigp_catequese`.`geral_foto_pessoa` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `nome_arquivo` VARCHAR(150) NOT NULL,
  `descricao` VARCHAR(150) NULL DEFAULT NULL,
  `content_type` VARCHAR(80) NOT NULL,
  `tamanho` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`paroquia`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`paroquia` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_paroquia` VARCHAR(45) NOT NULL,
  `nome_paroco` VARCHAR(45) NULL,
  `id_endereco` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`paroquia_pastoral`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`paroquia_pastoral` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_pastoral` VARCHAR(45) NOT NULL,
  `nome_resonsavel` VARCHAR(80) NULL,
  `is_catequista` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`paroquial_grupos_movimentos`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`paroquial_grupos_movimentos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `id_pastoral` INT NOT NULL,
  `nome_coordenador` VARCHAR(45) NULL,
  `is_catequista` TINYINT(1) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_centro_catequese`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_centro_catequese` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_centro` VARCHAR(45) NOT NULL,
  `nome_responsavel` VARCHAR(45) NOT NULL,
  `telefone_responsavel1` VARCHAR(45) NULL,
  `telefone_responsavel2` VARCHAR(45) NULL,
  `id_endereco` INT NULL,
  `id_catequista_responsavel_centro` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_sala_catequese`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_sala_catequese` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_sala` VARCHAR(45) NOT NULL,
  `total_cadeiras` INT NULL,
  `id_centro_catequese` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_catequista`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_catequista` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_dados_pessoais` INT NOT NULL,
  `id_sala_catequese_atual` INT NULL,
  `codigo_catequista` VARCHAR(45) NOT NULL,
  `data_inicio_catecismo` DATE NULL,
  `removido` INT(11) NOT NULL,
  `data_remocao` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_catecumeno`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_catecumeno` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_dados_pessoais` INT NOT NULL,
  `id_sala_catequese_atual` INT NULL,
  `codigo_catecumeno` VARCHAR(45) NOT NULL,
  `removido` INT(11) NOT NULL,
  `data_remocao` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_ano_letivo`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_ano_letivo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `ano` INT NOT NULL,
  `data_inicio` DATETIME NOT NULL,
  `data_fim` DATETIME NOT NULL,
  `descricao` VARCHAR(100) NOT NULL,
  `estado_ano_letivo` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_etapa_catequese`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_etapa_catequese` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_etapa` VARCHAR(45) NOT NULL,
  `ano_limite_inscricao` INT NULL,
  `mes_limite_inscricao` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_horario_catequese`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_horario_catequese` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `dia_semana` VARCHAR(45) NOT NULL,
  `hora_inicio` TIME NOT NULL,
  `hora_termino` TIME NOT NULL,
  `nome_periodo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_turma_catequese`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_turma_catequese` (
  `id_sala_catequese` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_ano_letivo` INT NOT NULL,
  `id_etapa_catequese` INT NOT NULL,
  `id_horario_catequese` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_catecumeno_turma`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_catecumeno_turma` (
  `id_catecumeno` INT NOT NULL,
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_turma_catequese` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`seguranca_utilizador`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`seguranca_utilizador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_utilizador` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`seguranca_log_acesso`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`seguranca_log_acesso` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_operacao` DATETIME NOT NULL,
  `descricao_operacao` VARCHAR(45) NOT NULL,
  `id_utilizador` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_inscricoes_catecumeno`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_inscricoes_catecumeno` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_ano_letivo` INT NOT NULL,
  `id_dados_pessoais` INT NOT NULL,
  `id_etapa_catequese` INT NOT NULL,
  `id_centro_catequese_preferencial` INT NOT NULL,
  `id_centro_catequese_alternativo` INT NOT NULL,
  `estado_inscricao` VARCHAR(255) NOT NULL,
  `tipo_inscricao` VARCHAR(255) NOT NULL,
  `data_inscricao` DATETIME NULL,
  `removido` INT(11) NOT NULL,
  `data_remocao` DATETIME NULL,
  `tem_batismo` TINYINT(4) NULL,
  `paroquia_batismo` VARCHAR(255),
  `tem_comunhao` TINYINT(4),
  `paroquia_comunhao` VARCHAR(255),
  `data_batismo` DATE NULL,
  `data_comunhao` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_formacao`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_formacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_formacao` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_catequista_formacao`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_catequista_formacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_catequista` INT NOT NULL,
  `id_formacao` INT NOT NULL,
  `data_inicio_formacao` DATE NULL,
  `data_fim_formacao` DATE NULL,
  `nome_formacao` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_formacao_topico`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_formacao_topico` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_topico` VARCHAR(45) NOT NULL,
  `codigo_topico` INT NULL,
  `id_formacao` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_inscricoes_catequista`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_inscricoes_catequista` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_dados_pessoais` INT NOT NULL,
  `data_inscricao` DATETIME NOT NULL,
  `estado_inscricao` VARCHAR(255) NOT NULL,
  `tipo_inscricao` VARCHAR(255) NOT NULL,
  `removido` INT(11) NOT NULL,
  `data_remocao` DATETIME NULL,
  `tem_batismo` TINYINT(4) NULL,
  `paroquia_batismo` VARCHAR(255) NULL,
  `data_batismo` DATE NULL DEFAULT NULL,
  `tem_comunhao` TINYINT(4) NULL,
  `paroquia_comunhao` VARCHAR(255) NULL,
  `data_comunhao` DATE NULL,
  `tem_crisma` TINYINT(4) NULL,
  `paroquia_crisma` VARCHAR(255) NULL,
  `data_crisma` VARCHAR(255) NULL,
  `tem_matrimonio` TINYINT(4) NULL,
  `paroquia_matrimonio` VARCHAR(255) NULL,
  `data_matrimonio` VARCHAR(255) NULL,
  `tem_grupo_movimento` TINYINT(4) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_catequista_turma`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_catequista_turma` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_catequista` INT NOT NULL,
  `id_turma_catequese` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_centro_catequese_horario_catequese`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_centro_catequese_horario_catequese` (
  `id_centro_catequese` INT NOT NULL,
  `id_horario_catequese` INT NOT NULL,
  PRIMARY KEY (`id_centro_catequese`, `id_horario_catequese`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_centro_catequese_etapa_catequese`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_centro_catequese_etapa_catequese` (
  `id_centro_catequese` INT NOT NULL,
  `id_etapa_catequese` INT NOT NULL,
  PRIMARY KEY (`id_centro_catequese`, `id_etapa_catequese`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_sacramento`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_sacramento` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome_sacramento` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_catecumeno_sacramento`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_catecumeno_sacramento` (
  `id_sacramento` INT NOT NULL,
  `id_catecumeno` INT NOT NULL,
  `data_conclusao_sacramento` DATETIME NULL,
  `paroquia` VARCHAR(255) NULL,
  PRIMARY KEY (`id_sacramento`, `id_catecumeno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sigp_catequese`.`catequese_catequista_sacramento`
-- -----------------------------------------------------
CREATE TABLE `sigp_catequese`.`catequese_catequista_sacramento` (
  `id_catequista` INT NOT NULL,
  `id_sacramento` INT NOT NULL,
  `data_conclusao_sacramento` DATETIME NULL,
  `paroquia` VARCHAR(255) NULL,
  PRIMARY KEY (`id_catequista`, `id_sacramento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- ADD Constraints
-- -----------------------------------------------------

ALTER TABLE `sigp_catequese`.`geral_dados_pessoais` ADD CONSTRAINT `fk_dados_pessoais_endereco1`
  FOREIGN KEY (`id_endereco`)
  REFERENCES `sigp_catequese`.`utilitario_endereco` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequista_grupos_movimentos1`
  FOREIGN KEY (`id_grupo_movimento`)
  REFERENCES `sigp_catequese`.`paroquial_grupos_movimentos` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_dados_pessoais_contactos1`
  FOREIGN KEY (`id_contactos`)
  REFERENCES `sigp_catequese`.`catequese_contactos` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`utilitario_endereco` 
ADD CONSTRAINT `fk_endereco_distrito1`
  FOREIGN KEY (`id_distrito`)
  REFERENCES `sigp_catequese`.`utilitario_distrito` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`utilitario_distrito` 
ADD CONSTRAINT `fk_distrito_municipio1`
  FOREIGN KEY (`id_municipio`)
  REFERENCES `sigp_catequese`.`utilitario_municipio` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`utilitario_municipio` 
ADD CONSTRAINT `fk_municipio_provincia1`
  FOREIGN KEY (`id_provincia`)
  REFERENCES `sigp_catequese`.`utilitario_provincia` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`utilitario_provincia` 
ADD CONSTRAINT `fk_provincia_pais`
  FOREIGN KEY (`id_pais`)
  REFERENCES `sigp_catequese`.`utilitario_pais` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`paroquia` 
ADD CONSTRAINT `fk_paroquia_utilitario_endereco1`
  FOREIGN KEY (`id_endereco`)
  REFERENCES `sigp_catequese`.`utilitario_endereco` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`paroquial_grupos_movimentos` 
ADD CONSTRAINT `fk_grupos_movimentos_pastoral1`
  FOREIGN KEY (`id_pastoral`)
  REFERENCES `sigp_catequese`.`paroquia_pastoral` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_catequista` ADD CONSTRAINT `fk_catequista_dados_pessoais1`
  FOREIGN KEY (`id_dados_pessoais`)
  REFERENCES `sigp_catequese`.`geral_dados_pessoais` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequista_sala_catequese1`
  FOREIGN KEY (`id_sala_catequese_atual`)
  REFERENCES `sigp_catequese`.`catequese_sala_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_catecumeno` ADD CONSTRAINT `fk_catecumeno_dados_pessoais1`
  FOREIGN KEY (`id_dados_pessoais`)
  REFERENCES `sigp_catequese`.`geral_dados_pessoais` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catecumeno_sala_catequese1`
  FOREIGN KEY (`id_sala_catequese_atual`)
  REFERENCES `sigp_catequese`.`catequese_sala_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_centro_catequese` ADD CONSTRAINT `fk_centro_catequese_endereco1`
  FOREIGN KEY (`id_endereco`)
  REFERENCES `sigp_catequese`.`utilitario_endereco` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_centro_catequese_catequista1`
  FOREIGN KEY (`id_catequista_responsavel_centro`)
  REFERENCES `sigp_catequese`.`catequese_catequista` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_sala_catequese` 
ADD CONSTRAINT `fk_sala_catequese_centro_catequese1`
  FOREIGN KEY (`id_centro_catequese`)
  REFERENCES `sigp_catequese`.`catequese_centro_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_catecumeno_turma` ADD CONSTRAINT `fk_sala_catequese_has_catecumeno_catecumeno1`
  FOREIGN KEY (`id_catecumeno`)
  REFERENCES `sigp_catequese`.`catequese_catecumeno` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_catecumeno_turma_catequese_turma_catequese1`
  FOREIGN KEY (`id_turma_catequese`)
  REFERENCES `sigp_catequese`.`catequese_turma_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_turma_catequese` ADD CONSTRAINT `fk_catequista_has_sala_catequese_sala_catequese1`
  FOREIGN KEY (`id_sala_catequese`)
  REFERENCES `sigp_catequese`.`catequese_sala_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequista_sala_catequese_catequese_ano_letivo1`
  FOREIGN KEY (`id_ano_letivo`)
  REFERENCES `sigp_catequese`.`catequese_ano_letivo` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequista_sala_catequese_catequese_etapa_catequese1`
  FOREIGN KEY (`id_etapa_catequese`)
  REFERENCES `sigp_catequese`.`catequese_etapa_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_turma_catequese_catequese_horario_catequese1`
  FOREIGN KEY (`id_horario_catequese`)
  REFERENCES `sigp_catequese`.`catequese_horario_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`seguranca_log_acesso` 
ADD CONSTRAINT `fk_seguranca_log_acesso_seguranca_utilizador1`
  FOREIGN KEY (`id_utilizador`)
  REFERENCES `sigp_catequese`.`seguranca_utilizador` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_inscricoes_catecumeno` ADD CONSTRAINT `fk_catequese_inscricoes_catequese_ano_letivo1`
  FOREIGN KEY (`id_ano_letivo`)
  REFERENCES `sigp_catequese`.`catequese_ano_letivo` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_inscricoes_geral_dados_pessoais1`
  FOREIGN KEY (`id_dados_pessoais`)
  REFERENCES `sigp_catequese`.`geral_dados_pessoais` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_inscricoes_catecumeno_catequese_etapa_catequese1`
  FOREIGN KEY (`id_etapa_catequese`)
  REFERENCES `sigp_catequese`.`catequese_etapa_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_inscricoes_catecumeno_catequese_centro_catequese1`
  FOREIGN KEY (`id_centro_catequese_preferencial`)
  REFERENCES `sigp_catequese`.`catequese_centro_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_inscricoes_catecumeno_catequese_centro_catequese2`
  FOREIGN KEY (`id_centro_catequese_alternativo`)
  REFERENCES `sigp_catequese`.`catequese_centro_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_catequista_formacao` 
ADD CONSTRAINT `fk_catequese_catequista_has_catequese_formacao_catequese_cate1`
  FOREIGN KEY (`id_catequista`)
  REFERENCES `sigp_catequese`.`catequese_catequista` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_catequista_has_catequese_formacao_catequese_form1`
  FOREIGN KEY (`id_formacao`)
  REFERENCES `sigp_catequese`.`catequese_formacao` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_formacao_topico` 
ADD CONSTRAINT `fk_catequese_formacao_topico_catequese_formacao1`
  FOREIGN KEY (`id_formacao`)
  REFERENCES `sigp_catequese`.`catequese_formacao` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_inscricoes_catequista` ADD CONSTRAINT `fk_catequese_inscricoes_catequista_geral_dados_pessoais1`
  FOREIGN KEY (`id_dados_pessoais`)
  REFERENCES `sigp_catequese`.`geral_dados_pessoais` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_catequista_turma` 
ADD CONSTRAINT `fk_catequese_catequista_turma_catequese_catequista1`
  FOREIGN KEY (`id_catequista`)
  REFERENCES `sigp_catequese`.`catequese_catequista` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_catequista_turma_catequese_turma_catequese1`
  FOREIGN KEY (`id_turma_catequese`)
  REFERENCES `sigp_catequese`.`catequese_turma_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_centro_catequese_horario_catequese` 
ADD CONSTRAINT `fk_catequese_centro_catequese_has_catequese_horario_catequese1`
  FOREIGN KEY (`id_centro_catequese`)
  REFERENCES `sigp_catequese`.`catequese_centro_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_centro_catequese_has_catequese_horario_catequese2`
  FOREIGN KEY (`id_horario_catequese`)
  REFERENCES `sigp_catequese`.`catequese_horario_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_centro_catequese_etapa_catequese` 
ADD CONSTRAINT `fk_catequese_centro_catequese_has_catequese_etapa_catequese_c1`
  FOREIGN KEY (`id_centro_catequese`)
  REFERENCES `sigp_catequese`.`catequese_centro_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_centro_catequese_has_catequese_etapa_catequese_c2`
  FOREIGN KEY (`id_etapa_catequese`)
  REFERENCES `sigp_catequese`.`catequese_etapa_catequese` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_catecumeno_sacramento` ADD CONSTRAINT `fk_catequese_sacramento_has_catequese_catecumeno_catequese_sa1`
  FOREIGN KEY (`id_sacramento`)
  REFERENCES `sigp_catequese`.`catequese_sacramento` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_sacramento_has_catequese_catecumeno_catequese_ca1`
  FOREIGN KEY (`id_catecumeno`)
  REFERENCES `sigp_catequese`.`catequese_catecumeno` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`catequese_catequista_sacramento` 
ADD CONSTRAINT `fk_catequese_catequista_has_catequese_sacramento_catequese_ca1`
  FOREIGN KEY (`id_catequista`)
  REFERENCES `sigp_catequese`.`catequese_catequista` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_catequese_catequista_has_catequese_sacramento_catequese_sa1`
  FOREIGN KEY (`id_sacramento`)
  REFERENCES `sigp_catequese`.`catequese_sacramento` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `sigp_catequese`.`geral_dados_pessoais` 
ADD CONSTRAINT `fk_geral_dados_pessoais1`
  FOREIGN KEY (`id_foto_pessoa`)
  REFERENCES `sigp_catequese`.`geral_foto_pessoa` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
 -- Inserting releationship on tables catequese_inscricoes_catequista, catequese_inscricoes_catecumeno with paroquial_grupos_movimentos
  
-- ALTER TABLE `sigp_catequese`.`catequese_inscricoes_catequista` 
-- ADD COLUMN `id_grupo_movimento` INT(11) NULL AFTER `data_remocao`,
-- ADD INDEX `fk_catequese_inscricoes_catequista1_idx` (`id_grupo_movimento` ASC) VISIBLE;

-- ALTER TABLE `sigp_catequese`.`catequese_inscricoes_catecumeno` 
-- ADD COLUMN `id_grupo_movimento` INT(11) NULL DEFAULT NULL AFTER `data_remocao`,
-- ADD INDEX `fk_catequese_inscricoes_catecumeno1_idx` (`id_grupo_movimento` ASC) VISIBLE;

-- ALTER TABLE `sigp_catequese`.`catequese_inscricoes_catequista` 
-- ADD CONSTRAINT `fk_catequese_inscricoes_catequista1`
  -- FOREIGN KEY (`id_grupo_movimento`)
  -- REFERENCES `sigp_catequese`.`paroquial_grupos_movimentos` (`id`)
  -- ON DELETE NO ACTION
  -- ON UPDATE NO ACTION;

-- ALTER TABLE `sigp_catequese`.`catequese_inscricoes_catecumeno` 
-- ADD CONSTRAINT `fk_catequese_inscricoes_catecumeno1`
  -- FOREIGN KEY (`id_grupo_movimento`)
  -- REFERENCES `sigp_catequese`.`paroquial_grupos_movimentos` (`id`)
  -- ON DELETE NO ACTION
  -- ON UPDATE NO ACTION;