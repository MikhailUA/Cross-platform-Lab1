package consoleTasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
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
		
		Map<String, List> output = calc(functs);
		
		return output;
	}
	
	
	private static Map<String,List> calc(Evaluatable functs[]) throws IOException{
		
		Map<String, List> output = new HashMap<String,List>(); 			// <functionName, results>	
			List<Map> results; 											// collects all results of current iteration		
				Map<String, Double> result; 							// x = , y = , dir = 
		
		String fileName = "";
		
		
		for (Evaluatable f: functs){
			
			String ClassName = f.getClass().getSimpleName();
			
			System.out.println("Функция: " + ClassName);
			fileName = ClassName + ".dat";
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
					
			results = new ArrayList<Map>();
			
			for (double x = 1.5; x <= 6.5; x += 0.05){
				
				double y = f.evalf(x);
				double derivative = NumMethods.der(x, 1.0e-4, f);
				
				result = new HashMap<String, Double>();
				result.put("x", x);
				result.put("y", y);
				result.put("derivative", derivative);
				
				results.add(result);				
				
				System.out.print("x: " + x + "\tf: " + y + "\tf' : " + derivative + "\n");
				out.printf("%16.6e%16.6e%16.6e\n", x, y, derivative);
			}
			
			output.put(ClassName, results);
			
			System.out.println("\n");
			out.close();
		}
			
		return output;
	}
}
