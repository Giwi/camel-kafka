## camel-kafka
It aims to be a [Camel](http://camel.apache.org/) component for accessing the [Kafka](http://incubator.apache.org/kafka/) messaging system.

Ok, I'm glad, but how to use it? Let's see the next guts-shaking chapter :

## How to use it
### The configuration part : 
    <camelContext xmlns="http://camel.apache.org/schema/spring">
        <package>org.giwi.camel.kafka.routes</package>
    </camelContext>
    <bean id="kafka">
        <property name="zkConnect" value="localhost:2181" />
    </bean>'


### The hard way :
    import org.apache.camel.builder.RouteBuilder;
    
    public class KafkaTestDrive extends RouteBuilder {
    
        @Override
        public void configure() throws Exception {
            // go !
            from("timer://foo?fixedRate=true&period=5000").setBody(constant("hello from Giwi Softwares")).to("kafka:TOPIC-TEST");
     
            // Recieving
            from("kafka:TOPIC-TEST?groupId=camelTest").log("${body}");
     
        }
    }