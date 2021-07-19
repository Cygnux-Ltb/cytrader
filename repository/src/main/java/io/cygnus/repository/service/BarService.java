package io.cygnus.repository.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;

import io.cygnus.repository.db.CommonDaoFactory;
import io.cygnus.repository.entity.BarEntity;
import io.mercury.common.log.CommonLoggerFactory;

public class BarService {

    private static final Logger logger = CommonLoggerFactory.getLogger(BarService.class);

    public List<BarEntity> getTimeBinners(Integer cygId, Date dateTradingDay, String instrumentCode) {
        Session session = CommonDaoFactory.getSession();
        @SuppressWarnings({"unchecked", "deprecation"})
        List<BarEntity> list = session.createCriteria(BarEntity.class).add(Restrictions.eq(BarEntity.COLUMN_CygId, cygId))
                .add(Restrictions.eq(BarEntity.COLUMN_TradingDay, dateTradingDay))
                .add(Restrictions.eq(BarEntity.COLUMN_InstrumentCode, instrumentCode)).list();
        CommonDaoFactory.close(session);
        return list;
    }

    public boolean addTimeBinners(BarEntity timeBinner) {
        Session session = CommonDaoFactory.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            @SuppressWarnings({"unchecked", "deprecation"})
            List<BarEntity> queryForFieldValues = session.createCriteria(BarEntity.class)
                    .add(Restrictions.eq(BarEntity.COLUMN_CygId, timeBinner.getCygId()))
                    .add(Restrictions.eq(BarEntity.COLUMN_TradingDay, timeBinner.getTradingDay()))
                    .add(Restrictions.eq(BarEntity.COLUMN_TimePoint, timeBinner.getTimePoint()))
                    .add(Restrictions.eq(BarEntity.COLUMN_InstrumentCode, timeBinner.getInstrumentCode())).list();
            if (queryForFieldValues.size() > 0) {
                // logger.error("Repeat consumption -> " + JSON.toJSONString(timeBinner));
                logger.error("Repeat consumption -> {}", timeBinner.toString());
                return true;
            }
            session.save(timeBinner);
            transaction.commit();
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
        } finally {
            CommonDaoFactory.close(session);
        }
        return true;
    }

}
