package consoleTasks;

import java.util.Scanner;

import edu.hws.jcm.data.*;

public class AnyFunction {

	public static void main(String[] args) {

		MathObject mathObjX = new MathObject() {
			
			String x;
			
			@Override
			public void setName(String x) {
				// TODO Auto-generated method stub
				this.x = x;
			}
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return this.x;
			}
		};
		
		mathObjX.setName("x");
		
		Parser parser = new Parser();
		parser.add(mathObjX);
		
		Scanner in = new Scanner(System.in);
		
		while(true){
			
			String exp = in.next();

			ExpressionProgram Expression = parser.parse(exp);
			double res = Expression.getVal();
			
			System.out.print(res);

		}
		
		
	}

}
