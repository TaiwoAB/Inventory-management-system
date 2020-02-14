package com.qa;

import org.apache.log4j.Logger;
/**
 * 
 * @author tolaa
 *The runs the application by instantiating ims.
 */
public class Runner {

	public static final Logger LOGGER = Logger.getLogger(Runner.class);
	
	public static void main(String[] args) {
		Ims ims = new Ims();
		ims.imsSystem();
	}

}
