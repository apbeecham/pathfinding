package com.apbeecham.pathfinding;

import java.awt.*;

public class Cell extends Vertex {
	
	private int width;
	private int height;
	private Color color;
	
	public Cell(Point position, int width, int height){
		super(position);
		this.width = width;
		this.height = height;	
		this.color = Color.WHITE;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void draw(Graphics g){
		g.setColor(color);
		g.fillRect(position.x, position.y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(position.x, position.y, width, height);
		
	}
}
