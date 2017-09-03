package org.smartbear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author KalpDev
 * @version 1.0
 * @since 02-09-2017
 *
 */
@SpringBootApplication
public class SmartBearBoot {

	/**
	 * <p>SpringBoot File method which initializes required things using SpringApplication</p>
	 * @param args Argument to be passed to SpringApplication
	 */
	public static void main(String[] args) {
		SpringApplication.run(SmartBearBoot.class, args);
	}

}
