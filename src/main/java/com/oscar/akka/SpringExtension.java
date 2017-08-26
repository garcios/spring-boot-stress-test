package com.oscar.akka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import akka.routing.RoundRobinPool;


public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

	public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

    
     
    @Override
    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    public static class SpringExt implements Extension {

    	private static final Logger logger = LoggerFactory.getLogger(SpringExt.class);
        private static int nrOfInstances;

        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext, int intanceCount) {
            this.applicationContext = applicationContext;
            nrOfInstances = intanceCount;
        }

        public Props props(String actorBeanName) {
        	
        	logger.info("nrOfInstances={}", nrOfInstances );
        	
            return Props.create(SpringActorProducer.class, applicationContext, actorBeanName)
            		.withRouter(new RoundRobinPool(nrOfInstances));
            		
          
            		
        }

    }

}
