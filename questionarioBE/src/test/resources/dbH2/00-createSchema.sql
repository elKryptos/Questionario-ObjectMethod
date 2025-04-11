CREATE TABLE domanda(
	domanda_id bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
	testo_domanda text,
	punteggio int
);

CREATE TABLE risposta(
	risposta_id bigint AUTO_INCREMENT PRIMARY KEY,
	domanda_id bigint,
	testo_risposta text NOT NULL,
	corretta boolean,
	FOREIGN KEY (domanda_id) REFERENCES domanda(domanda_id)
);

CREATE TABLE utente(
	utente_id bigint AUTO_INCREMENT PRIMARY KEY,
	nome_utente VARCHAR(25),
	email VARCHAR(50) UNIQUE,
	password varchar(255),
	indirizzo varchar(255),
	compleanno date
);