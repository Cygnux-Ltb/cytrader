package io.cygnuxltb.console.service.bean;


import io.cygnuxltb.console.persistence.entity.AccountEntity;
import io.cygnuxltb.console.persistence.entity.BarEntity;
import io.cygnuxltb.console.persistence.entity.InstrumentEntity;
import io.cygnuxltb.console.persistence.entity.InstrumentSettlementEntity;
import io.cygnuxltb.protocol.http.outbound.AccountDTO;
import io.cygnuxltb.protocol.http.outbound.BarM1DTO;
import io.cygnuxltb.protocol.http.outbound.InstrumentDTO;
import io.cygnuxltb.protocol.http.outbound.InstrumentSettlementDTO;

public class OutboundConverter {

    public static BarM1DTO toBarDTO(BarEntity entity) {
        return new BarM1DTO().setInstrumentCode(entity.getInstrumentCode())
                .setTradingDay(entity.getTradingDay())
                .setActualDate(entity.getActualDate())
                .setTimePoint(entity.getTimePoint())
                .setOpen(entity.getOpen())
                .setHigh(entity.getHigh())
                .setLow(entity.getLow())
                .setClose(entity.getClose())
                .setVolume(entity.getVolume())
                .setTurnover(entity.getTurnover());
    }

    public static AccountDTO toAccountDTO(AccountEntity entity) {
        return null;
    }

    public static InstrumentDTO toInstrumentDTO(InstrumentEntity entity) {
        return null;
    }


    public static InstrumentSettlementDTO toInstrumentSettlementDTO(InstrumentSettlementEntity entity) {
        return null;
    }


}
