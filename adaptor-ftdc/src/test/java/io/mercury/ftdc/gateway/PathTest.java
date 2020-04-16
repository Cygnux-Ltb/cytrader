package io.mercury.ctp.gateway;

import java.io.File;
import java.util.Arrays;

public class PathTest {
	
	public static void main(String[] args) {
		
		System.out.println("=========================================");
		
		System.out.println(System.getenv("JAVA_HOME"));
		
		System.out.println(System.getProperty("JAVA_HOME"));
		
		System.out.println(new File("").getAbsolutePath() + "/lib");
		
		File file = new File(new File("").getAbsolutePath() + "/lib/linux64");

		System.out.println(Arrays.asList(file.list()));
		
	}

}
