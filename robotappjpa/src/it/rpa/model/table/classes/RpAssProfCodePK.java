package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RpAssProfCodePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FK_ID_CODE", nullable = false)
	private String idCode;

	@Column(name = "FK_ID_PROF", nullable = false)
	private String idProf;

	public RpAssProfCodePK() {
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public String getIdProf() {
		return idProf;
	}

	public void setIdProf(String idProf) {
		this.idProf = idProf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCode == null) ? 0 : idCode.hashCode());
		result = prime * result + ((idProf == null) ? 0 : idProf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RpAssProfCodePK other = (RpAssProfCodePK) obj;
		if (idCode == null) {
			if (other.idCode != null) {
				return false;
			}
		} else if (!idCode.equals(other.idCode)) {
			return false;
		}
		if (idProf == null) {
			if (other.idProf != null) {
				return false;
			}
		} else if (!idProf.equals(other.idProf)) {
			return false;
		}
		return true;
	}

}