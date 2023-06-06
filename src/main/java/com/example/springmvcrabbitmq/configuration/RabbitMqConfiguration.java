package com.example.springmvcrabbitmq.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Value("${sq.rabbit.queue.name}")
    private String queueName;

    @Value("${sq.rabbit.exchange.name}")
    private String exchangeName;

   @Value("${sq.rabbit.routing.name}")
    private String routingName;

    @Bean
    public Queue firstQueue() {
        return new Queue(queueName,false);
    }
 /*   @Bean
    public Queue secondQueue() {
        return new Queue("secondStepQueue",true);
    }
    @Bean
    public Queue thirdQueue() {
        return new Queue("thirdStepQueue",true);
    } */

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding (final Queue firstQueue, final DirectExchange exchange) {
        return BindingBuilder.bind(firstQueue).to(exchange).with(routingName);
    }
   /* @Bean
    public Binding secondBinding (final Queue secondQueue, final DirectExchange exchange) {
        return BindingBuilder.bind(secondQueue).to(exchange).with("secondRoute");
    }
    @Bean
    public Binding thirdBinding (final Queue thirdQueue, final DirectExchange exchange) {
        return BindingBuilder.bind(thirdQueue).to(exchange).with("thirdRoute");
    }*/

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
