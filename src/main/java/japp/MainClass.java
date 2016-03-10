package japp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClass {

	private static Logger log = LogManager.getLogger(MainClass.class);

	private MainClass() {}

	public static void main(String[] args) {	
		Controller.start();
	}
}
