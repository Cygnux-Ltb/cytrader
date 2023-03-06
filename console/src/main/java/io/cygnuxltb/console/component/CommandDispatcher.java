package io.cygnuxltb.console.transport;

import io.cygnuxltb.console.persistence.entity.ParamEntity;
import io.cygnuxltb.console.service.dto.StrategySwitch;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class CommandDispatcher {

    @PostConstruct
    private void init() {
    }


    /**
     *
     */
    public boolean sendControlCommand(StrategySwitch strategySwitch) {
        return false;
    }


    public boolean sendStrategyCommand(StrategySwitch strategySwitch) {
        return false;
    }


    public boolean sendParams(List<ParamEntity> params) {
        return false;
    }

}
