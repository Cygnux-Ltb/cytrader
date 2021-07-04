package io.cygnus.repository.service;

import io.cygnus.repository.dao.InstrumentDao;
import io.cygnus.repository.dao.InstrumentSettlementDao;
import io.cygnus.repository.entity.InstrumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import io.cygnus.repository.entity.InstrumentSettlementEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.List;

@Component
public final class InstrumentService {

    @Resource
    private InstrumentDao instrumentDao;

    @Resource
    private InstrumentSettlementDao instrumentSettlementDao;

    public InstrumentEntity getInstrument(@Nonnull String instrumentCode) {

        return null;
    }

    public List<InstrumentSettlementEntity> getInstrumentSettlement(int tradingDay, @Nonnull String instrumentCode) {

        return null;
    }

}
