package it.rpa.model.view.classes;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import it.rpa.model.table.classes.RpAssProfCodePK;

/**
 * The persistent class for the RP_ASS_PROF_CODE database table.
 * 
 */
@Cacheable(false)
@Entity
@Table(name = "RPV_ASS_PROF_CODE", schema = "WRPA_OWN")
@NamedQueries(value = { @NamedQuery(name = "RpvAssProfCode.findAll", query = "SELECT r FROM RpvAssProfCode r"),
		@NamedQuery(name = "RpvAssProfCode.findProfbyCode", query = "SELECT r FROM RpvAssProfCode r WHERE r.id.idCode LIKE :code"), })
public class RpvAssProfCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RpAssProfCodePK id;

	@Column(name = "CODE_DESCRIPTION")
	private String codeDescription;

	@Column(name = "PROFILE_DESCRIPTION")
	private String profileDescription;

	@Column(name = "BUSINESS_OWNER")
	private String businessOwner;

	public RpvAssProfCode() {
	}

	public RpAssProfCodePK getId() {
		return this.id;
	}

	public void setId(RpAssProfCodePK id) {
		this.id = id;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public String getProfileDescription() {
		return profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	public String getBusinessOwner() {
		return businessOwner;
	}

	public void setBusinessOwner(String businessOwner) {
		this.businessOwner = businessOwner;
	}

}