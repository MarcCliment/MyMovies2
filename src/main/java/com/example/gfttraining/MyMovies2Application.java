package com.example.gfttraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.cache.config.EnableGemfireCaching;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableCachingDefinedRegions;
import org.apache.geode.cache.client.ClientRegionShortcut;

@SpringBootApplication
//@EnableCaching
//@Configuration
@ClientCacheApplication(name = "CachingGemFireApplication")
@EnableCachingDefinedRegions(clientRegionShortcut = ClientRegionShortcut.LOCAL)
@EnableGemfireCaching
public class MyMovies2Application {

	public static void main(String[] args) {
		SpringApplication.run(MyMovies2Application.class, args);
	}
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager(new String[] {"configuration", "genres", "top_rated", "keywords", "recommendations","similar"});
	}

}
