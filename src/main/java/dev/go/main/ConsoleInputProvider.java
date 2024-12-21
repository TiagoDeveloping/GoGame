package dev.go.main;

import java.util.AbstractMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class ConsoleInputProvider implements InputProvider {

	private Scanner scanner = new Scanner(System.in);
	
	@Override
	public Entry<Integer, Integer> getMoveInput() {
		System.out.println("Give point x,y: ");
		String in[] = scanner.nextLine().split(",");
		
		int x = Integer.parseInt(in[0]);
		int y = Integer.parseInt(in[1]);
		
		return new AbstractMap.SimpleEntry<>(x, y); 
	}

}
