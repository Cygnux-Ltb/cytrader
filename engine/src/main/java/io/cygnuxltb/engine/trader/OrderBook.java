package io.cygnuxltb.engine.trader;

import io.horizon.trader.order.Order;
import io.horizon.trader.order.enums.OrdStatus.OrdStatusException;
import io.mercury.common.collections.Capacity;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import javax.annotation.Nullable;

import static io.mercury.common.collections.MutableMaps.newLongObjectHashMap;

/**
 * 用于存储订单的组件
 *
 * @author yellow013
 */
public final class OrderBook {

    /*
     * 存储本OrderBook里的所有订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<Order> orderMap;

    /*
     * 存储本OrderBook里的所有long订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<Order> longOrderMap;

    /*
     * 存储本OrderBook里的所有short订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<Order> shortOrderMap;

    /*
     * 存储本OrderBook里的所有活动状态的订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<Order> activeOrderMap;

    /*
     * 存储本OrderBook里的所有活动状态的long订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<Order> activeLongOrderMap;

    /*
     * 存储本OrderBook里的所有活动状态的short订单, 以ordSysId索引
     */
    private final MutableLongObjectMap<Order> activeShortOrderMap;

    /**
     * Use default Capacity.L07_SIZE, Size == 128
     */
    OrderBook() {
        this(Capacity.L07_SIZE);
    }

    /**
     * @param capacity Capacity
     */
    OrderBook(Capacity capacity) {
        this.orderMap = newLongObjectHashMap(capacity.value());
        this.longOrderMap = newLongObjectHashMap(capacity.half().value());
        this.shortOrderMap = newLongObjectHashMap(capacity.half().value());
        this.activeOrderMap = newLongObjectHashMap(capacity.quarter().value());
        this.activeLongOrderMap = newLongObjectHashMap(capacity.quarter().value());
        this.activeShortOrderMap = newLongObjectHashMap(capacity.quarter().value());
    }

    public MutableLongObjectMap<Order> getOrderMap() {
        return orderMap;
    }

    public MutableLongObjectMap<Order> getLongOrderMap() {
        return longOrderMap;
    }

    public MutableLongObjectMap<Order> getShortOrderMap() {
        return shortOrderMap;
    }

    public MutableLongObjectMap<Order> getActiveOrderMap() {
        return activeOrderMap;
    }

    public MutableLongObjectMap<Order> getActiveLongOrderMap() {
        return activeLongOrderMap;
    }

    public MutableLongObjectMap<Order> getActiveShortOrderMap() {
        return activeShortOrderMap;
    }

    /**
     * @param order Order
     * @return Order return self
     */
    public Order putOrder(Order order) {
        switch (order.getDirection()) {
            case Long -> {
                longOrderMap.put(order.getOrdSysId(), order);
                activeLongOrderMap.put(order.getOrdSysId(), order);
            }
            case Short -> {
                shortOrderMap.put(order.getOrdSysId(), order);
                activeShortOrderMap.put(order.getOrdSysId(), order);
            }
            default ->
                    throw new IllegalStateException("ordSysId -> [" + order.getOrdSysId() + "], direction is invalid");
        }
        activeOrderMap.put(order.getOrdSysId(), order);
        return orderMap.put(order.getOrdSysId(), order);
    }

    /**
     * @param order Order
     * @return Order
     * @throws OrdStatusException ose
     */
    public Order finishOrder(Order order) throws OrdStatusException {
        switch (order.getDirection()) {
            case Long -> activeLongOrderMap.remove(order.getOrdSysId());
            case Short -> activeShortOrderMap.remove(order.getOrdSysId());
            case Invalid ->
                    throw new OrdStatusException("ordSysId: [" + order.getOrdSysId() + "], direction is invalid.");
        }
        return activeOrderMap.remove(order.getOrdSysId());
    }

    public boolean isContainsOrder(long ordSysId) {
        return orderMap.containsKey(ordSysId);
    }

    @Nullable
    public Order getOrder(long ordSysId) {
        return orderMap.get(ordSysId);
    }

}
