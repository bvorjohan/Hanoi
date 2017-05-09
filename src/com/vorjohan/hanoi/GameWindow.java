package com.vorjohan.hanoi;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import CS2114.Button;
import CS2114.Shape;
import CS2114.Window;
import CS2114.WindowSide;

public class GameWindow implements Observer{

	private HanoiSolver game;
	private Shape left;
	private Shape middle;
	private Shape right;
	private Window window;	//how is the imported Window different than java's?
							//^same with Shape
	private final int DISK_GAP=0;
	private final int DISK_HEIGHT=10;
	private final int ROD_HEIGHT=100;
	private final int ROD_WIDTH=5;
	private final int DISC_WIDTH_DIFF=10;
	private final int ROD_Y_LOC=100;
	private final int ROD_X_LOC=200;
	private final int ROD_SPACE=100;
	
	private Button solveButton;
	private Button resetButton;
	
	

	
	
	





	public GameWindow(HanoiSolver game) {
		this.game=game;
		this.game.addObserver(this);
		this.window=new Window("Tower of Hanoi");
		this.left=	new Shape(ROD_X_LOC, 			ROD_Y_LOC,ROD_WIDTH,ROD_HEIGHT,new Color(100,100,100));
		this.middle=new Shape(ROD_X_LOC+ROD_SPACE,	ROD_Y_LOC,ROD_WIDTH,ROD_HEIGHT,new Color(100,100,100));
		this.right=	new Shape(ROD_X_LOC+ROD_SPACE*2,ROD_Y_LOC,ROD_WIDTH,ROD_HEIGHT,new Color(100,100,100));
		
		
		for (int i=0;i<this.game.discs();i++){
			this.game.getTower(Position.LEFT).push(new Disc(DISC_WIDTH_DIFF*(this.game.discs()+1-i)+this.ROD_WIDTH+10,this.DISK_HEIGHT));
			this.window.addShape(this.game.getTower(Position.LEFT).peek());
			moveDisc(Position.LEFT);
		}
		
		this.window.addShape(this.left);
		this.window.addShape(this.middle);
		this.window.addShape(this.right);
		
		this.solveButton = new Button("Solve");
		this.window.addButton(solveButton, WindowSide.SOUTH);
		this.resetButton=new Button("Reset");
		this.window.addButton(resetButton, WindowSide.SOUTH);
		this.solveButton.onClick(this);
		this.resetButton.onClick(this);
		
		
	}









	public void update(Observable o, Object arg) {
		if(arg.getClass()==Position.class){
			moveDisc((Position)arg);
			
		}
		sleep();
		
	}
	
	private void sleep() {
	    try {
	        Thread.sleep(125);
	    }
	    catch (Exception e) {
	    }
	}


	public void clickedSolve(Button button) {
	    button.disable();
	    new Thread() {
	        public void run() {
	            game.solve();
	        }
	    }.start();
	    
	}
	
	public void clickedReset(Button button){
		button.disable();
		new Thread() {
			public void run(){
				game.reset(DISC_WIDTH_DIFF);
			}
		}.start();

	}
	
	
	
	private void moveDisc(Position position){
		Disc currentDisc=this.game.getTower(position).peek();
		Shape currentPole;
		switch (position){
		case LEFT:
			currentPole=this.left;
			break;
		case MIDDLE:
			currentPole=this.middle;
			break;
		case RIGHT:
			currentPole=this.right;
			break;
		default:
			currentPole=this.left;
			break;
		}
		int x=currentPole.getX();
		int y=currentPole.getY()+this.ROD_HEIGHT-(this.game.getTower(position).size()*(this.DISK_HEIGHT+this.DISK_GAP));
		//int width=currentDisc.getWidth();
		int height = currentDisc.getHeight();
		//int size = this.game.getTower(position).size();
		
		
		currentDisc.moveTo(x-(currentDisc.getWidth()-this.ROD_WIDTH)/2, y-(this.DISK_GAP));
	}
	


}
