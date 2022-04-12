set foreign_key_checks = 0;

delete from catequese_ano_letivo;
delete from catequese_catecumeno;
delete from catequese_catecumeno_sacramento;
delete from catequese_catecumeno_turma;
delete from catequese_catequista;
delete from catequese_catequista_formacao;
delete from catequese_catequista_sacramento;
delete from catequese_catequista_turma;
delete from catequese_centro_catequese;
delete from catequese_centro_catequese_etapa_catequese;
delete from catequese_centro_catequese_horario_catequese;
delete from catequese_contactos;
delete from catequese_etapa_catequese;
delete from catequese_formacao;
delete from catequese_formacao_topico;
delete from catequese_horario_catequese;
delete from catequese_inscricoes_catecumeno;
delete from catequese_inscricoes_catequista;
delete from catequese_sala_catequese;
delete from catequese_turma_catequese;
delete from geral_dados_pessoais;
delete from paroquia;
delete from paroquial_grupos_movimentos;
delete from paroquia_pastoral;
delete from seguranca_log_acesso;
delete from seguranca_utilizador;
delete from utilitario_distrito;
delete from utilitario_endereco;
delete from utilitario_municipio;
delete from utilitario_pais;
delete from utilitario_provincia;
delete from catequese_catequista;

set foreign_key_checks = 1;

alter table catequese_ano_letivo auto_increment = 1;
alter table catequese_catecumeno auto_increment = 1;
alter table catequese_catecumeno_sacramento auto_increment = 1;
alter table catequese_catecumeno_turma auto_increment = 1;
alter table catequese_catequista auto_increment = 1;
alter table catequese_catequista_formacao auto_increment = 1;
alter table catequese_catequista_sacramento auto_increment = 1;
alter table catequese_catequista_turma auto_increment = 1;
alter table catequese_centro_catequese auto_increment = 1;
alter table catequese_centro_catequese_etapa_catequese auto_increment = 1;
alter table catequese_centro_catequese_horario_catequese auto_increment = 1;
alter table catequese_contactos auto_increment = 1;
alter table catequese_etapa_catequese auto_increment = 1;
alter table catequese_formacao auto_increment = 1;
alter table catequese_formacao_topico auto_increment = 1;
alter table catequese_horario_catequese auto_increment = 1;
alter table catequese_inscricoes_catecumeno auto_increment = 1;
alter table catequese_inscricoes_catequista auto_increment = 1;
alter table catequese_sala_catequese auto_increment = 1;
alter table catequese_turma_catequese auto_increment = 1;
alter table geral_dados_pessoais auto_increment = 1;
alter table paroquia auto_increment = 1;
alter table paroquial_grupos_movimentos auto_increment = 1;
alter table paroquia_pastoral auto_increment = 1;
alter table seguranca_log_acesso auto_increment = 1;
alter table seguranca_utilizador auto_increment = 1;
alter table utilitario_distrito auto_increment = 1;
alter table utilitario_endereco auto_increment = 1;
alter table utilitario_municipio auto_increment = 1;
alter table utilitario_pais auto_increment = 1;
alter table utilitario_provincia auto_increment = 1;
alter table catequese_catequista auto_increment = 1;


-- Inserir pastoral
INSERT INTO `sigp_catequese`.`paroquia_pastoral` (`is_catequista`, `nome_pastoral`, `nome_resonsavel`) VALUES (1, 'Juvenil', 'Franklin Furtado');

-- Inserir Grupo/Movimento
INSERT INTO `sigp_catequese`.`paroquial_grupos_movimentos` (`id`, `nome`, `id_pastoral`, `is_catequista`) VALUES ('1', 'Movimento Missionario', '1', '0');

-- -- Inserir ano letivo
INSERT INTO `sigp_catequese`.`catequese_ano_letivo` (`ano`, `data_fim`, `data_inicio`, `descricao`, `estado_ano_letivo`) VALUES ('2020', '2020/03/12', '2020/09/12', 'Ano Letivo de 2020', 'ENCERRADO');
INSERT INTO `sigp_catequese`.`catequese_ano_letivo` (`ano`, `data_fim`, `data_inicio`, `descricao`, `estado_ano_letivo`) VALUES ('2021', '2021/03/12', '2021/09/12', 'Ano Letivo de 2021', 'CRIADO');

-- Inserindo uma paroquia
INSERT INTO `sigp_catequese`.`utilitario_pais` (`nome_pais`) VALUES ('Angola');
INSERT INTO `sigp_catequese`.`utilitario_provincia` (`nome_provincia`, `id_pais`) VALUES ('Luanda', '1');
INSERT INTO `sigp_catequese`.`utilitario_municipio` (`nome_municipio`, `id_provincia`) VALUES ('Luanda', '1');
INSERT INTO `sigp_catequese`.`utilitario_distrito` (`nome_distrito`, `id_municipio`) VALUES ('Sambizanga', '1');
INSERT INTO `sigp_catequese`.`utilitario_endereco` (`bairro`, `id_distrito`) VALUES ('São Paulo', '1');
INSERT INTO `sigp_catequese`.`paroquia` (`nome_paroquia`, `nome_paroco`, `id_endereco`) VALUES ('São Paulo', 'Pe. Alberto', '1');

-- Inserir inscricao de catequistas
INSERT INTO `sigp_catequese`.`catequese_contactos` (`id`, `email`, `telefone_contacto_emergencia1`, `nome_contacto_emergencia1`, `grau_parentesco_contacto_emergencia1`, `telefone_contacto_emergencia2`, `nome_contacto_emergencia2`, `numero_telefone1`, `numero_telefone2`) VALUES (1, "franklin.silva.furtado@gmail.com", "333333333", "Marcia Pinto Furtado", "Outro", "444444444", null, "921300901", "995375669"); 
INSERT INTO `sigp_catequese`.`utilitario_endereco` (`id`, `bairro`, `rua`, `id_distrito`) VALUES (2, "Sao Paulo", "Comandante Bula", 1); 
INSERT INTO `sigp_catequese`.`geral_dados_pessoais` (`id`, `nome`, `sobrenome`, `nome_completo`, `data_nascimento`, `nome_pai`, `nome_mae`, `id_endereco`, `id_contactos`, `id_foto_pessoa`, `id_grupo_movimento`) VALUES (1, "Franklin", "Furtado", "Franklin da Silva Furtado", "1993-05-03", "Naturino Silva Furtado", "Maria de Fatima Silva", 1, 1, null, 1);   
INSERT INTO `sigp_catequese`.`catequese_inscricoes_catequista` (`id`, `estado_inscricao`, `id_dados_pessoais`, `data_inscricao`, `tipo_inscricao`, `removido`, `tem_batismo`, `paroquia_batismo`, `data_batismo`, `tem_comunhao`, `paroquia_comunhao`, `data_comunhao`, `tem_crisma`, `paroquia_crisma`, `data_crisma`, `tem_matrimonio`, `paroquia_matrimonio`, `data_matrimonio`, `tem_grupo_movimento`) VALUES (1, 'APROVADO', 1, "2021-08-06 14:02:46", 'LOCAL', 0, 1, "São Paulo", "2021-08-05", 1, null, null, 1, null, null, 1, null, null, 1);

INSERT INTO `sigp_catequese`.`catequese_contactos` (`id`, `email`, `telefone_contacto_emergencia1`, `nome_contacto_emergencia1`, `grau_parentesco_contacto_emergencia1`, `telefone_contacto_emergencia2`, `nome_contacto_emergencia2`, `numero_telefone1`, `numero_telefone2`) VALUES (2, "lauro.silva@gmail.com", "333333333", "Jacira Silva", "Outro", "444444444", null, "921300242", "921371000"); 
INSERT INTO `sigp_catequese`.`utilitario_endereco` (`id`, `bairro`, `rua`, `id_distrito`) VALUES (3, "Viana", "Rua J", 1); 
INSERT INTO `sigp_catequese`.`geral_dados_pessoais` (`id`, `nome`, `sobrenome`, `nome_completo`, `data_nascimento`, `nome_pai`, `nome_mae`, `id_endereco`, `id_contactos`, `id_foto_pessoa`) VALUES (2, "Lauro", "Silva", "Lauro Zua da Silva", "1993-08-24", "Sadraque Silva", "Maria Zua", 3, 2, null);   
INSERT INTO `sigp_catequese`.`catequese_inscricoes_catequista` (`id`, `estado_inscricao`, `id_dados_pessoais`, `data_inscricao`, `tipo_inscricao`, `removido`, `tem_batismo`, `paroquia_batismo`, `data_batismo`, `tem_comunhao`, `paroquia_comunhao`, `data_comunhao`, `tem_crisma`, `paroquia_crisma`, `data_crisma`, `tem_matrimonio`, `paroquia_matrimonio`, `data_matrimonio`, `tem_grupo_movimento`) VALUES (2, 'APROVADO', 2, "2021-08-06 14:02:46", 'LOCAL', 0, 1, "São Paulo", "2021-08-05", 1, null, null, 1, null, null, 0, null, null, 0);

-- Inserir catequista
INSERT INTO `sigp_catequese`.`catequese_catequista` (`id`, `id_dados_pessoais`, `codigo_catequista`, `data_inicio_catecismo`, `removido`) VALUES ('1', '1', 'CTSP000001', '2017-02-05', '0');
INSERT INTO `sigp_catequese`.`catequese_catequista` (`id`, `id_dados_pessoais`, `codigo_catequista`, `data_inicio_catecismo`, `removido`) VALUES ('2', '2', 'CTSP000002', '2010-02-05', '0');

-- Inserir sacramentos no catequista
INSERT INTO `sigp_catequese`.`catequese_catequista_sacramento` (`id_catequista`, `id_sacramento`, `data_conclusao_sacramento`, `paroquia`) VALUES ('1', '1', '1995/03/12', 'São Paulo');
INSERT INTO `sigp_catequese`.`catequese_catequista_sacramento` (`id_catequista`, `id_sacramento`, `data_conclusao_sacramento`, `paroquia`) VALUES ('1', '2', '2010/11/12', 'São Paulo');
INSERT INTO `sigp_catequese`.`catequese_catequista_sacramento` (`id_catequista`, `id_sacramento`, `data_conclusao_sacramento`, `paroquia`) VALUES ('1', '3', '2010/11/12', 'São Paulo');
INSERT INTO `sigp_catequese`.`catequese_catequista_sacramento` (`id_catequista`, `id_sacramento`, `data_conclusao_sacramento`, `paroquia`) VALUES ('1', '4', '2021/05/06', 'São Paulo');

INSERT INTO `sigp_catequese`.`catequese_catequista_sacramento` (`id_catequista`, `id_sacramento`, `data_conclusao_sacramento`, `paroquia`) VALUES ('2', '1', '2020/03/12', 'Sagrada Familia');
INSERT INTO `sigp_catequese`.`catequese_catequista_sacramento` (`id_catequista`, `id_sacramento`, `data_conclusao_sacramento`, `paroquia`) VALUES ('2', '2', '2020/03/12', 'São Domingos');
INSERT INTO `sigp_catequese`.`catequese_catequista_sacramento` (`id_catequista`, `id_sacramento`) VALUES ('2', '3');


