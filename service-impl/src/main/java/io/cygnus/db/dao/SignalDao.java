package io.cygnus.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import io.cygnus.db.CommonDaoFactory;
import io.cygnus.service.entity.Signal;
import io.cygnus.service.entity.SignalParam;
import io.cygnus.service.entity.SignalSymbol;

public class SignalDao {

	public List<Signal> getAllSignal() {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Signal> list = session.createCriteria(Signal.class).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<Signal> getSignalById(Integer signalId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<Signal> list = session.createCriteria(Signal.class)
				.add(Restrictions.eq(Signal.COLUMN_NAME_SignalID, signalId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<SignalParam> getParamsBySignalId(Integer signalId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<SignalParam> list = session.createCriteria(SignalParam.class)
				.add(Restrictions.eq(Signal.COLUMN_NAME_SignalID, signalId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

	public List<SignalSymbol> getSymbolBySignalId(Integer signalId) {
		Session session = CommonDaoFactory.getSession();
		@SuppressWarnings({ "unchecked", "deprecation" })
		List<SignalSymbol> list = session.createCriteria(SignalSymbol.class)
				.add(Restrictions.eq(SignalSymbol.COLUMN_NAME_SignalID, signalId)).list();
		CommonDaoFactory.close(session);
		return list;
	}

}
