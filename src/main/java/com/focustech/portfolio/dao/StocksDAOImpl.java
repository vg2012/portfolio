package com.focustech.portfolio.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.skyway.spring.util.dao.AbstractJpaDao;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import com.focustech.portfolio.domain.Stocks;

/**
 * DAO to manage Users entities.
 * 
 */
@Repository("StocksDAO")
@Transactional
public class StocksDAOImpl extends AbstractJpaDao<Stocks> implements StocksDAO {

	/**
	 * Set of entity classes managed by this DAO.  Typically a DAO manages a single entity.
	 *
	 */
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(Arrays.asList(new Class<?>[] { Stocks.class }));

	/**
	 * EntityManager injected by Spring for persistence unit 
	 *
	 */
	@PersistenceContext(unitName = "stocks_db")
	private EntityManager entityManager;

	/**
	 * Instantiates a new UsersDAOImpl
	 *
	 */
	public StocksDAOImpl() {
		super();
	}

	/**
	 * Get the entity manager that manages persistence unit 
	 *
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * Returns the set of entity classes managed by this DAO.
	 *
	 */
	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	/**
	 * findAllStocks
	 *
	 */
	@Transactional
	public Set<Stocks> findAllStocks() throws DataAccessException {

		return findAllStocks(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Stocks> findAllStocks(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllStocks", startResult, maxRows);
		return new LinkedHashSet<Stocks>(query.getResultList());
	}

	/**
	 * JPQL Query - findStockByStockId
	 *
	 */
	@Transactional
	public Stocks findStockByStockId(Integer stockId) throws DataAccessException {

		return findStockByStockId(stockId, -1, -1);
	}

	@Transactional
	public Stocks findStockByStockId(Integer userId, int startResult, int maxRows) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findStockByStockId", userId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	
	/**
	 * Used to determine whether or not to merge the entity or persist the entity when calling Store
	 * @see store
	 * 
	 *
	 */
	public boolean canBeMerged(Stocks entity) {
		return true;
	}

	@Transactional
	public Stocks findStockByTicker(String ticker) throws DataAccessException {
		try {
			return executeQueryByNameSingleResult("findStockByTicker", ticker);
		} catch (NoResultException nre) {
			return null;
		}
	}
}
