package it.rpa.model.table.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RpAssSubProfMenuPK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "FK_ID_MENU", nullable = false)
	private String idMenu;

	@Column(name = "SUB_PROF", nullable = false)
	private String idProf;

	public RpAssSubProfMenuPK() {
	}

	public String getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(String idMenu) {
		this.idMenu = idMenu;
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
		result = prime * result + ((idMenu == null) ? 0 : idMenu.hashCode());
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
		RpAssSubProfMenuPK other = (RpAssSubProfMenuPK) obj;
		if (idMenu == null) {
			if (other.idMenu != null) {
				return false;
			}
		} else if (!idMenu.equals(other.idMenu)) {
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