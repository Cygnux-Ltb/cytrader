package io.cygnux.console.dto.pack;

import io.cygnux.console.dto.StrategySwitch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class OutboxMessage<T> {

    private String title;

    private T content;

    public static void main(String[] args) {

        StrategySwitch strategySwitch1 = new StrategySwitch().setProductId(1).setStrategyId(1).setInstrumentCode("ni")
                .setTradable(true);
        StrategySwitch strategySwitch2 = new StrategySwitch().setProductId(1).setStrategyId(1).setInstrumentCode("rb")
                .setTradable(true);
        StrategySwitch strategySwitch3 = new StrategySwitch().setProductId(1).setStrategyId(2).setInstrumentCode("TA")
                .setTradable(true);
        StrategySwitch strategySwitch4 = new StrategySwitch().setProductId(1).setStrategyId(2).setInstrumentCode("MA")
                .setTradable(true);
        StrategySwitch strategySwitch5 = new StrategySwitch().setProductId(1).setStrategyId(2).setInstrumentCode("cu")
                .setTradable(true);
        StrategySwitch strategySwitch6 = new StrategySwitch().setProductId(1).setStrategyId(3).setInstrumentCode("p")
                .setTradable(true);

        List<StrategySwitch> list = new ArrayList<>();

        Map<String, List<StrategySwitch>> map = new HashMap<>();

        map.put("1", list);

        map.get("1").add(strategySwitch1);
        map.get("1").add(strategySwitch2);
        map.get("1").add(strategySwitch3);
        map.get("1").add(strategySwitch4);
        map.get("1").add(strategySwitch5);
        map.get("1").add(strategySwitch6);

        System.out.println(map);

    }

}
