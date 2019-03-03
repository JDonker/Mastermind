import java.util.ArrayList;
import java.util.Random;

public class HetBrein {
	private String deCode;
	private Random random;
	final private int maxpogingen=10;
	private int poging;
	private ArrayList<String> pogingen;
	private ArrayList<String> uitslag;
	private boolean geraden;


	
	public HetBrein(){
		random = new Random();
		nieuweCode();
	}
	
	public void nieuweCode() {
		nieuweCode(4);
	}
	
	public void nieuweCode(int lengte) {
		this.poging = 0;
		this.pogingen = new ArrayList<>();
		this.uitslag = new ArrayList<>();
		this.geraden=false;
	
		deCode = "";
		for(int i=0;i<lengte;i++) {
			char keuze = (char)(random.nextInt(6)+65);
		 	deCode += keuze;
		}
	}	
	
	
	public String checkCode(String kleuren) {
		kleuren = kleuren.toUpperCase();
		if(kleuren.matches("[A-F]*")&&kleuren.length()==this.deCode.length()) {
			StringBuilder copykleuren = new StringBuilder(kleuren);
			StringBuilder copycode = new StringBuilder(this.deCode);
			String goedstring = "";
			int goed = 0;
			// check match
			for(int i=0;i<deCode.length();i++) {
				if(copykleuren.charAt(i)==copycode.charAt(i)) {
					goed += 1;
					
					copykleuren.setCharAt(i, 'g');
	
					copycode.setCharAt(i, 'h');
					
				}
			}
			
			goedstring += "" + goed + " ";
			
			if(goed>=this.deCode.length()) {
				this.geraden=true;
				this.poging=this.maxpogingen-1;
			}
			goed =0;
			
			
			// check contains
			outer:
			for(int i=0;i<deCode.length();i++) {
				for(int j=0;j<deCode.length();j++) {
					if(copykleuren.charAt(i)==copycode.charAt(j)) {
						goed += 1;
						
						copykleuren.setCharAt(i, 'g');
		
						copycode.setCharAt(j, 'h');
						continue outer;
					}
				}
			}
			goedstring += "" + goed + " ";
			
			this.pogingen.add(kleuren);
			this.uitslag.add(goedstring);
			poging++;
			return "Nog " + (this.maxpogingen-poging) + " pogingen";
		
	}
		return "Verkeerde invoer!";
	}
	
	
	
	
	public String nieuweKeuze(String letters) {
		if  (!(this.poging<this.maxpogingen)) {
			return "game over! De code was " + this.deCode ;
			
		}
		String output = "";
		output += checkCode(letters);
		output += "\nMASTERMIND\n";
		for(int i=0;i<this.pogingen.size();i++) {
			output += this.pogingen.get(i);
			output += " score: ";
			output += this.uitslag.get(i);
			output += "\n";
		}
		return output;
	}
	
	public boolean geraden() {

		return this.geraden;
	}
	
	public boolean gameover() {
		return !(this.poging<this.maxpogingen);
		
	}
	
}
