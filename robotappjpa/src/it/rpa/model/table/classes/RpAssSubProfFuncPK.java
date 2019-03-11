package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RpAssSubProfFuncPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FK_ID_FUNC", nullable = false)
	private String idFunc;

	@Column(name = "SUB_PROF", nullable = false)
	private String idProf;

	public RpAssSubProfFuncPK() {
	}

	public String getIdFunc() {
		return idFunc;
	}

	public void setIdFunc(String idFunc) {
		this.idFunc = idFunc;
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
		result = prime * result + ((idFunc == null) ? 0 : idFunc.hashCode());
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
		RpAssSubProfFuncPK other = (RpAssSubProfFuncPK) obj;
		if (idFunc == null) {
			if (other.idFunc != null) {
				return false;
			}
		} else if (!idFunc.equals(other.idFunc)) {
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