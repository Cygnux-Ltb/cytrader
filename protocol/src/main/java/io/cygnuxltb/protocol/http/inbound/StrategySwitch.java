package io.cygnuxltb.protocol.http.inbound;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@Accessors(chain = true)
public class StrategySwitch implements Comparable<StrategySwitch> {

    private int productId;

    private int strategyId;

    private String instrumentCode;

    private boolean tradable;

    public String getKey() {
        return productId + "-" + strategyId + "-" + instrumentCode;
    }

    @Override
    public int compareTo(StrategySwitch o) {
        if (o.productId == this.productId) {
            if (o.strategyId == this.strategyId) {
                if (o.instrumentCode.equals(this.instrumentCode)) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public static void main(String[] args) {

        StrategySwitch strategySwitch1 = new StrategySwitch().setProductId(1).setStrategyId(1).setInstrumentCode("au1706");
        StrategySwitch strategySwitch2 = new StrategySwitch().setProductId(1).setStrategyId(1).setInstrumentCode("au1706");
        StrategySwitch strategySwitch3 = new StrategySwitch().setProductId(1).setStrategyId(1).setInstrumentCode("au1706");
        StrategySwitch strategySwitch4 = new StrategySwitch().setProductId(1).setStrategyId(1).setInstrumentCode("au1706");

        Set<StrategySwitch> set = new TreeSet<>();

        set.add(strategySwitch1);
        set.add(strategySwitch2);
        set.add(strategySwitch3);
        set.add(strategySwitch4);

        for (StrategySwitch strategySwitch : set) {
            System.out.println(strategySwitch.getProductId() + "-" + strategySwitch.getStrategyId() + "-"
                    + strategySwitch.getInstrumentCode());
        }

    }
}
