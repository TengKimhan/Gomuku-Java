import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

class MenuConstants {
	
	//FRAME START BUTTON
	protected static final double X_START_BUTTON = 563*(10000/900);// 130*(10000/900)
	protected static final double Y_START_BUTTON = 250*(10000/500);// 157*(10000/500)
	protected static final double WIDTH_START_BUTTON = 100*(10000/900); // 192*(10000/900)
	protected static final double HEIGHT_START_BUTTON = 50*(10000/500); // 71*(10000/500)
		
	//FRAME EXIT BUTTON
	protected static final double X_EXIT_BUTTON = 563*(10000/900);
	protected static final double Y_EXIT_BUTTON = 190*(10000/500);
	protected static final double WIDTH_EXIT_BUTTON = 100*(10000/900);
	protected static final double HEIGHT_EXIT_BUTTON = 50*(10000/500);
		
	//Quit button in playing game
	protected static final double X_QUIT_BUTTON = 320*(10000/900);
	protected static final double Y_QUIT_BUTTON = 100*(10000/500);
	protected static final double WIDTH_QUIT_BUTTON = 260*(10000/900);
	protected static final double HEIGHT_QUIT_BUTTON = 90*(10000/500);
}

public class Menu {
	
	//LOCAL VARIABLES
	public static boolean start_game=false;
	public static boolean players = false;
	public static boolean instructions=false;
	public static boolean home=true;
	public static boolean navigation=true;
	public static boolean exit=false;
	
	//GLOBAL VARIABLES
	public static void navigation() 
	{
		
//		StdDraw.setPenColor(StdDraw.WHITE);
//		StdDraw.filledRectangle(4,6, 1000, 1000);
		//StdDraw.text(0, 0, "Hello");
		//StdDraw.show();
		while(navigation)
		{
			StdDraw.show();
			
			while(home) 
			{
				//StdDraw.setPenColor(StdDraw.WHITE);
				// StdDraw.filledRectangle(4,6, 1000, 1000);
    			//StdDraw.show();
				StdDraw.picture(Constants.X_MAX/2, Constants.Y_MAX/2, "./src/gomuku_start_template.png", 6000, 2500);        
				// if click on start
				if(MenuConstants.X_START_BUTTON < StdDraw.mouseX() && 
						StdDraw.mouseX() < MenuConstants.X_START_BUTTON+MenuConstants.WIDTH_START_BUTTON && 
						MenuConstants.Y_START_BUTTON < StdDraw.mouseY() && 
						StdDraw.mouseY() < MenuConstants.Y_START_BUTTON+MenuConstants.HEIGHT_START_BUTTON)
				{
					//si on clique sur le bouton start
					if(StdDraw.mousePressed()){
						start_game=true;
						home=false;
						instructions=false;
					}
				}
				// if click on instruction
				else if(MenuConstants.X_EXIT_BUTTON < StdDraw.mouseX() && 
						StdDraw.mouseX() < MenuConstants.X_EXIT_BUTTON+MenuConstants.WIDTH_EXIT_BUTTON && 
						MenuConstants.Y_EXIT_BUTTON < StdDraw.mouseY() && 
						StdDraw.mouseY() < MenuConstants.Y_EXIT_BUTTON+MenuConstants.HEIGHT_EXIT_BUTTON) {
					//si on clique sur le bouton instructions
					if(StdDraw.mousePressed()){
						instructions=true;
						start_game=false;
						home=false;
						exit=true;
					}
				}
			}
			
			if (exit) System.exit(0);
			while(start_game)
			{
				  TicTacToe.startGame();
				  break;
			}
		}
	}
}