package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RpAssProcessUserPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FK_ID_USER", nullable = false)
	private String idUser;

	@Column(name = "FK_ID_PROCESS", nullable = false)
	private long idProcess;

	public RpAssProcessUserPK() {
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
		RpAssProcessUserPK other = (RpAssProcessUserPK) obj;
		if (idProcess != other.idProcess) {
			return false;
		}
		if (idUser == null) {
			if (other.idUser != null) {
				return false;
			}
		} else if (!idUser.equals(other.idUser)) {
			return false;
		}
		return true;
	}

}