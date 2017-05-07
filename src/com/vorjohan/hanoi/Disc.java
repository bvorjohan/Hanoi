package com.vorjohan.hanoi;
import CS2114.Shape;
import java.util.Random;

import java.awt.Color;
import java.math.*;


public class Disc extends Shape implements Comparable<Disc>{

	public Disc(int width) {
		super(0, 0, width, 5);
		Color col = new Color((int)Math.random()*255,(int)Math.random()*255,(int)Math.random()*255);
		this.setBackgroundColor(col);
	}

	
	public int compareTo(Disc otherDisc){
		if (otherDisc==null){
			throw new IllegalArgumentException();
		}
		
		return this.getWidth()-otherDisc.getWidth();
	}

	public String toString(){
		super.toString();
		return Integer.toString(this.getWidth());
	}
	
	public boolean equals(Disc otherDisc){
		
		return this.getWidth()==otherDisc.getWidth();
	}

}
