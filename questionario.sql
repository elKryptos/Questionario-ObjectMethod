-- CREAZIONE TABELLE
	-- INIZIO CREAZIONE TABELLE INDIPENDENTI
CREATE TABLE utente(
	utente_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome_utente VARCHAR(25),
	email VARCHAR(50) UNIQUE,
	password varchar(255),
	indirizzo varchar(255),
	compleanno date,
	is_admin bit DEFAULT 0
);
CREATE TABLE test(
	test_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome varchar(50),
	durata_minuti int
);
CREATE TABLE domanda(
	domanda_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	testo_domanda text,
	punteggio int
);
-- INIZIO CREAZIONE TABELLE DIPENDENTI
CREATE TABLE risposta(
	risposta_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	domanda_id bigint,
	testo_risposta text NOT NULL,
	corretta boolean,
	FOREIGN KEY (domanda_id) REFERENCES domanda(domanda_id) ON DELETE CASCADE
);
CREATE TABLE utente_test(
	utente_test_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	test_id bigint,
	utente_id bigint,
	data_inizio datetime,
	data_fine datetime,
	punteggio_totale int,
	FOREIGN KEY (test_id) REFERENCES test(test_id) ON DELETE CASCADE,
	FOREIGN KEY (utente_id) REFERENCES utente(utente_id) ON DELETE CASCADE
);
CREATE TABLE test_domanda(
	test_domanda_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	domanda_id bigint,
	test_id bigint,
	FOREIGN KEY (domanda_id) REFERENCES domanda(domanda_id) ON DELETE SET NULL,
	FOREIGN KEY (test_id) REFERENCES test(test_id) ON DELETE CASCADE
);
CREATE TABLE risposta_utente(
	risposta_utente_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	utente_test_id bigint,
	risposta_id bigint,
	domanda_id bigint,
    punteggio_ottenuto int,
	FOREIGN KEY (utente_test_id) REFERENCES utente_test(utente_test_id) ON DELETE SET NULL,
	FOREIGN KEY (risposta_id) REFERENCES risposta(risposta_id) ON DELETE SET NULL,
	FOREIGN KEY (domanda_id) REFERENCES domanda(domanda_id) ON DELETE SET NULL
);







