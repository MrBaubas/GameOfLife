package Game2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.util.Random;

public class ConwaysLife extends JFrame implements Runnable, MouseListener {
	// member data
	private BufferStrategy strategy;
	private Graphics offscreenBuffer;
	private boolean gameState[][][] = new boolean[40][40][2];
	//private JButton resetButton, cycleButton; //start and cycle control
	//private JLabel generationsLived;
	//private int numLiveNeighbours = 0;
	public boolean isPlaying = false;
	

	public ConwaysLife() {
		//Display the window
		Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int x = screensize.width/2 - 400;
		int y = screensize.height/2 - 400;
		setBounds(x, y, 800, 800);
		setVisible(true);
		this.setTitle("Conway's game of life");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//initialise double-buffering
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		offscreenBuffer = strategy.getDrawGraphics();
		
		//register the JFrame itself to receive mouse events
		addMouseListener(this);
		
		//initialise the game state
		for (x=0; x<40; x++){
			for (y=0; y<40; y++){		
				gameState[x][y][0] = false;
				//count the live neighbours of cell [x][y][0]
				//numLiveNeighbours = [x][y][0];
			/*	int numLiveNeighbours = 0;
				for (int xx=-1; xx<=1; xx++) {
					for (int yy=-1; yy<=1; yy++) {
						if (xx!=0 || yy!=0) {
							//check cell [x+xx][y+yy][0]
							int i,j =0;
							i=x+xx;
							j=y+yy;
							if (i == -1){
								i=39;
							}
							else if(i==40){
								i=1;
							}
							if(j == -1){
								j=39;
							}
							else if(y==40){
								y=1;
							}
							if(gameState[x][y][0]){
								numLiveNeighbours++;
							}
							
							//but.. what if x+xx==-1????
						}
					}
					if(gameState[x][y][0]){
						if(numLiveNeighbours<2){
							gameState[x][y][1] = false;
						}
						else if(numLiveNeighbours==2 || numLiveNeighbours==3){
							gameState[x][y][1] = true;
						}
						else if (numLiveNeighbours>3){
							gameState[x][y][1] = false;
						}
					}
					else{
						if (numLiveNeighbours == 3){
							gameState[x][y][0] = true;
						}
					}*/
				}
		}
		/*
		//add two buttoms 
		
		 resetButton = new JButton("New Game");
	        resetButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                screensize.newGame();
	                updateDisplay();
	            }
	        });
	        cycleButton = new JButton("Live One Cycle");
	        cycleButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                screensize.lifeCycle();
	                updateDisplay();
	            }
	        });
	        
	     // Put the buttons and the generation count display on the screen
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.add(resetButton);
	        buttonPanel.add(cycleButton);
	        generationsLived = new JLabel("     Generations Lived: " , JLabel.RIGHT);
	        buttonPanel.add(generationsLived);
	        add(buttonPanel, BorderLayout.SOUTH);

	        // show initial display
	        updateDisplay();
	    }
	        
	 /** Update display to match game state. 
    public void updateDisplay() {
        // update count display
        generationsLived.setText("     Generations Lived: " + screensize.getGenerationCount());

        // update board display
        for (int row = 0; row < Life.NROWSCOLS; row++) {
            for (int col = 0; col < Life.NROWSCOLS; col++) {
                gameState[row][col].setState(screensize.getCell(row,col));
            }
        }
        repaint();
    }
	        */
		
		//create and start our animation thread
		Thread t = new Thread(this);
		t.start();
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		offscreenBuffer = strategy.getDrawGraphics();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		if(!isPlaying){
		//determinate whitch cell of the gameState array was clicket on
		int x = e.getX()/20;
		int y = e.getY()/20;
		//toggle the state of the cell
		gameState[x][y][0] = !gameState[x][y][0];
		
		if((e.getX()>=20 && e.getX()<=90)&&(e.getY()>=40 &&e.getY()<=70)){
			isPlaying = true;
//			rules();
			
		}
		if((e.getX()>=110 && e.getX()<=226)&&(e.getY()>=40 &&e.getY()<=70)){
			//random
			for(int i=0;i<40;i++){
				for(int j=0;j<40;j++){
					Random rand = new Random();
					int w = rand.nextInt((2-1)+1)+1;
					if(w==1){
						gameState[i][j][0]= true;					
					}else{
						gameState[i][j][0]= false;
					
//					}
				}
			}
		}
		//request an extra repaint, to get immediate visual feedback 
		this.repaint();
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		while ( 1==1 )
		{
			// 1:sleep for 1/5 sec
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) { e.printStackTrace(); }
			// 2: animate game objects [nothing yet!]
			
			//3: force an application repaint
			this.repaint();
		}
	}
	public void rules(){
		boolean gameState2[][][] = new boolean[40][40][2];
		for (int x=0; x<40; x++){
			
			for (int y=0; y<40; y++){				
				//count the live neighbours of cell [x][y][0]
				//numLiveNeighbours = [x][y][0];
				int numLiveNeighbours = 0;
				for (int xx=-1; xx<=1; xx++) {
					for (int yy=-1; yy<=1; yy++) {
						if (xx!=0 || yy!=0) {
							//check cell [x+xx][y+yy][0]
							int i,j =0;
							i=x+xx;
							j=y+yy;
							if (i == -1){
								i=39;
							}
							else if(i==40){
								i=1;
							}
							if(j == -1){
								j=39;
							}
							else if(y==40){
								y=1;
							}
							if(gameState2[x][y][1]){
								numLiveNeighbours++;
							}
							
							//but.. what if x+xx==-1????
						}
					}
					if(gameState2[x][y][0]){
						if(numLiveNeighbours<2){
							gameState2[x][y][1] = false;
						}
						else if(numLiveNeighbours==2 || numLiveNeighbours==3){
							gameState2[x][y][1] = true;
						}
						else if (numLiveNeighbours>3){
							gameState2[x][y][1] = false;
						}
					}
					else{
						if (numLiveNeighbours == 3){
							gameState2[x][y][1] = true;
						}
					}

				}
			}
		}
		gameState = gameState2;
		
	}
	
	//application paint method
	public void paint(Graphics g) {
		g = offscreenBuffer; //draw to screen buffer
		//clear the canvas with a big offscreen buffer
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800);
		if(isPlaying){
			rules();
		}
		//redraw all game objects
		g.setColor(Color.WHITE);
		for(int x=0;x<40;x++){
			for(int y=0;y<40;y++){
				if(gameState[x][y][0]){
					g.fillRect(x*20, y*20, 20, 20);
					
				}
				
			}
		}
//		if(!isPlaying){
			g.setColor(Color.CYAN);
			g.fillRect(18,40,70,30);
			g.fillRect(108,40,116,30);
		
			Font f = new Font("Dialog", Font.PLAIN, 28);
			g.setColor(Color.BLACK);
			g.setFont(f);
			g.drawString("Start", 20, 65);
			g.drawString("Random", 110, 65);
//		}
		
		
		//flip the buffer
		strategy.show();

	}	
	
	
	
	
	public static void main(String[] args) {
		ConwaysLife w = new ConwaysLife();
	}
}
