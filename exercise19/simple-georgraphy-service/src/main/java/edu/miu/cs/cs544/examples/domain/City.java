package edu.miu.cs.cs544.examples.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class City {
	
	@Id
	@Column(name = "CITY_ID")
	private Integer id;
	
	@Column(name = "CITY")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID")
	private Country country;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
