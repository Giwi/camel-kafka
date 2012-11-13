## camel-kafka
It aims to be a [Camel](http://camel.apache.org/) component for accessing the [Kafka](http://incubator.apache.org/kafka/) messaging system.

Ok, I'm glad, but how to use it? Let's see the next guts-shaking chapter :

## How to use it

    import org.apache.camel.builder.RouteBuilder; 
    
    public class KafkaTestDrive extends RouteBuilder {
    
        @Override
        public void configure() throws Exception {
            // go !
           from("timer://foo?fixedRate=true&period=5000")
               .setBody(constant("hello from Giwi Softwares"))
               .to("kafka:TOPIC-TEST?zkConnect=localhost:2181");

	       // Recieving
		   from("kafka:TOPIC-TEST?groupId=camelTest&zkConnect=localhost:2181").log("${body}");
     
        }
    }
    
### Conclusion
Oh Lord, it's tremendously impressive.