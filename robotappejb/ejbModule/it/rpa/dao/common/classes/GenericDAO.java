package it.rpa.dao.common.classes;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import it.rpa.dao.exception.classes.DAOException;

@Stateless
public class GenericDAO {

	@PersistenceContext(unitName = "robotappjpa")
	EntityManager entityManager;

	public void delete(final Object persistentObject) throws DAOException {
		try {
			this.entityManager.remove(persistentObject);
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public Collection<?> findAll(final Class<?> classObject) throws DAOException {
		try {
			return this.entityManager.createNamedQuery(classObject.getSimpleName() + ".findAll", classObject) //$NON-NLS-1$
					.getResultList();
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public Collection<?> findByParam(final String queryName, final Class<?> classObject, final String paramName,
			final Object paramObject) throws DAOException {
		try {
			final TypedQuery<?> findByParam = this.entityManager
					.createNamedQuery(classObject.getSimpleName() + "." + queryName, classObject); //$NON-NLS-1$
			findByParam.setParameter(paramName, paramObject);
			return findByParam.getResultList();
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public Collection<?> findByForeignKey(final Object persistentForeignKeyObject, final Class<?> classObject)
			throws DAOException {
		try {
			final TypedQuery<?> findByForeignKeyQuery = this.entityManager
					.createNamedQuery(classObject.getSimpleName() + ".findByForeignKey", classObject); //$NON-NLS-1$
			findByForeignKeyQuery.setParameter("foreignKey", //$NON-NLS-1$
					persistentForeignKeyObject);
			return findByForeignKeyQuery.getResultList();
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public Object findById(final Object persistentIdObject, final Class<?> classObject) throws DAOException {
		try {
			return this.entityManager.find(classObject, persistentIdObject);
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public void insert(final Object persistentObject) throws DAOException {
		try {
			this.entityManager.persist(persistentObject);
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public void insertCollection(final Collection<?> persistentObjects) throws DAOException {
		try {
			for (final Object persistentObject : persistentObjects) {
				this.entityManager.persist(persistentObject);
			}
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public void upsertCollection(final Collection<?> persistentObjects) throws DAOException {
		try {
			for (final Object persistentObject : persistentObjects) {
				this.entityManager.merge(persistentObject);
			}
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public void upsert(final Object persistentObject) throws DAOException {
		try {
			this.entityManager.merge(persistentObject);
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public void deleteCollection(final Collection<?> persistentObjects) throws DAOException {
		try {
			for (final Object persistentObject : persistentObjects) {
				this.entityManager.remove(persistentObject);
			}
		} catch (final PersistenceException persistenceException) {
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			throw new DAOException(runtimeException.getMessage());
		}
	}

	public void deleteById(final Class<?> classObject, final Object id) throws DAOException {

		final TypedQuery<?> findByForeignKeyQuery = this.entityManager
				.createNamedQuery(classObject.getSimpleName() + ".deleteById", classObject).setParameter("id", id);
		try {

			findByForeignKeyQuery.executeUpdate();
		} catch (final PersistenceException persistenceException) {
			persistenceException.printStackTrace();
			throw new DAOException(persistenceException.getMessage());
		} catch (final RuntimeException runtimeException) {
			runtimeException.printStackTrace();
			throw new DAOException(runtimeException.getMessage());
		}

	}
}