package consoleTasks;

public class FFunction implements Evaluatable {

	private double a;

	public FFunction(double d) {
		this.setA(d);
	}
	
	public FFunction(){
		this(1.0);
	}
	
	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}	

	@Override
	public double evalf(double x) {
		// TODO Auto-generated method stub
		return Math.exp(-a*x*x)*Math.sin(x);
	}

	public static void main(String[] args) {
		System.out.println("Проверка класса FFunction");
		FFunction fun = new FFunction();
		/*
		java.util.Scanner in = new java.util.Scanner (System.in);
		System.out.print("xBeg: ");
		double xBeg = in.nextDouble();
		System.out.print("xEnd: ");
		double xEnd = in.nextDouble();
		System.out.print("xStep: ");
		double xStep = in.nextDouble();
		
		System.out.println("Параметр а: " + fun.getA());
		for (double x = xBeg; x <= xEnd; x += xStep)
			System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
		
		System.out.print("x: ");
		double x = in.nextDouble();
		System.out.print("aBeg: ");
		double aBeg = in.nextDouble();
		System.out.print("aEnd: ");
		double aEnd = in.nextDouble();
		System.out.print("aStep");
		double aStep = in.nextDouble();
		
		System.out.println("Переменная х: " + x);
		*/
		double x = 2;
		double aBeg = 1;
		double aEnd = 7;
		double aStep = 0.5;
		
		
		for (double a = aBeg; a <= aEnd; a += aStep){
			fun.setA(a);
			System.out.printf("a: %6.4f\tf: %6.4f\n", fun.getA(), fun.evalf(x));
		}
	}
}
