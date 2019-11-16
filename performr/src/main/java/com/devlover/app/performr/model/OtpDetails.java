package com.devlover.app.performr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@Entity
@Table(name = "otp_details")
public class OtpDetails implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;

	@Column
	String otp;

	@Column
	@JsonIgnore
	long userid;
	
	@Column
	@JsonIgnore
	Date createddate;
	
	@Column
	@JsonIgnore
	Date updateddate;
	
	@Column 
	@JsonIgnore
	int attempts;

	@JsonIgnore
	public Date getCreateddate() {
		return createddate;
	}

	@JsonProperty
	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	@JsonIgnore
	public Date getUpdateddate() {
		return updateddate;
	}

	@JsonProperty
	public void setUpdateddate(Date updateddate) {
		this.updateddate = updateddate;
	}
	@JsonIgnore
	public long getUserid() {
		return userid;
	}

	@JsonProperty
	public void setUserid(long userid) {
		this.userid = userid;
	}
	@JsonIgnore
	public int getAttempts() {
		return attempts;
	}
	@JsonProperty
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	
	
}
