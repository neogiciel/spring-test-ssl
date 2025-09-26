package com.neogiciel.mtls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@SpringBootApplication
public class ClientSsl1Application {


	static Logger logger = LoggerFactory.getLogger(ClientSsl1Application.class);

	// Property values
    @Value("${spring.ssl.bundle.jks.client.keystore.location}")
    private String keyStoreLocation;

    @Value("${spring.ssl.bundle.jks.client.keystore.password}")
    private String keyStorePassword;

	@Value("${spring.ssl.bundle.jks.client.truststore.location}")
    private String trustStoreLocation;
;
    @Value("${spring.ssl.bundle.jks.client.truststore.password}")
    private String trustStorePassword;


	@PostConstruct
	public void initSsl(){
		logger.info("[ClientSsl1Application/initSsl] **************** debut ***************************");
		logger.info("[ClientSsl1Application/initSsl] keyStoreLocation = "+ keyStoreLocation);
		System.setProperty("javax.net.ssl.keyStore", keyStoreLocation);
		logger.info("[ClientSsl1Application/initSsl] keyStorePassword = "+ keyStorePassword);
		System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassword);
		logger.info("[ClientSsl1Application/initSsl] trueStoreLocation = "+ trustStoreLocation);
		System.setProperty("javax.net.ssl.trustStore", trustStoreLocation);
		logger.info("[ClientSsl1Application/initSsl] trustStorePassword = "+ trustStorePassword);
		System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword);
		logger.info("[ClientSsl1Application/initSsl] **************** fin ***************************");

		//System.setProperty("javax.net.ssl.keyStore", Thread.currentThread().getContextClassLoader().getResource("keystore.jks").getPath());
		
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
			(hostname,sslSession) -> {
				logger.info("[ClientSsl1Application/initSsl] hostname = "+hostname);
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			});
	}
	
	@Bean
	public RestTemplate template() throws Exception{
		RestTemplate template = new RestTemplate();
		return template;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientSsl1Application.class, args);
	}

}

