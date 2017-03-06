package consoleTasks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class Task1 {

	public static void main(String[] args) throws ClassNotFoundException {
		
		Scanner in = new Scanner(System.in);
		
		String str = in.nextLine();
		
		Class<?> cls = Class.forName(str);
		
		Field[] publicfields = cls.getDeclaredFields();
		
		for(Field field: publicfields){
			Class fieldType = field.getType();
		    System.out.println("Èìÿ: " + field.getName()); 
		    System.out.println("Òèï: " + fieldType.getName());
		}
		
	}
	
	
}
