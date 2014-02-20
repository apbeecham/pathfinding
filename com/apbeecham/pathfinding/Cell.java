package com.apbeecham.pathfinding;

import java.awt.*;

/************************************************************
 * Provides a visual representation of a vertex so they can
 * be drawn on the grid component.
 * @Author Adam Beecham
 ************************************************************/
public class Cell extends Vertex {

	private int width;
	private int height;
	private Color color;

	/************************************************************
	 * Constructs the cell object
	 * @param position the x and y pixel coordinates of the cell
	 * @param width the pixel width of the cell
	 * @param height the pixel height of the cell
	 ************************************************************/
	public Cell(Point position, int width, int height){
		super(position);
		this.width = width;
		this.height = height;
		this.color = Color.WHITE;
	}

	/************************************************************
	 * Set the fill color of the cell.
	 * @param color the color of the cell
	 ************************************************************/
	public void setColor(Color color){
		this.color = color;
	}

	/************************************************************
	 * Get the color of this cell
	 ************************************************************/
	public Color getColor(){
		return this.color;
	}

	/************************************************************
	 * Draw the cell
	 ************************************************************/
	public void draw(Graphics g){
		g.setColor(color);
		g.fillRect(position.x, position.y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(position.x, position.y, width, height);

	}
}
