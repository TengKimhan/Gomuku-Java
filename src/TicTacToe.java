import java.awt.Color;
import java.util.*;


class Helper extends TimerTask {
	@Override
	public void run() {
		TicTacToe.executeEveryOneSecond();
	}
}

public class TicTacToe 
{
	static int grid_size = 20;
	static int[][] board = new int[grid_size][grid_size];
	static long timer = 0;
	static long total_timer = 0;
	static Turn turn = Turn.CIRCLE;

	//Constants
    static final int EMPTY = 0;
    static final int FILL_O = 1;
    static final int FILL_X = 2;
    
    static boolean start_think_time = false;
    static boolean can_move = true;
    static boolean timerNotFinish = true;
    static boolean timerPlayerNotFinish = true;

	public static void initGame() 
	{
		StdDraw.clear();
		StdDraw.setCanvasSize(Constants.X_VIEW, Constants.Y_VIEW);
		StdDraw.setXscale(0, Constants.X_MAX);
		StdDraw.setYscale(0, Constants.Y_MAX);
	}
	
	enum Turn {
	      CIRCLE,
	      CROSS
	}
	
	public static void executeEveryOneSecond() 
	{
		System.out.println("Timer: "+timer);
		
		// Draw Total Timer	
		if (timerNotFinish && Menu.start_game) {
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(9.5, -8.5, "Total Timer");
			StdDraw.setPenColor(StdDraw.WHITE);
			StdDraw.filledRectangle(9.5, -7, 1, 1);
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.text(9.5, -7, String.valueOf(total_timer));
			StdDraw.show();
			total_timer++;
		}
		
		if (start_think_time && timerPlayerNotFinish && Menu.start_game) 
		{
			switch(turn) 
            {
        	  case CIRCLE:
        		  // opposite timer count
        		  StdDraw.setPenColor(StdDraw.WHITE);
        		  StdDraw.filledRectangle(-5, 12, 1, 1);
        		  
        		  StdDraw.setPenColor(StdDraw.WHITE);
        		  StdDraw.filledRectangle(25, 12, 1, 1);
        		  StdDraw.setPenColor(StdDraw.BLACK);
        		  StdDraw.text(25, 12, String.valueOf(10-timer));
        		  StdDraw.show();
        		  break;
        	  
        	  case CROSS:
        		  // opposite timer count
        		  StdDraw.setPenColor(StdDraw.WHITE);
        		  StdDraw.filledRectangle(25, 12, 1, 1);
        		  
        		  StdDraw.setPenColor(StdDraw.WHITE);
        		  StdDraw.filledRectangle(-5, 12, 1, 1);
        		  StdDraw.setPenColor(StdDraw.BLACK);
        		  StdDraw.text(-5, 12, String.valueOf(10-timer));
        		  StdDraw.show();
        		  break;
            }
		}
	
		timer++;	
		if (timer>=11 && start_think_time) {
			System.out.println("Time out!\nPlayer " + turn.toString() + " lose");

			switch(turn) 
            {
        	  case CIRCLE:
        		  can_move=false;
        		  start_think_time=false;
        		  System.out.println(turn.toString());
        		  StdDraw.text(10, 26, "Player 2 win! Want to play again?");
      			  StdDraw.picture(10, 28, "./src/replay.png", 6, 3);		
      			  StdDraw.show();
        		  break;
        	  
        	  case CROSS:
        		  can_move=false;
        		  start_think_time=false;
        		  System.out.println(turn.toString());
        		  StdDraw.text(10, 26, "Player 1 win! Want to play again?");
      			  StdDraw.picture(10, 28, "./src/replay.png", 6, 3);		
      			  StdDraw.show();
        		  break;
            }
		}
	}
	
	public static void printBoard(int[][] board) 
	{
	    for (int i = 0; i < board.length; i++) 
	    {
	          for (int j = 0; j < board[i].length; j++) 
	          {
	              System.out.printf("%d ", board[i][j]);
	          }
	          System.out.println();
	      }
	  }
	
	public static int isWin() {
		int count_streak_x = 0;
		int count_streak_o = 0;
		// check horizontally 
		for (int y = 0; y < grid_size; y++) {	// loop all row
	        for (int x = 0; x < grid_size; x++) {
	            // check for player X	
	        	if (board[grid_size-y-1][x] == FILL_X) {
	    			count_streak_x++;
	        	} else {
	        		count_streak_x = 0;
	        	}
	        	
	        	if (count_streak_x == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x - 4 + 0.5, y + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_X;
	        	}
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x - 4 + 0.5, y + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_O;
	        	}
	        }
	    }
		
		// check vertically 
		for (int x = 0; x < grid_size; x++) {	// loop all row
	        for (int y = 0; y < grid_size; y++) {
	            // check for player X	
	        	if (board[grid_size-y-1][x] == FILL_X) {
	    			count_streak_x++;
	        	} else {
	        		count_streak_x = 0;
	        	}
	        	
	        	if (count_streak_x == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x + 0.5, y -4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_X;
	        	}
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x + 0.5, y -4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_O;
	        	}
	        }
	    }
		
		// check the first diagonal '\'
		// the first half
		int x = 0;
		int y = 0;
		for (int v = 0; v < grid_size; v++) {	// going upward
	        x = 0;
	        y = v;
	        while ( y > -1 && x < grid_size) {
	        	// check for player X	
	        	if (board[grid_size-y-1][x] == FILL_X) {
	    			count_streak_x++;
	        	} else {
	        		count_streak_x = 0;
	        	}
	        	
	        	if (count_streak_x == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x - 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_X;
	        	}
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x - 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_O;
	        	}
	        	
	        	y--;
	        	x++;
	        }
	    }
		// the second half
		for (int h = 0; h < grid_size; h++) {	// going right
	        y = grid_size-1;
	        x = h;
	        while ( y > -1 && x < grid_size) {
	        	// check for player X	
	        	if (board[grid_size-y-1][x] == FILL_X) {
	    			count_streak_x++;
	        	} else {
	        		count_streak_x = 0;
	        	}
	        	
	        	if (count_streak_x == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x - 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_X;
	        	}
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x - 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_O;
	        	}
	        	
	        	y--;
	        	x++;
	        }
	    }
		
		// check the second diagonal '/'
		// the first half
		for (int h = 0; h < grid_size; h++) {	// going right
	        x = h;
	        y = grid_size-1;
	        while ( y > -1 && x > -1) {
	        	// check for player X	
	        	if (board[grid_size-y-1][x] == FILL_X) {
	    			count_streak_x++;
	        	} else {
	        		count_streak_x = 0;
	        	}
	        	
	        	if (count_streak_x == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x + 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_X;
	        	}
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x + 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_O;
	        	}
	        	
	        	y--;
	        	x--;
	        }
	    }
		// the first half
		for (int v = grid_size-1; v > -1; v--) {	// going down
	        x = grid_size-1;
	        y = v;
	        while ( y > -1 && x > -1) {
	        	// check for player X	
	        	if (board[grid_size-y-1][x] == FILL_X) {
	    			count_streak_x++;
	        	} else {
	        		count_streak_x = 0;
	        	}
	        	
	        	if (count_streak_x == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x + 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_X;
	        	}
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 5) {
	        		// draw a win line
	        		StdDraw.setPenColor(Color.RED);
	        		StdDraw.line(x + 4 + 0.5, y + 4 + 0.5, x + 0.5, y + 0.5);
	        		StdDraw.show(500);
	        		return FILL_O;
	        	}
	        	
	        	y--;
	        	x--;
	        }
	    }
		
		return -1;
	}
	
	public static int isForbidden(int select_x, int select_y) {
		int count_streak_o = 0;
		int unbroken_and_unblocked_count = 0;
		// check horizontally 
		for (int y = 0; y < grid_size; y++) {	// loop all row
	        for (int x = 0; x < grid_size; x++) {
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {        		
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}

	        	if (count_streak_o == 3) {
	        		if( x-3 > -1 && x+1 < grid_size && board[grid_size-y-1][x-3] == EMPTY && board[grid_size-y-1][x+1] == EMPTY) {	// check for unblocked chain
		        		for(int i=0;i<3;i++) {
		        			if(x-i == select_x && y == select_y) {
		        				unbroken_and_unblocked_count++;
		        				break;
		        			}
		        		}
	        		}
	        	}
	        }
	    }
		
		// check vertically 
		for (int x = 0; x < grid_size; x++) {	// loop all row
	        for (int y = 0; y < grid_size; y++) {	       
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 3) {
	        		if(grid_size-y-1-1>-1 && grid_size-y-1+3 < grid_size && board[grid_size-y-1-1][x] == EMPTY && board[grid_size-y-1+3][x] == EMPTY) {	// check for unblocked chain
		        		for(int i=0;i<3;i++) {
		        			if(x == select_x && y+i == select_y) {
		        				unbroken_and_unblocked_count++;
		        				break;
		        			}
		        			if(x == select_x && y-i == select_y) {
		        				unbroken_and_unblocked_count++;
		        				break;
		        			}
		        		}	 
	        		}
	        	}
	        }
	    }
		
		// check the first diagonal '\'
		// the first half
		int x = 0;
		int y = 0;
		for (int v = 0; v < grid_size-1; v++) {	// going upward
	        x = 0;
	        y = v;
	        while ( y > -1 && x < grid_size) {	        	
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 3) {
	        		if(grid_size-y-1+1<grid_size && x+1 < grid_size && grid_size-y-1-3>-1 && x-3>-1 && board[grid_size-y-1-3][x-3] == EMPTY && board[grid_size-y-1+1][x+1] == EMPTY) {	// check for unblocked chain
		        		for(int i=0;i<3;i++) {
		        			if(x-i == select_x && y+i == select_y) {
		        				unbroken_and_unblocked_count++;
		        				break;
		        			}
		        		}
	        		}
	        	}
	        	
	        	y--;
	        	x++;
	        }
	    }
		// the second half
		for (int h = 0; h < grid_size; h++) {	// going right
	        y = grid_size-1;
	        x = h;
	        while ( y > -1 && x < grid_size) {	        	
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 3) {
	        		if(grid_size-y-1+1<grid_size && x+1 < grid_size && grid_size-y-1-3>-1 && x-3>-1 && board[grid_size-y-1-3][x-3] == EMPTY && board[grid_size-y-1+1][x+1] == EMPTY) {	// check for unblocked chain
		        		for(int i=0;i<3;i++) {
		        			if(x-i == select_x && y+i == select_y) {
		        				unbroken_and_unblocked_count++;
		        				break;
		        			}
		        		}
	        		}
	        	}
	        	
	        	y--;
	        	x++;
	        }
	    }
		
		// check the second diagonal '/'
		// the first half
		for (int h = 0; h < grid_size-1; h++) {	// going right
	        x = h;
	        y = grid_size-1;
	        while ( y > -1 && x > -1) {	        	
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 3) {
	        		if( grid_size-y-1-3>-1 && x+3<grid_size && grid_size-y-1+1<grid_size && x-1>-1 && board[grid_size-y-1-3][x+3] == EMPTY && board[grid_size-y-1+1][x-1] == EMPTY) {	// check for unblocked chain
		        		for(int i=0;i<3;i++) {	 
		        			if(x+i == select_x && y+i == select_y) {
		        				unbroken_and_unblocked_count++;
		        				break;
		        			}
		        		}
	        		}
	        	}
	        	
	        	y--;
	        	x--;
	        }
	    }
		// the second half
		for (int v = grid_size-1; v > -1; v--) {	// going down
	        x = grid_size-1;
	        y = v;
	        while ( y > -1 && x > -1) {	        	
	        	// check for player O
	        	if (board[grid_size-y-1][x] == FILL_O) {
	    			count_streak_o++;
	        	} else {
	        		count_streak_o = 0;
	        	}
	        	
	        	if (count_streak_o == 3) {
	        		if( grid_size-y-1-3>-1 && x+3<grid_size && grid_size-y-1+1<grid_size && x-1>-1 && board[grid_size-y-1-3][x+3] == EMPTY && board[grid_size-y-1+1][x-1] == EMPTY) {	// check for unblocked chain
		        		for(int i=0;i<3;i++) {
		        			if(x+i == select_x && y+i == select_y) {
		        				unbroken_and_unblocked_count++;
		        				break;
		        			}
		        		}
	        		}
	        	}
	        	
	        	y--;
	        	x--;
	        }
	    }
		
		return unbroken_and_unblocked_count;
	}
	
	public static void startGame() 
	{
	  StdDraw.clear();
	  can_move=true;
	  start_think_time = true;
	  board = new int[grid_size][grid_size];
	  timer=0;
	  total_timer=0;
	  turn = Turn.CIRCLE;
	  
	  //Declare variables
	  int x, y;
	  
	  //Draw the board
	  StdDraw.setPenColor(Color.BLACK);
	  StdDraw.setScale(30, -10);
	  StdDraw.setPenRadius(0.0008);
	  for (int c = 0; c <= grid_size; c++) {
	      StdDraw.line(c, 0, c, 20);
	  }
	  for (int c = 0; c <= grid_size; c++) {
	      StdDraw.line(0, c, 20, c);
	  }
	  
	  // Draw text playing board
	  StdDraw.picture(9.5, -3, "./src/playing_template2.png", 10, 5);
	  
	  // Draw text player 1 
	  StdDraw.picture(25, 4, "./src/player1.png", 5, 2);
	  
	  // Draw circle black 
	  StdDraw.picture(25, 7, "./src/Black_Circle.jpeg", 2, 2);
	  
	  // Draw Text Timer
	  StdDraw.text(25, 10, "Timer");
	  
	  // Draw text player 2
	  StdDraw.picture(-5, 4, "./src/player2.png", 5, 2);
	  
	  // Draw circle green
	  StdDraw.picture(-5, 7, "./src/green_circle.png", 1.5, 1.5);
	  
	  // Draw Text Timer
	  StdDraw.text(-5, 10, "Timer");

	  // Draw text exit game
	  StdDraw.picture(10, 23, "./src/quit_game.png", 7, 3);
	     
	  //Two-player Game
      while (true) 
      {
      	  StdDraw.setPenRadius(0.005);
          if (StdDraw.mousePressed()) 
          { 
              x = (int) Math.floor(StdDraw.mouseX());
              y = (int) Math.floor(StdDraw.mouseY());
              
              System.out.println("x = " + x);
              System.out.println("y = " + y);
             
              // replay position
              if (x>=7 && x<=12 && y>=27 && y<=29) 
              {
            	  timerPlayerNotFinish=true;
            	  timerNotFinish=true;
            	  break;	
	          }

              if (x>=7 && x<=12 && y>=22 && y<=23) {
              	System.exit(0);
              }

              if (board[grid_size-y-1][x] == EMPTY && can_move) {
            	 
      			  StdDraw.setPenColor(StdDraw.WHITE);
      			  StdDraw.filledRectangle(10, 26, 9, 0.7);
      			  StdDraw.show();
                  switch(turn) 
                  {
                  	  case CIRCLE:   	
			            	board[grid_size-y-1][x] = FILL_O;
			            	// check 33forbidden move
			            	// only check for black stone
			            	if(isForbidden(x,y)>1) {
			            		board[grid_size-y-1][x] = EMPTY;
			            		System.out.println("33forbidden move!");
			            		StdDraw.setPenColor(StdDraw.RED);
			            		StdDraw.text(10, 26, "Forbidden Move!!");
			            		continue;
			            	}
			            	StdDraw.setPenColor(Color.BLACK);	// set pen color
			            	StdDraw.filledCircle(x + 0.5, y + 0.5, 0.35);
			            	StdDraw.show(500);
	                      	
			            	turn = Turn.CROSS;	// change turn
			            	timer = 0L;	// restart timer
			            break;
  		            
                  	  case CROSS:
				            StdDraw.setPenColor(Color.GREEN);
			            	StdDraw.filledCircle(x + 0.5, y + 0.5, 0.35);
	                        board[grid_size-y-1][x] = FILL_X;
	                        StdDraw.show(500);
	                       
			            	turn = Turn.CIRCLE;
			            	timer = 0L;	// restart timer
			            	
			            break;
                  }
                  
                  if (isWin() == FILL_X) {
	            		can_move = false;
	            		timerNotFinish = false;
	            		timerPlayerNotFinish=false;
                	  	System.out.println("X win!");
                	  	StdDraw.setPenColor(StdDraw.WHITE);
            			StdDraw.filledRectangle(10, 26, 9, 0.7);
            			
            			StdDraw.setPenColor(StdDraw.RED);
	            		StdDraw.text(10, 26, "Player 2 win! Want to play again?");
            			StdDraw.picture(10, 28, "./src/replay.png", 6, 3);		
            			StdDraw.show();
            			
	            		
	            	} else if (isWin() == FILL_O) {
	            		
	            		can_move = false;
	            		timerNotFinish=false;
	            		timerPlayerNotFinish=false;
	            		System.out.println("O win"); // Player 1
	            		StdDraw.setPenColor(StdDraw.WHITE);
            			StdDraw.filledRectangle(10, 26, 9, 0.7);
            			
            			StdDraw.setPenColor(StdDraw.RED);
	            		StdDraw.text(10, 26, "Player 1 win! Want to play again?");
            			StdDraw.picture(10, 28, "./src/replay.png", 6, 3);
            			StdDraw.show();
            			
	            	}
              }
          }
      }
	}
	
	public static void main(String[] args) 
	{
		Timer schedule = new Timer();
		TimerTask task = new Helper();
		schedule.schedule(task, 0, 1000);	// execute every 1 second
		
		initGame();
		Menu.navigation();		
	}
}


