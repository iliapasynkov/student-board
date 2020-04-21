package com.epam.rdlab.online.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class StudentBoardConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		//restTemplate.setRequestFactory(clientHttpRequestFactory());
		return restTemplate;
	}

	/*
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
		//restTemplate.setRequestFactory(clientHttpRequestFactory());
		return restTemplate;
	}
	*/
	// to read values from application.properties as List
	@Bean
	public ConversionService conversionService() {
		return new DefaultConversionService();
	}

	/*
	private HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
				= new HttpComponentsClientHttpRequestFactory();

		clientHttpRequestFactory.setHttpClient(httpClient());

		return clientHttpRequestFactory;
	}

	private HttpClient httpClient() {
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

		credentialsProvider.setCredentials(AuthScope.ANY,
										   new UsernamePasswordCredentials("login", "password"));

		HttpClient client = HttpClientBuilder
				.create()
				.setDefaultCredentialsProvider(credentialsProvider)
				.build();
		return client;
	}
	*/
}
