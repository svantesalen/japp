package japp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Japp {

	private static Logger log = LogManager.getLogger(Japp.class);

	private Japp() {}

	public static void main(String[] args) {	
		Controller.start();
	}
}
