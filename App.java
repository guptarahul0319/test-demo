package com.tricon.app;

import java.io.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		int i=0;
		
		for(i=0;i<10;i++)
		{

			}

			  System.out.println("Enter 10 number");
		Future<Double> result1 = executor.submit(new MyCallable());
		Future<Double> result2 = executor.submit(new MyCallable());
		Future<Double> result3 = executor.submit(new MyCallable());
		Future<Double> result4 = executor.submit(new MyCallable());
		Future<Double> result5 = executor.submit(new MyCallable());
		Future<Double> result6 = executor.submit(new MyCallable());
		Future<Double> result7 = executor.submit(new MyCallable());
		Future<Double> result8 = executor.submit(new MyCallable());
		Future<Double> result9 = executor.submit(new MyCallable());
		Future<Double> result10 = executor.submit(new MyCallable());
	
		double sum= result1.get()+ result2.get()+ result3.get()+ result4.get()+ result5.get()+ result6.get()+ result7.get()+ result8.get()+ result9.get()+ result10.get();
	    System.out.println(sum);
		System.out.println("submitted callable task");
		System.out.println("finished  main");
	}

}

class MyCallable implements Callable<Double>{

	@Override
	public Double call() throws Exception {
	    InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int a= Integer.parseInt(br.readLine());
		double sum = 0;
		sum= Math.sqrt(a);
		return sum;
	}
	
}