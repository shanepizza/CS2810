import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ThreadedRectangles {
	private static int _rectangleCount = 1_000_000_000;
	private static int _threadCount = 5;
	private static String _filePath = "ThreadedTest.txt";
	private static byte[] _messegeAsByte;
	
	public static void main(String[] args) throws InterruptedException {
		
		findAverageAreasSingleThreaded();
		String messege;
			for(int i = _threadCount; i < 5000; i+=10){
				System.out.println("When we use "+ i +" threads: ");
				messege = i+" "+findAverageAreasMultithreaded(i);	
			}//End for loop
	}//End Main
	
	public static float findAverageAreasSingleThreaded() {
		long startTime = System.nanoTime();
		Random random = new Random();
		double totalArea = 0;
		for(int i = 0; i < _rectangleCount; i++) {
			totalArea += random.nextFloat(25f) * random.nextFloat(25f);
		}
		System.out.println("SingleThreaded time: " + ((System.nanoTime() - startTime) / 1000000000.0));
		//System.out.println("average area Singlethreaded: " + totalArea/_rectangleCount);

		return (float) (totalArea / _rectangleCount);
	}//End SingleThread
	
	public static float findAverageAreasMultithreaded(int threadCount) throws InterruptedException {
		double completeArea = 0;
		
		RectangleThread[] arrayOfThreads = new RectangleThread[threadCount];
		int rectanglesPerThread = _rectangleCount / threadCount;
		
		long startTime = System.nanoTime();

		for(int i = 0; i < threadCount; i++){
			arrayOfThreads[i] = new RectangleThread();
			arrayOfThreads[i].numberOfRectangles = rectanglesPerThread;
			arrayOfThreads[i].start();
		}

		for (int i = 0; i < threadCount; i++){
			arrayOfThreads[i].join();
			completeArea += arrayOfThreads[i].totalArea;
		}

		float averageArea = (float)(completeArea /_rectangleCount);
		float runTime = (float)((System.nanoTime()-startTime)/1_000_000_000.0);
		System.out.println("Multithreaded Time: " + (runTime));
		//System.out.println("average area multithreaded: " + averageArea);
		
		
		return runTime;
	}//End MultiThread
}//End Class

class RectangleThread extends Thread {
	public int numberOfRectangles;
	public double totalArea = 0;
	
	

	public void run(){
		Random random = new Random();
		for(int i = 0; i < numberOfRectangles; i++){
			totalArea += random.nextFloat(25f) * random.nextFloat(25f);
		}
		//return (float) (totalArea / numberOfRectangles);
	}//End Run
}//End Class
