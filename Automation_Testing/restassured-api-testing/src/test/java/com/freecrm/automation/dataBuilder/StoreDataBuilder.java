package com.freecrm.automation.dataBuilder;

import com.freecrm.automation.apiEngine.model.Order;
import com.freecrm.automation.enums.OrderStatus;

public class StoreDataBuilder {

    private StoreDataBuilder() {}

    public static Order buildNewOrder(long order_id, long pet_id) {
        return Order.builder()
                .id(order_id)
                .petId(pet_id)
                .quantity(1)
                .shipDate("2026-04-15T10:00:00.000Z")
                .status(OrderStatus.placed)
                .complete(false)
                .build();
    }
}
