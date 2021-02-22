package io.cygnus.db.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.SymbolInfo;
import io.cygnus.service.entity.SymbolTradingFee;
import io.cygnus.service.entity.SymbolTradingPeriod;
import io.cygnus.service.entity.TradeableInstrument;

public class SymbolDao {

	public List<SymbolInfo> getAllSymbolInfo() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<SymbolInfo> list = session.createCriteria(SymbolInfo.class).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<SymbolInfo> getSymbolInfoByName(String symbol) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<SymbolInfo> list = session.createCriteria(SymbolInfo.class)
				.add(Restrictions.eq(SymbolInfo.COLUMN_NAME_Symbol, symbol)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<SymbolTradingFee> getSymbolTradingFeeByName(String symbol) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<SymbolTradingFee> list = session.createCriteria(SymbolTradingFee.class)
				.add(Restrictions.eq(SymbolTradingFee.COLUMN_NAME_Symbol, symbol)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean putSymbolTradingFeeByName(String symbol, SymbolTradingFee symbolTradingFee) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(SymbolTradingFee.class);
			@SuppressWarnings("unchecked")
			List<SymbolTradingFee> queryResult = criteria
					.add(Restrictions.eq(SymbolTradingFee.COLUMN_NAME_Symbol, symbol)).list();
			if (queryResult.size() == 0) {
				session.save(symbolTradingFee);
			} else {
				SymbolTradingFee queryResultObj = queryResult.get(0);
				symbolTradingFee.setUid(queryResultObj.getUid());
				session.update(symbolTradingFee);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
			}
			return false;
		} finally {
			CommonDaoFactory.close(session);
		}
	}

	public List<SymbolTradingPeriod> getSymbolTradingPeriodByName(String symbol) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<SymbolTradingPeriod> list = session.createCriteria(SymbolTradingPeriod.class)
				.add(Restrictions.eq(SymbolTradingPeriod.COLUMN_NAME_Symbol, symbol)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public boolean putSymbolTradingPeriodByName(String symbol, SymbolTradingPeriod symbolTradingPeriod) {
		Session session = CommonDaoFactory.getSession();
		Transaction transaction = session.beginTransaction();
		try {
			@SuppressWarnings("unchecked")
			List<SymbolTradingPeriod> queryResult = session.createCriteria(SymbolTradingPeriod.class)
					.add(Restrictions.eq(SymbolTradingPeriod.COLUMN_NAME_Symbol, symbol)).list();
			if (queryResult.size() == 0) {
				session.save(symbolTradingPeriod);
			} else {
				SymbolTradingPeriod queryResultObj = queryResult.get(0);
				symbolTradingPeriod.setUid(queryResultObj.getUid());
				session.update(symbolTradingPeriod);
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (null != transaction) {
				transaction.rollback();
			}
			return false;
		} finally {
			CommonDaoFactory.close(session);
		}
	}

	public List<TradeableInstrument> getTradeableInstrument(String symbol, Date tradingDay) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings("unchecked")
		List<TradeableInstrument> list = session.createCriteria(TradeableInstrument.class)
				.add(Restrictions.eq(TradeableInstrument.COLUMN_NAME_Symbol, symbol))
				.add(Restrictions.eq(TradeableInstrument.COLUMN_NAME_TradingDay, tradingDay)).list();
		CommonDaoFactory.close(session);
		return list;
	}

}
