import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

	public class GameFrame extends JFrame implements ActionListener{

	 Game game;
	 JButton resetButton;
	 
	 GameFrame(){
	  
	  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  this.setSize(600, 500);
	  this.setLayout(null);
	  
	  resetButton = new JButton();
	  resetButton.setText("Reset");
	  resetButton.setSize(100, 50);
	  resetButton.setLocation(0, 200);
	  resetButton.addActionListener(this);
	  
	  game = new Game();
	  
	  this.add(resetButton);
	  this.add(game);
	  this.setVisible(true);
	  
	 }

	 @Override
	 public void actionPerformed(ActionEvent e) {
	  if(e.getSource()==resetButton) {
	   this.remove(game);
	   game = new Game();
	   this.add(game);
	   SwingUtilities.updateComponentTreeUI(this);
	  }
	 }
	}
