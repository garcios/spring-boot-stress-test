package com.oscar.akka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.AbstractActor;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MasterActor extends AbstractActor {

	private static final Logger logger = LoggerFactory.getLogger(MasterActor.class);

	
	@Autowired
    private CallerService callerService;

    public MasterActor(CallerService greetingService) {
        this.callerService = greetingService;
        
        
    }

	@Override
	public Receive createReceive() {
		
		return receiveBuilder().match(Message.class, this::onCall).build();
	}

	private void onCall(Message g){
		 String message = g.getName();
		 logger.info(callerService.call(message));
		 
	}
 

    public static class Message {

        private String myMessage;

        public Message(String msg) {
            this.myMessage = msg;
        }

        public String getName() {
            return myMessage;
        }

    }
    
 }    


