-- -----------------------------------------------------
-- Schema test-db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `test-db`;

-- -----------------------------------------------------
-- Schema test-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test-db`;
USE `test-db`;

-- -----------------------------------------------------
-- Table `test-db`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`usuarios` (
  `id` VARCHAR(36) NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) UNIQUE NOT NULL,
  `senha` VARCHAR(100) NOT NULL,
  `criado_em` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `test-db`.`eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`eventos` (
  `id` VARCHAR(36) NOT NULL,
  `titulo` VARCHAR(100) NOT NULL,
  `descricao` TEXT NULL,
  `tipo` VARCHAR(30) NULL,
  `data` DATETIME NOT NULL,
  `ativo` BOOLEAN NOT NULL DEFAULT true,
  `usuario_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuario_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `test-db`.`enderecos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`enderecos` (
  `id` VARCHAR(36) NOT NULL,
  `local` VARCHAR(100) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `cidade` VARCHAR(50) NOT NULL,
  `rua` VARCHAR(100) NOT NULL,
  `numero` INT NOT NULL,
  `evento_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`evento_id`) REFERENCES `test-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test-db`.`arquivos` (
    id VARCHAR(36) PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    caminho VARCHAR(255) NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    evento_id VARCHAR(36) NOT NULL,
    FOREIGN KEY (evento_id) REFERENCES `test-db`.`eventos`(id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table `test-db`.`imagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`imagens` (
  `id` VARCHAR(36) NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `caminho` LONGTEXT NOT NULL,
  `eventos_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (id) REFERENCES `test-db`.`arquivos`(id) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `test-db`.`notificacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`notificacoes` (
  `id` VARCHAR(36) NOT NULL,
  `titulo` VARCHAR(100) NULL,
  `corpo` LONGTEXT NULL,
  `data` DATETIME NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `test-db`.`datas_especiais`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`datas_especiais` (
  `id` VARCHAR(36) NOT NULL,
  `data` DATETIME NOT NULL,
  `usuarios_id` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuarios_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `test-db`.`amizades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`amizades` (
  `id` VARCHAR(36) NOT NULL,
  `usuarios_id` VARCHAR(36),
  `amigo_id` VARCHAR(36),
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`usuarios_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`amigo_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `test-db`.`usuario_evento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`usuario_evento` (
  `usuarios_id` VARCHAR(36) NOT NULL,
  `eventos_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuarios_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`eventos_id`) REFERENCES `test-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `test-db`.`notificacao_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test-db`.`notificacao_usuario` (
  `notificacao_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuario_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`notificacao_id`) REFERENCES `test-db`.`notificacoes`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `test-db`.`requisitos` (
	`id` VARCHAR(36) PRIMARY KEY NOT NULL,
	`titulo` VARCHAR(100) NOT NULL,
	`descricao` VARCHAR(200) NOT NULL,
	`evento_id` VARCHAR(36) NOT NULL,
	 FOREIGN KEY (`evento_id`) REFERENCES `test-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test-db`.`requisito_usuario` (
  `requisito_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuario_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`requisito_id`) REFERENCES `test-db`.`requisitos`(`id`) ON DELETE CASCADE,
  CONSTRAINT `unique_requisito_usuario` UNIQUE (`requisito_id`, `usuario_id`)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test-db`.`convites` (
	`id` VARCHAR(36) PRIMARY KEY NOT NULL,
	`titulo` VARCHAR(100) NOT NULL,
	`descricao` VARCHAR(200) NOT NULL,
	`evento_id` VARCHAR(36) NOT NULL,
	 FOREIGN KEY (`evento_id`) REFERENCES `test-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `test-db`.`convite_usuario` (
  `convite_id` VARCHAR(36) NOT NULL,
  `usuario_id` VARCHAR(36) NOT NULL,
  FOREIGN KEY (`usuario_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`convite_id`) REFERENCES `test-db`.`convites`(`id`) ON DELETE CASCADE,
  CONSTRAINT `unique_requisito_usuario` UNIQUE (`convite_id`, `usuario_id`)
) ENGINE = InnoDB;

CREATE TABLE `test-db`.`chats` (
    `id` VARCHAR(36) PRIMARY KEY,
    `nome` VARCHAR(100) NOT NULL,
    `evento_id` VARCHAR(36),
    FOREIGN KEY (`evento_id`) REFERENCES `test-db`.`eventos`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE `test-db`.`chat_usuario` (
    `chat_id` VARCHAR(36) NOT NULL,
    `usuario_id` VARCHAR(36) NOT NULL,
    FOREIGN KEY (`chat_id`) REFERENCES `test-db`.`chats`(`id`) ON DELETE CASCADE,
    FOREIGN KEY (`usuario_id`) REFERENCES `test-db`.`usuarios`(`id`) ON DELETE CASCADE
) ENGINE = InnoDB;


CREATE TABLE `test-db`.`mensagens` (
    `id` VARCHAR(36) PRIMARY KEY,
    `conteudo` TEXT NOT NULL,
	`data_envio` TIMESTAMP NOT NULL,
	`chat_id` VARCHAR(36) NOT NULL,
    `usuario_id` VARCHAR(36) NOT NULL,
    FOREIGN KEY (`chat_id`) REFERENCES `test-db`.`chats`(id) ON DELETE CASCADE,
    FOREIGN KEY (`usuario_id`) REFERENCES `test-db`.`usuarios`(id) ON DELETE CASCADE
) ENGINE = InnoDB;
