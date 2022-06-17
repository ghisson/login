package com.flush.FlushVideo.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tb_sta_stanza_cl")
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long STA_ID_STANZA_PK;

	@Column(name = "STA_NOME")
	private String nome;

	@Column(name = "STA_STATUS")
	private String status;

	@Column(name = "STA_FLAG_STATUS")
	private String flagstatus;

	@Column(name = "STA_UTE_ID_UTENTE_PK")
	private int idutente;

	@Column(name = "STA_DATA_AGGIORN")
	private Date dataagg;

	@Column(name = "STA_DATA_INIZIO")
	private Date datainizio;

	@Column(name = "STA_DATA_FINE")
	private Date datafine;

	public Room() {

	}
	
	public Room(long sTA_ID_STANZA_PK, String nome, String status, String flagstatus, int idutente, Date dataagg,
			Date datainizio, Date datafine) {
		super();
		STA_ID_STANZA_PK = sTA_ID_STANZA_PK;
		this.nome = nome;
		this.status = status;
		this.flagstatus = flagstatus;
		this.idutente = idutente;
		this.dataagg = dataagg;
		this.datainizio = datainizio;
		this.datafine = datafine;
	}

	public long getSTA_ID_STANZA_PK() {
		return STA_ID_STANZA_PK;
	}

	public void setSTA_ID_STANZA_PK(long sTA_ID_STANZA_PK) {
		STA_ID_STANZA_PK = sTA_ID_STANZA_PK;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlagstatus() {
		return flagstatus;
	}

	public void setFlagstatus(String flagstatus) {
		this.flagstatus = flagstatus;
	}

	public int getIdutente() {
		return idutente;
	}

	public void setIdutente(int idutente) {
		this.idutente = idutente;
	}

	public Date getDataagg() {
		return dataagg;
	}

	public void setDataagg(Date dataagg) {
		this.dataagg = dataagg;
	}

	public Date getDatainizio() {
		return datainizio;
	}

	public void setDatainizio(Date datainizio) {
		this.datainizio = datainizio;
	}

	public Date getDatafine() {
		return datafine;
	}

	public void setDatafine(Date datafine) {
		this.datafine = datafine;
	}

	@Override
	public String toString() {
		return "Room [STA_ID_STANZA_PK=" + STA_ID_STANZA_PK + ", nome=" + nome + ", status=" + status + ", flagstatus="
				+ flagstatus + ", idutente=" + idutente + ", dataagg=" + dataagg + ", datainizio=" + datainizio
				+ ", datafine=" + datafine + "]";
	}

	

	}