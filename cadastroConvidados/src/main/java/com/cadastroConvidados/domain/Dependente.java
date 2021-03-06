package com.cadastroConvidados.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dependente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;
	private String email;
	private Date dataNasc;
	private Integer tipoDependente;
	
	
	@ManyToOne
	@JoinColumn(name="convidado_id")
	private Convidado convidado;
	
	public Dependente(Integer id, String nome, Date date, String email,Integer tipoDependente, Convidado convidado) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNasc = date;
		this.email = email;
		this.tipoDependente = tipoDependente;
		this.convidado = convidado;
	}
	
	public Dependente() {
	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getTipoDependente() {
		return tipoDependente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dependente other = (Dependente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	public void setTipoDependente(Integer tipoDependente) {
		this.tipoDependente = tipoDependente;
	}

}
