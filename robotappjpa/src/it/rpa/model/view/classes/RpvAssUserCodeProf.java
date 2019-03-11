package it.rpa.model.view.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RPV_ASS_USER_CODE_PROF database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RPV_ASS_USER_CODE_PROF", schema = "WRPA_OWN")
@NamedQueries(value = {
		@NamedQuery(name = "RpvAssUserCodeProf.findAll", query = "SELECT r FROM RpvAssUserCodeProf r"), })
public class RpvAssUserCodeProf implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "FK_ID_CODE")
	private String code;

	@Column(name = "FK_ID_PROF")
	private String profile;

	public RpvAssUserCodeProf() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}