import java.awt.Color;
import java.util.Random;
import javax.swing.JPanel;

	public class Game extends JPanel{
	 
	 Random random;
	 
	 Game(){
	  
	  random = new Random();
	  
	  int r = random.nextInt(256);
	  int g = random.nextInt(256);
	  int b = random.nextInt(256);
	  
	  this.setSize(500, 500);
	  this.setLocation(100,0);
	  this.setBackground(new Color(r,g,b));
	  
	 }
	}
