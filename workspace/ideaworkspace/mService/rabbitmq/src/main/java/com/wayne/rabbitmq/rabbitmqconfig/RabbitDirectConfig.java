package com.wayne.rabbitmq.rabbitmqconfig;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: provide->exchange->binding->queue->consumer
 * @author: LinWeiQi
 */
@Configuration
public class RabbitDirectConfig {
    public static final String directName = "exchange1";

    @Bean
    Queue queue(){
        return new Queue("myQ");//消息队列
    }

    @Bean
    DirectExchange directExchange(){//exchange
        return new DirectExchange(directName,true,false);//durable重启有效 autoDelete长期不适用是否删除
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");//directExchange:消息提供者将消息加入routingKey的队列中
    }
}
