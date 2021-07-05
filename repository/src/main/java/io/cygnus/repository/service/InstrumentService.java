package io.cygnus.repository.service;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import io.cygnus.repository.dao.InstrumentDao;
import io.cygnus.repository.dao.InstrumentSettlementDao;
import io.cygnus.repository.entity.InstrumentEntity;
import io.cygnus.repository.entity.InstrumentSettlementEntity;

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
