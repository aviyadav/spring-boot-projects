package org.apache.camel.example.transformer.demo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderResponse {
    
    @XmlAttribute
    private String orderId;

    @XmlAttribute
    private boolean accepted;

    @XmlAttribute
    private String description;

    public String getOrderId() {
        return orderId;
    }

    public OrderResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public OrderResponse setAccepted(boolean accepted) {
        this.accepted = accepted;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return String.format("OrderResponse[orderId='%s', accepted='%s', description='%s']", orderId, accepted, description);
    }
}
