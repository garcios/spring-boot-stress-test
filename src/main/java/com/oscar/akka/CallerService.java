package com.oscar.akka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
public class CallerService {

	private static final Logger logger = LoggerFactory.getLogger(CallerService.class);
	
    public String call(String msg) {
    	
  
    	logger.debug(msg);
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String url = "http://127.0.0.1:8080/api/v1/test/start";
    	ResponseEntity<String> response
    	  = restTemplate.getForEntity(url + "?myMessage=" + msg, String.class);
    	
        return response.getStatusCode().toString();
    }

}