package com.weather.app.configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport{
	 @Bean
	    public CacheManager cacheManager() {
	        return (CacheManager) new ConcurrentMapCacheManager() {
	            @Override
	            protected Cache createConcurrentMapCache(final String name) {
	                return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
	                        // You can customize here the eviction time
	                        .expireAfterWrite(2, TimeUnit.HOURS)
	                        .build()
	                        .asMap(),
	                        false);
	            }
	        };
	    }

	    @Bean
	    public KeyGenerator keyGenerator() {
	        return new SimpleKeyGenerator();
	    }
}
