package com.vorjohan.hanoi;

public class ProjectRunner {

	
	public static void main(String[] args){
		int discs = 7;
		if(args.length==1){
			discs=Integer.parseInt(args[0]);
		}
		HanoiSolver hanoi = new HanoiSolver(discs);
		GameWindow window = new GameWindow(hanoi);
		
	}
	
	public ProjectRunner(){
		
	}
	
	
}
