package com.sdpzhong.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        template.setConnectionFactory(redisConnectionFactory);

        // 设置key的序列化
        template.setKeySerializer(new StringRedisSerializer());

        // 设置value的序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // 设置Hash Key的序列化
        template.setHashKeySerializer(new StringRedisSerializer());

        // 设置Hash Value的序列化
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}