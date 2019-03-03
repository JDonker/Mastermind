import java.util.Scanner;

public class Mastermind {
	public HetBrein brein;
	public Scanner scanner;
	
	
	public Mastermind(){
		brein = new HetBrein();
		scanner = new Scanner(System.in);
	}
	
	public void run() {
		boolean spelen= true;
		System.out.println("Welkom bij Mastermind");
		gameloop:
		while(spelen) {
			System.out.println("De code is 4 tekens lang en bestaat uit letters (a,b,c,d,e,f)");
			while(!brein.gameover()) {
				System.out.println("Je mag raden, [q] stopt het spel:");
				String test = scanner.nextLine();
				switch (test) {
					default:
						String output = brein.nieuweKeuze(test);
					    System.out.println(output);
					    break;
					case "q":
						spelen=false;
						break gameloop;
				}
			}
			
			if (brein.geraden()) {
			System.out.println("Je hebt de code geraden!");	
				
			} else {
				System.out.println(brein.nieuweKeuze("zzzz"));
			}
			
			OUTER:
			while(true) {
				System.out.println("Nog een potje? [j/n]");
				String test = scanner.nextLine();
				switch(test) {
				case "j": 
					brein.nieuweCode();
					break OUTER;
				case "n":
					spelen=false;
					break OUTER;
				default :
					System.out.println("Verkeerde invoer");
				}	
			}
		}
		
		System.out.println("Tot ziens!");
	}
}
