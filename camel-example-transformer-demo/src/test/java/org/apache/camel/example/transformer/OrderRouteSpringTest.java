package org.apache.camel.example.transformer;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.example.transformer.demo.Order;
import org.apache.camel.example.transformer.demo.OrderResponse;
import org.apache.camel.spi.DataType;
import org.apache.camel.spi.DataTypeAware;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(CamelSpringRunner.class)
@ContextConfiguration(value = "/META-INF/spring/camel-context.xml", loader = CamelSpringDelegatingTestContextLoader.class)
@MockEndpointsAndSkip("direct:csv")
public class OrderRouteSpringTest {

    @Produce("direct:java")
    protected ProducerTemplate javaProducer;

    @Produce("direct:xml")
    protected ProducerTemplate xmlProducer;

    @Produce("direct:json")
    protected ProducerTemplate jsonProducer;

    @EndpointInject("mock:direct:csv")
    private MockEndpoint mockCsv;

    @Before
    public void before() {
        mockCsv.reset();
    }

    @Test
    public void testJava() throws Exception {
        mockCsv.whenAnyExchangeReceived(new Processor() {
            public void process(Exchange exchange) {
                Object mockBody = exchange.getIn().getBody();
                assertEquals(Order.class, mockBody.getClass());
                Order mockOrder = (Order) mockBody;
                assertEquals("Order-Java-0001", mockOrder.getOrderId());
                assertEquals("MILK", mockOrder.getItemId());
                assertEquals(3, mockOrder.getQuantity());
            }
        });
        mockCsv.setExpectedMessageCount(1);

        Order order = new Order()
                .setOrderId("Order-Java-0001")
                .setItemId("MILK")
                .setQuantity(3);
        Object answer = javaProducer.requestBody(order);
        assertEquals(OrderResponse.class, answer.getClass());
        OrderResponse or = (OrderResponse) answer;
        assertEquals("Order-Java-0001", or.getOrderId());
        assertEquals(true, or.isAccepted());
        assertEquals("Order accepted:[item='MILK' quantity='3']", or.getDescription());
        mockCsv.assertIsSatisfied();
    }

    @Test
    public void testXML() throws Exception {
        mockCsv.whenAnyExchangeReceived(new Processor() {
            public void process(Exchange exchange) {
                Object mockBody = exchange.getIn().getBody();
                assertEquals(Order.class, mockBody.getClass());
                Order mockOrder = (Order) mockBody;
                assertEquals("Order-XML-0001", mockOrder.getOrderId());
                assertEquals("MIKAN", mockOrder.getItemId());
                assertEquals(365, mockOrder.getQuantity());
            }
        });
        mockCsv.setExpectedMessageCount(1);

        String order = "<order orderId=\"Order-XML-0001\" itemId=\"MIKAN\" quantity=\"365\"/>";
        String expectedAnswer = "<orderResponse orderId=\"Order-XML-0001\" accepted=\"true\" description=\"Order accepted:[item='MIKAN' quantity='365']\"/>";
        Exchange answer = xmlProducer.send("direct:xml", ex -> {
            ((DataTypeAware) ex.getIn()).setBody(order, new DataType("xml:XMLOrder"));
        });
        String xml = answer.getMessage().getBody(String.class);
        XMLUnit.compareXML(expectedAnswer, xml);
        mockCsv.assertIsSatisfied();
    }

    @Test
    public void testJSON() throws Exception {
        mockCsv.whenAnyExchangeReceived(new Processor() {
            public void process(Exchange exchange) {
                Object mockBody = exchange.getIn().getBody();
                assertEquals(Order.class, mockBody.getClass());
                Order mockOrder = (Order) mockBody;
                assertEquals("Order-JSON-0001", mockOrder.getOrderId());
                assertEquals("MIZUYO-KAN", mockOrder.getItemId());
                assertEquals(16350, mockOrder.getQuantity());
            }
        });
        mockCsv.setExpectedMessageCount(1);

        String order = "{\"orderId\":\"Order-JSON-0001\", \"itemId\":\"MIZUYO-KAN\", \"quantity\":\"16350\"}";
        OrderResponse expected = new OrderResponse()
                .setAccepted(true)
                .setOrderId("Order-JSON-0001")
                .setDescription("Order accepted:[item='MIZUYO-KAN' quantity='16350']");
        ObjectMapper jsonMapper = new ObjectMapper();
        String expectedJson = jsonMapper.writeValueAsString(expected);
        Exchange answer = jsonProducer.send("direct:json", ex -> {
            ((DataTypeAware) ex.getIn()).setBody(order, new DataType("json"));
        });
        assertEquals(expectedJson, answer.getMessage().getBody(String.class));
        mockCsv.assertIsSatisfied();
    }
}
