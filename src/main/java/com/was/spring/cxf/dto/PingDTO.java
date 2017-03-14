package com.was.spring.cxf.dto;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PING")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@XmlRootElement(name = "ping")
public class PingDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	// Constructors
	public PingDTO() {}
	
	public PingDTO(String message) {
		super();
		this.message = message;
	}
	
	public PingDTO(long id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	
	@Id
	@SequenceGenerator(name = "pingSeq", sequenceName = "PING_SEQUENCE ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pingSeq")
	private long id;

	@XmlAttribute
	@Column(unique=true, name="MESSAGE")
	private String message;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
