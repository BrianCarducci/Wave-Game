package mainGame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Loads the window of the game, and sets the proper dimensions
 * @author Brandon Loehle
 * 5/30/16
 */

public class Window extends Canvas{

	private static final long serialVersionUID = 1L;
	
	public Window(int width, int height, String title, Game game){
		//uses the java api to get the users screen width and height to build the window 
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = (int) screenSize.getWidth();
		height = (int) screenSize.getHeight();
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		//packs the frame based on the users monitor
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
		
	}


}
