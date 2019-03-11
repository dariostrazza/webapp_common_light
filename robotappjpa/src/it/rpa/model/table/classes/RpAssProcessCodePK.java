package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RpAssProcessCodePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FK_ID_CODE", nullable = false)
	private String idCode;

	@Column(name = "FK_ID_PROCESS", nullable = false)
	private long idProcess;

	public RpAssProcessCodePK() {
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode;
	}

	public long getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(long idProcess) {
		this.idProcess = idProcess;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idProcess ^ (idProcess >>> 32));
		result = prime * result + ((idCode == null) ? 0 : idCode.hashCode());
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
		RpAssProcessCodePK other = (RpAssProcessCodePK) obj;
		if (idProcess != other.idProcess) {
			return false;
		}
		if (idCode == null) {
			if (other.idCode != null) {
				return false;
			}
		} else if (!idCode.equals(other.idCode)) {
			return false;
		}
		return true;
	}

}