--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

-- Started on 2021-09-28 20:16:30

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 40454)
-- Name: tbl_amigo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_amigo (
    id_amigo bigint NOT NULL,
    e_amigo character varying(1) NOT NULL,
    data_atualizado timestamp without time zone,
    nome_amigo character varying(100) NOT NULL,
    id_tipo_amigo bigint NOT NULL
);


ALTER TABLE tbl_amigo OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 40459)
-- Name: tbl_funcionalidade_modulo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_funcionalidade_modulo (
    id_funcionalidade_modulo bigint NOT NULL,
    codg_mnemonico_funcionalidade character varying(40) NOT NULL,
    nome_funcionalidade_modulo character varying(200) NOT NULL,
    stat_funcionalidade_modulo character varying(1) NOT NULL,
    id_modulo_sistema bigint NOT NULL
);


ALTER TABLE tbl_funcionalidade_modulo OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 40464)
-- Name: tbl_grupo_funcionalidade; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_grupo_funcionalidade (
    id_grupo_funcionalidade bigint NOT NULL,
    id_funcionalidade_modulo bigint NOT NULL,
    id_grupo_usuario bigint NOT NULL
);


ALTER TABLE tbl_grupo_funcionalidade OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 40469)
-- Name: tbl_grupo_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_grupo_usuario (
    id_grupo_usuario bigint NOT NULL,
    flag_grupo_admin_usuario character varying(1) NOT NULL,
    desc_grupo_usuario character varying(200),
    nome_grupo_usuario character varying(50),
    stat_grupo_usuario character varying(1) NOT NULL
);


ALTER TABLE tbl_grupo_usuario OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 40474)
-- Name: tbl_modulo_sistema; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_modulo_sistema (
    id_modulo_sistema bigint NOT NULL,
    codg_mnemonico_modulo character varying(40) NOT NULL,
    nome_modulo_sistema character varying(200) NOT NULL,
    stat_modulo_sistema character varying(1) NOT NULL
);


ALTER TABLE tbl_modulo_sistema OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 40499)
-- Name: tbl_s_amigo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_amigo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_amigo OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 40501)
-- Name: tbl_s_func_modulo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_func_modulo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_func_modulo OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 40503)
-- Name: tbl_s_grupo_funcionalidade; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_grupo_funcionalidade
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_grupo_funcionalidade OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 40505)
-- Name: tbl_s_grupo_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_grupo_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_grupo_usuario OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 40507)
-- Name: tbl_s_modulo_sistema; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_modulo_sistema
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_modulo_sistema OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 40509)
-- Name: tbl_s_telefone_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_telefone_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_telefone_usuario OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 40511)
-- Name: tbl_s_tipo_amigo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_tipo_amigo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_tipo_amigo OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 40513)
-- Name: tbl_s_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_usuario OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 40515)
-- Name: tbl_s_usuario_grupo; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tbl_s_usuario_grupo
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tbl_s_usuario_grupo OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 40479)
-- Name: tbl_telefone_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_telefone_usuario (
    codg_telefone_usuario bigint NOT NULL,
    ddd_telefone_usuario bigint,
    numr_telefone_usuario character varying(11) NOT NULL,
    tipo_telefone_usuario bigint NOT NULL,
    codg_usuario bigint NOT NULL
);


ALTER TABLE tbl_telefone_usuario OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 40484)
-- Name: tbl_tipo_amigo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_tipo_amigo (
    id_tipo_amigo bigint NOT NULL,
    nome_tipo_amigo character varying(100) NOT NULL
);


ALTER TABLE tbl_tipo_amigo OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 40489)
-- Name: tbl_usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_usuario (
    codg_usuario bigint NOT NULL,
    stat_bloqueio_acesso character varying(1),
    stat_expirado_acesso character varying(1),
    numr_cpf character varying(14) NOT NULL,
    data_expirado_acesso timestamp without time zone,
    data_atualizado timestamp without time zone NOT NULL,
    data_cadastrado timestamp without time zone NOT NULL,
    email character varying(75) NOT NULL,
    login_usuario character varying(20) NOT NULL,
    nome_usuario character varying(65) NOT NULL,
    qtde_acesso numeric(2,0),
    cont_tentativa_acesso character varying(1),
    senha_usuario character varying(255) NOT NULL,
    stat_usuario character varying(1) NOT NULL,
    data_ultimo_acesso timestamp without time zone
);


ALTER TABLE tbl_usuario OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 40494)
-- Name: tbl_usuario_grupo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE tbl_usuario_grupo (
    id_usuario_grupo bigint NOT NULL,
    id_grupo_usuario bigint NOT NULL,
    codg_usuario bigint NOT NULL
);


ALTER TABLE tbl_usuario_grupo OWNER TO postgres;

--
-- TOC entry 2864 (class 0 OID 40454)
-- Dependencies: 196
-- Data for Name: tbl_amigo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_amigo VALUES (1, 'S', '2021-09-28 00:00:00', 'Primeiro Amigo', 1);
INSERT INTO tbl_amigo VALUES (2, 'S', '2021-09-28 00:00:00', 'Primeiro Conhecido', 2);


--
-- TOC entry 2865 (class 0 OID 40459)
-- Dependencies: 197
-- Data for Name: tbl_funcionalidade_modulo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_funcionalidade_modulo VALUES (1, 'ATIVAR_INATIVAR', 'Ativar/Inativar', 'A', 1);
INSERT INTO tbl_funcionalidade_modulo VALUES (2, 'ALTERAR', 'Alterar', 'A', 1);
INSERT INTO tbl_funcionalidade_modulo VALUES (3, 'VISUALIZAR', 'Visualizar', 'A', 1);
INSERT INTO tbl_funcionalidade_modulo VALUES (4, 'INCLUIR', 'Incluir', 'A', 1);
INSERT INTO tbl_funcionalidade_modulo VALUES (5, 'PESQUISAR', 'Pesquisar', 'A', 1);
INSERT INTO tbl_funcionalidade_modulo VALUES (6, 'ATIVAR_INATIVAR', 'Ativar/Inativar', 'A', 2);
INSERT INTO tbl_funcionalidade_modulo VALUES (7, 'ALTERAR', 'Alterar', 'A', 2);
INSERT INTO tbl_funcionalidade_modulo VALUES (8, 'VISUALIZAR', 'Visualizar', 'A', 2);
INSERT INTO tbl_funcionalidade_modulo VALUES (9, 'INCLUIR', 'Incluir', 'A', 2);
INSERT INTO tbl_funcionalidade_modulo VALUES (10, 'PESQUISAR', 'Pesquisar', 'A', 2);
INSERT INTO tbl_funcionalidade_modulo VALUES (11, 'REMOVER', 'Remover', 'A', 3);
INSERT INTO tbl_funcionalidade_modulo VALUES (12, 'ALTERAR', 'Alterar', 'A', 3);
INSERT INTO tbl_funcionalidade_modulo VALUES (13, 'VISUALIZAR', 'Visualizar', 'A', 3);
INSERT INTO tbl_funcionalidade_modulo VALUES (14, 'INCLUIR', 'Incluir', 'A', 3);
INSERT INTO tbl_funcionalidade_modulo VALUES (15, 'PESQUISAR', 'Pesquisar', 'A', 3);
INSERT INTO tbl_funcionalidade_modulo VALUES (16, 'REMOVER', 'Remover', 'A', 4);
INSERT INTO tbl_funcionalidade_modulo VALUES (17, 'ALTERAR', 'Alterar', 'A', 4);
INSERT INTO tbl_funcionalidade_modulo VALUES (18, 'VISUALIZAR', 'Visualizar', 'A', 4);
INSERT INTO tbl_funcionalidade_modulo VALUES (19, 'INCLUIR', 'Incluir', 'A', 4);
INSERT INTO tbl_funcionalidade_modulo VALUES (20, 'PESQUISAR', 'Pesquisar', 'A', 4);
INSERT INTO tbl_funcionalidade_modulo VALUES (21, 'STATUS', 'É Amigo', 'A', 4);


--
-- TOC entry 2866 (class 0 OID 40464)
-- Dependencies: 198
-- Data for Name: tbl_grupo_funcionalidade; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_grupo_funcionalidade VALUES (1, 19, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (2, 14, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (3, 9, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (4, 17, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (5, 6, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (6, 11, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (7, 1, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (8, 16, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (9, 20, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (10, 12, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (11, 13, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (12, 4, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (13, 7, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (14, 18, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (15, 2, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (16, 8, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (17, 21, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (18, 3, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (19, 15, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (20, 10, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (21, 5, 1);
INSERT INTO tbl_grupo_funcionalidade VALUES (22, 18, 2);
INSERT INTO tbl_grupo_funcionalidade VALUES (23, 20, 2);
INSERT INTO tbl_grupo_funcionalidade VALUES (24, 13, 2);
INSERT INTO tbl_grupo_funcionalidade VALUES (25, 15, 2);
INSERT INTO tbl_grupo_funcionalidade VALUES (26, 2, 3);
INSERT INTO tbl_grupo_funcionalidade VALUES (27, 5, 3);
INSERT INTO tbl_grupo_funcionalidade VALUES (28, 1, 3);
INSERT INTO tbl_grupo_funcionalidade VALUES (29, 3, 3);
INSERT INTO tbl_grupo_funcionalidade VALUES (30, 4, 3);
INSERT INTO tbl_grupo_funcionalidade VALUES (31, 20, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (32, 13, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (33, 11, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (34, 21, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (35, 18, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (36, 16, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (37, 14, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (38, 12, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (39, 17, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (40, 19, 4);
INSERT INTO tbl_grupo_funcionalidade VALUES (41, 15, 4);


--
-- TOC entry 2867 (class 0 OID 40469)
-- Dependencies: 199
-- Data for Name: tbl_grupo_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_grupo_usuario VALUES (1, 'S', 'Grupo de Administradores', 'Administradores', 'A');
INSERT INTO tbl_grupo_usuario VALUES (2, 'N', 'Usuários gerais do sistema', 'Usuário Comum', 'A');
INSERT INTO tbl_grupo_usuario VALUES (3, 'N', 'Grupo de usuários que podem manter Usuário no sistema.', 'Admin Usuários', 'A');
INSERT INTO tbl_grupo_usuario VALUES (4, 'N', 'Usuário administrador do sistema de amigos.', 'Usuário Sistema Amigos', 'A');


--
-- TOC entry 2868 (class 0 OID 40474)
-- Dependencies: 200
-- Data for Name: tbl_modulo_sistema; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_modulo_sistema VALUES (1, 'USUARIO', 'Manter Usuário', 'A');
INSERT INTO tbl_modulo_sistema VALUES (2, 'GRUPO', 'Manter Grupo', 'A');
INSERT INTO tbl_modulo_sistema VALUES (3, 'TIPOAMIGO', 'Manter Tipo Amigo ', 'A');
INSERT INTO tbl_modulo_sistema VALUES (4, 'AMIGO', 'Manter Amigo ', 'A');


--
-- TOC entry 2869 (class 0 OID 40479)
-- Dependencies: 201
-- Data for Name: tbl_telefone_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2870 (class 0 OID 40484)
-- Dependencies: 202
-- Data for Name: tbl_tipo_amigo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_tipo_amigo VALUES (1, 'Amigo');
INSERT INTO tbl_tipo_amigo VALUES (2, 'Conhecido');
INSERT INTO tbl_tipo_amigo VALUES (3, 'Melhor Amigo');


--
-- TOC entry 2871 (class 0 OID 40489)
-- Dependencies: 203
-- Data for Name: tbl_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_usuario VALUES (1, NULL, NULL, '10010010017', NULL, '2021-09-28 00:00:00', '2021-09-28 00:00:00', 'admin@teste.com.br', 'admin', 'Administrador', NULL, NULL, '$2a$10$uHg5A/aD3hBUUCnx2XIL0OhmaiWXGntU6elimy4j7xWMVFB16d4Xy', 'A', NULL);
INSERT INTO tbl_usuario VALUES (2, 'N', 'N', '10000000019', NULL, '2021-09-28 00:00:00', '2021-09-28 00:00:00', 'usercomum@comum.com.br', 'usercomum1', 'Usuário Comum 1', NULL, NULL, '$2a$10$reylC1ZrrXnMhkBPpsmCh.iFHsicrS0xvPebPehx8uGY78hZKKlO.', 'A', NULL);
INSERT INTO tbl_usuario VALUES (3, 'N', 'N', '20000000027', NULL, '2021-09-28 00:00:00', '2021-09-28 00:00:00', 'usercomum2@comum.com.br', 'usercomum2', 'Usuário comum 2', NULL, NULL, '$2a$10$dY8ql2WPB55B6K6B1YESeOkT0lFEJePhhcwge59IQWqLPTkuqYaqa', 'A', NULL);
INSERT INTO tbl_usuario VALUES (4, 'N', 'N', '30000000035', NULL, '2021-09-28 00:00:00', '2021-09-28 00:00:00', 'usercomum3@comum.com.br', 'usercomum3', 'Usuário Comm 3', NULL, NULL, '$2a$10$Mq8Chpg2vXngIz5v/tbTbu3ePJ5Sl1v1ORHCXu8lkiTcQGRi7sCXK', 'A', NULL);
INSERT INTO tbl_usuario VALUES (5, 'N', 'N', '40000000043', NULL, '2021-09-28 00:00:00', '2021-09-28 00:00:00', 'adminamigo@comum.com.br', 'adminamigo', 'Admin do sistema de Amigo', NULL, NULL, '$2a$10$CqrCGoJRn1V1jcJKf8LIqO/hL.9OFg.PJevqs6aDavt9vFIxjgoQK', 'A', NULL);


--
-- TOC entry 2872 (class 0 OID 40494)
-- Dependencies: 204
-- Data for Name: tbl_usuario_grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tbl_usuario_grupo VALUES (1, 1, 1);
INSERT INTO tbl_usuario_grupo VALUES (2, 2, 2);
INSERT INTO tbl_usuario_grupo VALUES (3, 2, 3);
INSERT INTO tbl_usuario_grupo VALUES (4, 2, 4);
INSERT INTO tbl_usuario_grupo VALUES (5, 4, 5);


--
-- TOC entry 2887 (class 0 OID 0)
-- Dependencies: 205
-- Name: tbl_s_amigo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_amigo', 2, true);


--
-- TOC entry 2888 (class 0 OID 0)
-- Dependencies: 206
-- Name: tbl_s_func_modulo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_func_modulo', 21, true);


--
-- TOC entry 2889 (class 0 OID 0)
-- Dependencies: 207
-- Name: tbl_s_grupo_funcionalidade; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_grupo_funcionalidade', 41, true);


--
-- TOC entry 2890 (class 0 OID 0)
-- Dependencies: 208
-- Name: tbl_s_grupo_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_grupo_usuario', 4, true);


--
-- TOC entry 2891 (class 0 OID 0)
-- Dependencies: 209
-- Name: tbl_s_modulo_sistema; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_modulo_sistema', 4, true);


--
-- TOC entry 2892 (class 0 OID 0)
-- Dependencies: 210
-- Name: tbl_s_telefone_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_telefone_usuario', 1, false);


--
-- TOC entry 2893 (class 0 OID 0)
-- Dependencies: 211
-- Name: tbl_s_tipo_amigo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_tipo_amigo', 3, true);


--
-- TOC entry 2894 (class 0 OID 0)
-- Dependencies: 212
-- Name: tbl_s_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_usuario', 5, true);


--
-- TOC entry 2895 (class 0 OID 0)
-- Dependencies: 213
-- Name: tbl_s_usuario_grupo; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tbl_s_usuario_grupo', 5, true);


--
-- TOC entry 2719 (class 2606 OID 40458)
-- Name: tbl_amigo tbl_amigo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_amigo
    ADD CONSTRAINT tbl_amigo_pkey PRIMARY KEY (id_amigo);


--
-- TOC entry 2721 (class 2606 OID 40463)
-- Name: tbl_funcionalidade_modulo tbl_funcionalidade_modulo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_funcionalidade_modulo
    ADD CONSTRAINT tbl_funcionalidade_modulo_pkey PRIMARY KEY (id_funcionalidade_modulo);


--
-- TOC entry 2723 (class 2606 OID 40468)
-- Name: tbl_grupo_funcionalidade tbl_grupo_funcionalidade_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_grupo_funcionalidade
    ADD CONSTRAINT tbl_grupo_funcionalidade_pkey PRIMARY KEY (id_grupo_funcionalidade);


--
-- TOC entry 2725 (class 2606 OID 40473)
-- Name: tbl_grupo_usuario tbl_grupo_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_grupo_usuario
    ADD CONSTRAINT tbl_grupo_usuario_pkey PRIMARY KEY (id_grupo_usuario);


--
-- TOC entry 2727 (class 2606 OID 40478)
-- Name: tbl_modulo_sistema tbl_modulo_sistema_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_modulo_sistema
    ADD CONSTRAINT tbl_modulo_sistema_pkey PRIMARY KEY (id_modulo_sistema);


--
-- TOC entry 2729 (class 2606 OID 40483)
-- Name: tbl_telefone_usuario tbl_telefone_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_telefone_usuario
    ADD CONSTRAINT tbl_telefone_usuario_pkey PRIMARY KEY (codg_telefone_usuario);


--
-- TOC entry 2731 (class 2606 OID 40488)
-- Name: tbl_tipo_amigo tbl_tipo_amigo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_tipo_amigo
    ADD CONSTRAINT tbl_tipo_amigo_pkey PRIMARY KEY (id_tipo_amigo);


--
-- TOC entry 2735 (class 2606 OID 40498)
-- Name: tbl_usuario_grupo tbl_usuario_grupo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_usuario_grupo
    ADD CONSTRAINT tbl_usuario_grupo_pkey PRIMARY KEY (id_usuario_grupo);


--
-- TOC entry 2733 (class 2606 OID 40493)
-- Name: tbl_usuario tbl_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_usuario
    ADD CONSTRAINT tbl_usuario_pkey PRIMARY KEY (codg_usuario);


--
-- TOC entry 2737 (class 2606 OID 40522)
-- Name: tbl_funcionalidade_modulo fk69wh08in5rvomym8m8e2orjp8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_funcionalidade_modulo
    ADD CONSTRAINT fk69wh08in5rvomym8m8e2orjp8 FOREIGN KEY (id_modulo_sistema) REFERENCES tbl_modulo_sistema(id_modulo_sistema);


--
-- TOC entry 2741 (class 2606 OID 40542)
-- Name: tbl_usuario_grupo fk7u893i5afy0xy7s6923n94so0; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_usuario_grupo
    ADD CONSTRAINT fk7u893i5afy0xy7s6923n94so0 FOREIGN KEY (id_grupo_usuario) REFERENCES tbl_grupo_usuario(id_grupo_usuario);


--
-- TOC entry 2738 (class 2606 OID 40527)
-- Name: tbl_grupo_funcionalidade fk9qffbqv2umgwjv69r9ki29yas; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_grupo_funcionalidade
    ADD CONSTRAINT fk9qffbqv2umgwjv69r9ki29yas FOREIGN KEY (id_funcionalidade_modulo) REFERENCES tbl_funcionalidade_modulo(id_funcionalidade_modulo);


--
-- TOC entry 2740 (class 2606 OID 40537)
-- Name: tbl_telefone_usuario fkf77khq6u245al7569b9ggh5nl; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_telefone_usuario
    ADD CONSTRAINT fkf77khq6u245al7569b9ggh5nl FOREIGN KEY (codg_usuario) REFERENCES tbl_usuario(codg_usuario);


--
-- TOC entry 2739 (class 2606 OID 40532)
-- Name: tbl_grupo_funcionalidade fki5rge57hfbk849l3o19h0hs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_grupo_funcionalidade
    ADD CONSTRAINT fki5rge57hfbk849l3o19h0hs FOREIGN KEY (id_grupo_usuario) REFERENCES tbl_grupo_usuario(id_grupo_usuario);


--
-- TOC entry 2736 (class 2606 OID 40517)
-- Name: tbl_amigo fkimtor199v50k3jt3mtgiexq35; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_amigo
    ADD CONSTRAINT fkimtor199v50k3jt3mtgiexq35 FOREIGN KEY (id_tipo_amigo) REFERENCES tbl_tipo_amigo(id_tipo_amigo);


--
-- TOC entry 2742 (class 2606 OID 40547)
-- Name: tbl_usuario_grupo fkse8ga8xolrna8owasauqfuegj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tbl_usuario_grupo
    ADD CONSTRAINT fkse8ga8xolrna8owasauqfuegj FOREIGN KEY (codg_usuario) REFERENCES tbl_usuario(codg_usuario);


-- Completed on 2021-09-28 20:16:31

--
-- PostgreSQL database dump complete
--

