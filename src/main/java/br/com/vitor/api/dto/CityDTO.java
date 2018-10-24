package br.com.vitor.api.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;

public class CityDTO {
	@NotEmpty(message = "Ibgeid can't be empty")
	@UniqueElements(message = "ibgeid need to be unique")
	private Long ibgeId;
	@NotEmpty(message = "uf can't be empty")
	@Length(max = 2, message = "uf max size is 2")
	private String uf;
	@NotEmpty(message = "name can't be empty")
	@Length(max = 100, message = "name max size is 100")
	private String name;
	private Boolean capital;
	@NotEmpty(message = "longitude can't be empty")
	@Length(max = 20, message = "longitude max size is 20")
	private String lon;
	@NotEmpty(message = "latitude can't be empty")
	@Length(max = 20, message = "latitude max size is 20")
	private String lat;
	@NotEmpty(message = "no accents can't be empty")
	@Length(max = 2, message = "no accents max size is 100")
	private String noAccents;
	private String alternativeNames;
	@NotEmpty(message = "micro region can't be empty")
	@Length(max = 60, message = "micro region max size is 60")
	private String microregion;
	@NotEmpty(message = "meso region can't be empty")
	@Length(max = 60, message = "meso region max size is 60")
	private String mesoregion;

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	@Override
	public String toString() {
		return "CityDTO [ibgeId=" + ibgeId + ", uf=" + uf + ", name=" + name + ", capital=" + capital + ", lon=" + lon
				+ ", lat=" + lat + ", noAccents=" + noAccents + ", alternativeNames=" + alternativeNames
				+ ", microregion=" + microregion + ", mesoregion=" + mesoregion + "]";
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}

}
