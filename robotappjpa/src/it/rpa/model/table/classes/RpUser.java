package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the RP_USER database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RP_USER", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpUser.findAll", query = "SELECT r FROM RpUser r"),
		@NamedQuery(name = "RpUser.findUsersSubProfileByCode", query = "SELECT r FROM RpUser r WHERE r.rpEnablingCode.id LIKE :enablingCode AND r.subProfile = true"),
		@NamedQuery(name = "RpUser.findUserSubProfileByCode", query = "SELECT r FROM RpUser r WHERE r.rpEnablingCode.id LIKE :enablingCode AND r.id LIKE :user AND r.subProfile = true"), })

public class RpUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private String id;

	@ManyToOne
	@JoinColumn(name = "FK_ID_OFFICE", nullable = false)
	private RpOffice rpOffice;

	@ManyToOne
	@JoinColumn(name = "FK_ID_CODE")
	private RpEnablingCode rpEnablingCode;

	@Column(name = "SUB_PROFILE")
	private boolean subProfile;

	public RpUser() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public RpOffice getRpOffice() {
		return rpOffice;
	}

	public void setRpOffice(RpOffice rpOffice) {
		this.rpOffice = rpOffice;
	}

	public RpEnablingCode getRpEnablingCode() {
		return rpEnablingCode;
	}

	public void setRpEnablingCode(RpEnablingCode rpEnablingCode) {
		this.rpEnablingCode = rpEnablingCode;
	}

	public boolean isSubProfile() {
		return subProfile;
	}

	public void setSubProfile(boolean subProfile) {
		this.subProfile = subProfile;
	}

}