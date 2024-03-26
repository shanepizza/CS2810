import java.util.Random;
import java.util.Scanner;

public class ThreadedMatrixMultiplication {
	static int width = 4000;
	static int height = 2000;
	static int link = 2000;
//Threadcount must be a divisor of both width and height else we will have to run the for loop a weird number of time.
	static int threadCount = 8;
	public static int[][] lazyMatrix_Final_Solution = new int[width][height];
	public static void main(String[] args) throws InterruptedException{
		//You are NOT ALLOWED to change the size of the matrices
		int[][] m1 = generatePopulatedMatrix(width, link);
		int[][] m2 = generatePopulatedMatrix(link, height);

		long start_time = System.nanoTime();
		int[][] result = multiplyMatrices(m1, m2, 0, 0, width, height);
		//printMatrix(result);
		System.out.println("Single thread runtime is: "+ (System.nanoTime()-start_time)/1_000_000_000.0 +" seconds");

		start_time = System.nanoTime();
		MatrixThread[][] arrayOfThreads = new MatrixThread[threadCount][threadCount]; 
	//this is our x value. each increament moves us one "unit"
		for(int x = 0; x < threadCount; x++){
	//this is a for loop inside a for loop. the increment will move us 1 whole "unit" down. 
	//Units are determined by dividing the weight and width amoung however many threads we have.		
			for (int y = 0; y < threadCount; y++){
				arrayOfThreads[x][y] = new MatrixThread(m1, m2);
				arrayOfThreads[x][y].starting_coodinates[0] = (width/threadCount)*y;
				arrayOfThreads[x][y].starting_coodinates[1] = (height/threadCount)*x;
				arrayOfThreads[x][y].ending_coordinates[0] = (width/threadCount)*(y+1);
				arrayOfThreads[x][y].ending_coordinates[1] = (height/threadCount)*(x+1);
				arrayOfThreads[x][y].start();
			}//End Y loop
		}//End X loop
	//I don't understand why our Xs are working with the height and our Ys are working with the width. must be a matrix thing. 
	//Either way, the x and y work. 

		for(int x = 0; x < threadCount; x++){
			for(int y = 0; y <threadCount; y++){
				arrayOfThreads[x][y].join();
				
				//printMatrix(arrayOfThreads[x][y].tempResult);
			}
		}
		System.out.println("Multi thread runtime is: "+ (System.nanoTime()-start_time)/1_000_000_000.0 +" seconds");
		System.out.println("Ya like cheeeese???...");
		//printMatrix(lazyMatrix_Final_Solution);
	}
	public static int[][] generatePopulatedMatrix(int width, int height) {
		int[][] matrixToFill = new int[width][height];
		Random generator = new Random();
		for(int x = 0; x < matrixToFill.length; x++) {
			for(int y = 0; y < matrixToFill[x].length; y++) {
				matrixToFill[x][y] = generator.nextInt(20) + 1;
			}
		}
		return matrixToFill;
	}
	
	public static void printMatrix(int[][] matrixToPrint) {
		System.out.println("Printing matrix: ");
		for(int x = 0; x < matrixToPrint.length; x++) {
			for(int y = 0; y < matrixToPrint[x].length; y++) {
				System.out.print(matrixToPrint[x][y] + ", ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static int[][] multiplyMatrices(int[][] m1, int[][] m2, int starting_Y_Position, int starting_X_Position, int ending_Y_Position, int ending_X_position) {
		if(m1[0].length != m2.length) {
			System.out.println("matrix failed to mutliply"); 
			return null;
		}
		int resultant = 0;

		int[][] resultMatrix = new int[m1.length][m2[0].length];

		for(int m1Y = starting_Y_Position; m1Y < ending_Y_Position; m1Y++) {
			for(int m2X = starting_X_Position; m2X < ending_X_position; m2X++) {
				resultant = 0;
				for(int cross = 0; cross < m1[m1Y].length; cross++) {
					resultant += m1[m1Y][cross] * m2[cross][m2X];
				}
				resultMatrix[m1Y][m2X] = resultant;
			}
		}
		//Adding the result to a matrix outside of this one
		for(int m1Y = starting_Y_Position; m1Y < ending_Y_Position; m1Y++) {
			for(int m2X = starting_X_Position; m2X < ending_X_position; m2X++) {
				lazyMatrix_Final_Solution[m1Y][m2X] = resultMatrix[m1Y][m2X];
			}//End for
		}//End for

		
		return resultMatrix;
	}
}

class MatrixThread extends Thread {
	int[] starting_coodinates = new int[2];
	int[] ending_coordinates = new int[2];
	int[][] tempResult;
	int[][] m1;
	int[][] m2;

	MatrixThread(int[][] m1, int[][] m2){
		this.m1 = m1;
		this.m2 = m2;
	}

	public void run(){
		tempResult = ThreadedMatrixMultiplication.multiplyMatrices(m1, m2, starting_coodinates[0], starting_coodinates[1], ending_coordinates[0], ending_coordinates[1]);
		//ThreadedMatrixMultiplication.printMatrix(tempResult);
	}
}
