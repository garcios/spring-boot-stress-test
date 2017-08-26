package com.oscar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.oscar.akka.MasterActor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.compat.Future;
import akka.util.Timeout;
import scala.concurrent.duration.FiniteDuration;

import static com.oscar.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private ActorSystem system;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Sample Akka");
		
		ActorRef caller = system.actorOf(SPRING_EXTENSION_PROVIDER.get(system)
				  .props("masterActor"), "caller");
		
		for (int i=0;i<5000;i++){
			caller.tell(new MasterActor.Message("Oscar: " + i), ActorRef.noSender());
		
		}
		
		TimeUnit.SECONDS.sleep(200);
		system.terminate();
		
		//FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        //Timeout timeout = Timeout.durationToTimeout(duration);

       // Future<Object> result = ask(greeter, new Greet("John"), timeout);
		
	}
}
