package util;

import java.util.Scanner;

/**
 * Classe Utilitária para manipular as entradas de dados do Sistema
 *
 * @author lhries
 * @since JDK 1.0
 */
public class Console {
	
	public static String scanString(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextLine());
	}
	
	public static int scanInt(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextInt());		
	}

	public static double scanDouble(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextDouble());		
	}

	public static float scanFloat(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextFloat());		
	}

	public static boolean scanBoolean(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.nextBoolean());		
	}
	
	public static char scanChar(Object out)
	{
		System.out.print(out);
		Scanner scanner = new Scanner (System.in);
		return(scanner.next().charAt(0));				
	}
	
}
