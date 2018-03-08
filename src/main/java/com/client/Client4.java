package com.client;
import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Client4 {
	
	static int loop=1;
	
	public static void main(String[] args) throws IOException {
		
		do {
			service1();
			
		}
		while(loop==1);
			
	}
	private static void service1() throws IOException {
		
		Scanner sc=new Scanner(System.in) ;
		System.out.println("For Upload press 'u' and For Dounload press 'd' ");
		
		String task=sc.nextLine();
				
		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
		HttpPost httppost=null;
		if(task.equals("u")) {
			httppost = new HttpPost("http://localhost:8080/Assignment4/upload");
			
		}
		else {
		if(task.equals("d"))	{
			
			httppost = new HttpPost("http://localhost:8080/Assignment4/download");
			
		}
		else {
			System.out.println("Sorry you don't read instruction about selection of Right choice ");
			System.exit(0);
			
		}
		}
		
		System.out.println("Enter file name: ");
		String name=sc.nextLine();

		HttpResponse response = null;
		
		try {
			httppost.addHeader("img",""+name+"");
			response = httpclient.execute(httppost);
			
			String result = response.getFirstHeader("avail").getValue();
			
			if(task.equals("u")) {
			
			System.out.println("Iamge Upload status is :\t"+result);
			
		}
		else{
			System.out.println("Iamge Avalibiity status is :\t"+result);
		}
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			httpclient.close();
		}
		
		System.out.println("\nDo you want to try Again [y/n]");
		String aa=sc.nextLine();
		if(aa.equals("y")) {loop=1;}
		else {loop=0;
		System.out.println("By");}
	
	
	}
	

}
