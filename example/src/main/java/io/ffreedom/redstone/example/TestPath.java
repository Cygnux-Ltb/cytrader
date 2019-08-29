package io.ffreedom.redstone.example;

import java.io.File;
import java.util.Arrays;

import io.ffreedom.common.env.SysProperty;
import io.ffreedom.common.env.SysPropertys;

public class TestPath {
	
	public static void main(String[] args) {

		SysPropertys.showAll();

		System.out.println("=========================================");

		System.out.println(System.getenv("JAVA_HOME"));

		System.out.println(SysProperty.JAVA_IO_TMPDIR);

		//System.out.println(new File("").getAbsolutePath() + "/lib");

		//File file = new File(new File("").getAbsolutePath() + "/lib/linux64");

		///=System.out.println(Arrays.asList(file.list()));

	}

}
