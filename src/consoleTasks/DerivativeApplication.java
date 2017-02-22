package consoleTasks;

import java.io.*;

public class DerivativeApplication {
	
	

	public static void main(String[] args){		
		MainView.start();	
	}
	
	
	public static void calculate()  throws IOException {
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
		
		String fileName = "";
		for (Evaluatable f: functs){
			System.out.println("Функция: " + f.getClass().getSimpleName());
			fileName = f.getClass().getSimpleName() + ".dat";
			PrintWriter out = new PrintWriter(new FileWriter(fileName));
			
			for (double x = 1.5; x <= 6.5; x += 0.05){
				System.out.print("x: " + x + "\tf: " + f.evalf(x) + "\tf' : " + NumMethods.der(x, 1.0e-4, f));
				out.printf("%16.6e%16.6e%16.6e\n", x, f.evalf(x), NumMethods.der(x, 1.0e-4, f));
			}
			System.out.println("\n");
			out.close();
		}
	}
}
