package org.apache.camel.example.transformer.demo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@CsvRecord(separator = ",")
public class Order {
    
    @XmlAttribute
    @DataField(pos = 1)
    private String orderId;
    
    @XmlAttribute
    @DataField(pos = 2)
    private String itemId;

    @XmlAttribute
    @DataField(pos = 3)
    private int quantity;

    public String getOrderId() {
        return orderId;
    }

    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getItemId() {
        return itemId;
    }

    public Order setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }


    @Override
    public String toString() {
        return String.format("Order[orderId='%s', itemId='%s', quantity='%s']", orderId, itemId, quantity);
    }
}
