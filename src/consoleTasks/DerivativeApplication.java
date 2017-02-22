package consoleTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DerivativeApplication {
	
	public static void main(String[] args){		
		MainView.start();		
	}
		
	public static Map calculate()  throws IOException {
		Evaluatable functs[] = new Evaluatable[3];
		
		functs[0] = new FFunction(0.5);			 // функция задана f(x) = exp(-ax^2)*sin(x)
		functs[1] = new SolveEqFunction();		 // функция задана уравнением для заданного а на отрезке а [1.0, 7.0]
		functs[2] = new FileListInterpolation(); // функция задана в табличной форме
		
		((SolveEqFunction)functs[1]).setRootApprox(0.7);
		
		try{
			( (FileListInterpolation) functs[2] ).readFromFile("TblFunc.dat");
		}catch(IOException ex){
			ex.printStackTrace();
			System.exit(-1);
		}		
		
		Map<String, List> output = calculateAll(functs);
		
		return output;
	}
		
	private static Map<String,List> calculateAll(Evaluatable functs[]) throws IOException{
		
		Map<String, List> output = new HashMap<String,List>(); 			// <functionName, results>	
			ArrayList<Map> results; 									// collects all results of current iteration		
	//			Map<String, Double> result; 							// x = , y = , dir = 
				
		double xBeg = 1.5;
		double xEnd = 6.5;
		double xStep = 0.05;		
		
		for (Evaluatable f: functs){			
			String ClassName = f.getClass().getSimpleName();								
			results = new ArrayList<Map>();		
			results = calculateSingleFunction(f, xBeg, xEnd, xStep);
			saveToFile(ClassName, results);
			printToConcole(ClassName,results);
			output.put(ClassName, results);
		}
			
		return output;
	}
	
	private static ArrayList<Map> calculateSingleFunction(Evaluatable f, double xBeg, double xEnd, double xStep){
		
		ArrayList<Map> results = new ArrayList<Map>();		
		
		for (double x = xBeg; x <= xEnd; x += xStep){
			
			double y = f.evalf(x);
			double derivative = NumMethods.der(x, 1.0e-4, f);
			
			Map<String, Double> result = new HashMap<String, Double>();
			result.put("x", x);
			result.put("y", y);
			result.put("derivative", derivative);
			
			results.add(result);				
			
			System.out.print("x: " + x + "\tf: " + y + "\tf' : " + derivative + "\n");
			
		}
		
		return results; 
	}
		
	private static void saveToFile (String functionName, ArrayList<Map> results) throws IOException{
		
		String filename = functionName + ".dat";
		
		PrintWriter out = new PrintWriter(new FileWriter(filename));
		
		out.print(functionName + "\n");
		
		for (Map<String, Double> result : results){
			out.printf("%16.6e%16.6e%16.6e\n", result.get("x"), result.get("y"), result.get("derivative"));
		}
		
		out.close();
	}
		
	private static void printToConcole (String functionName, ArrayList<Map> results){
		System.out.println("Функция: " + functionName);
		for (Map<String, Double> result : results){
			System.out.print("x: " + result.get("x") + "\tf: " + result.get("y") + "\tf' : " + result.get("derivative") + "\n");
		}
	}
}
