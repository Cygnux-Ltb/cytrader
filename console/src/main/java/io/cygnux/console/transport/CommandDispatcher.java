package io.cygnux.console.transport;

import io.cygnux.console.dto.StrategySwitch;
import io.cygnux.repository.entities.TParam;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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


    public boolean sendParams(List<TParam> params) {
        return false;
    }
}
