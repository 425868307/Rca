package com.yaof.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;

public class Test04 {

	private  int a = 10;
	
	private final void sddsd() {
int a = 5;
System.out.println(this.a);
	}
	
	public static void main(String[] args) throws Exception {
		StringBuilder stringBuilder = new StringBuilder("dsadsad 1 5 2");
		System.out.println(stringBuilder);
		Scanner sss = new Scanner(stringBuilder.toString());
		while(sss.hasNextInt()){
			System.out.println(sss.nextInt());
		}
		
	}
	
	static class Test04_1{
		public static void main(String[] args) {
			System.out.println("aaaa");
			Test04_2.main(args);
		}
	}

	static class Test04_2{
		public static void main(String[] args) {
			System.out.println("bbbb");
		}
	}
}
