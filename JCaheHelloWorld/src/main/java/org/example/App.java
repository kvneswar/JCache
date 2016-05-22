package org.example;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

/**
 * Hello world!
 *
 */
public class App{

	public static void main( String[] args ) throws InterruptedException{
		App app = new App();
		app.listEntriesJavaSEStyle("HelloJCache");
	}

	private void listEntriesJavaSEStyle(String filter) throws InterruptedException {
		final String name = "myCache";

		Cache<String, String> cache = Caching.getCache(name, String.class, String.class);
		if (cache == null) {
			final CachingProvider cachingProvider = Caching.getCachingProvider();
			final CacheManager mgr = cachingProvider.getCacheManager();
			MutableConfiguration<String, String> config = new MutableConfiguration<String, String>();
			config.setTypes(String.class, String.class);
			config.setStoreByValue(true);
			config.setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));
			cache = mgr.createCache(name, config);
		}
		cache.put(filter, "TestCache");
		//Thread.sleep(60000);
		String cached = cache.get(filter);
		System.out.println(cached);

	}
}
