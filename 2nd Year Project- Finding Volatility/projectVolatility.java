import java.util.*;
import java.math.*;

public class projectVolatility {

	private static Scanner scan;

	public static void main(String[] args) {
		
		fileIO reader = new fileIO();
		String[] StockData = reader.load("C://DataStock.txt");
		int rows = StockData.length;
		int cols = StockData[0].split("\t").length;
		double[][] array = new double[rows][cols];
		double[] StockPrice = new double[cols];
		
		System.out.println("Enter your budget!");
		scan = new Scanner(System.in);
		double maxNum = scan.nextDouble();	
		
		for(int i = 1; i < rows-1; i++){
			for(int j = 1; j < cols; j++){
				StockPrice[j] = Double.parseDouble(StockData[rows - 1].split("\t")[j]);
				array[i][j] = Double.parseDouble(StockData[i].split("\t")[j]);
			}
		}
		double volatility[] = new double[cols];
		double temp[] = new double[rows];
		
		for(int i = 0; i < cols; i++){
			for(int j = 0; j < rows;j++){
				temp[j] = array[j][i];
			}
			volatility[i] = std_Dev(temp, temp.length);
		}
		
		double finalAnswer[] = new double[cols];
		for(int i = 0; i < finalAnswer.length;i++){
			finalAnswer[i]= 0;
		}
		
		double sum = 0;
		for(int j = 0;j < (maxNum*.001); j++){
			for(int i = 1; i < cols; i++){
				if(i == 212 | i == 368 && (sum + (StockPrice[i]*14)) < maxNum){
					sum+=StockPrice[i]*14;
					finalAnswer[i]+=14;
				}
				else if(volatility[i]< 1.51 && (sum + StockPrice[i]) < maxNum){
					sum+=StockPrice[i];
					finalAnswer[i]++;
				}
			}
		}
		System.out.println((int)sum + " is the highest amount you can invest without going over budget" +"\n");
		System.out.println("Insert into spreadsheet");
		for(int i = 1;i<finalAnswer.length;i++){
			System.out.print((int)finalAnswer[i]+ "\t");
		}
		System.out.println("\n");
		System.out.println("Final Answer String");
		for(int i = 1;i<finalAnswer.length;i++){
			System.out.print((int)finalAnswer[i]+ ",");
		}
		
				
	}
	public static double std_Dev(double a[], int n) {
//		if(n == 0)
//			return 0.0;
		double sum = 0;
		double sq_sum = 0;
		for(int i = 0; i < n; ++i) {
			sum += a[i];
			sq_sum += a[i] * a[i];
		}
		double mean = sum / n;
		double variance = sq_sum / n - mean * mean;
		return Math.sqrt(variance);
	}

}
