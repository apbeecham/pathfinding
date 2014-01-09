package com.titanstudios.pathfinding;

import java.awt.*;
import java.util.*;

/************************************************************
 * A point in a graph that can be connected to other vertices
 * by an edge.
 * @author Adam Beecham
 * @version 1.0
 ************************************************************/
public class Vertex implements Comparable<Vertex>{	
	protected Point position;
	private double distanceFromStart = Double.POSITIVE_INFINITY;
	private double totalCost;
	private Vertex predecessor;
	private ArrayList<Edge> edges;
	
	/************************************************************
	 * Constructs a vertex with a 2D point and an id.
	 * @param id the unique identifier for this vertex.
	 * @param position the position of this vertex in 2D space.
	 ************************************************************/
	public Vertex(Point position){
		this.position = position;
		this.distanceFromStart = Double.POSITIVE_INFINITY;
		this.edges = new ArrayList<Edge>();
	}
		
	/************************************************************
	 * Adds an edge to this vertex.
	 * @param edge an edge to be added to this vertex.
	 ************************************************************/
	public void addEdge(Edge edge){
		edges.add(edge);		
	}
	
	/************************************************************
	 * Sets the distance of this vertex from the start vertex.
	 * @param distanceFromStart the distance of this vertex from
	 * the start vertex.
	 ************************************************************/
	public void setDistanceFromStart(double distanceFromStart){
		this.distanceFromStart = distanceFromStart;
	}	
	
	public void setTotalCost(double totalCost){
		this.totalCost = totalCost;
	}
	
	/************************************************************
	 * Sets the predecessor of this vertex.
	 * @param predecessor the predecessor of this vertex.
	 ************************************************************/
	public void setPredecessor(Vertex predecessor){
		this.predecessor = predecessor;
	}
	
	/************************************************************
	 * Gets the distance of this vertex from the start vertex.
	 * @return distanceFromStart the distance of this vertex from
	 * the start vertex.
	 ************************************************************/
	public double getDistanceFromStart(){
		return distanceFromStart;
	}
	
	public double getTotalCost(){
		return totalCost;
	}
	/************************************************************
	 * Gets the position of this vertex.
	 * @return position the position of the vertex in 2D space.
	 ************************************************************/
	public Point getPosition(){
		return position;
	}
	
	/************************************************************
	 * Gets a specified edge connected to this vertex.
	 * @param index the index of the edge to retrieve from the 
	 * edges arraylist.
	 * @return edge an edge connecting this vertex to another.
	 ************************************************************/
	public Edge getEdge(int index){
		return edges.get(index);
	}
	
	/************************************************************
	 * Gets all edges traversable from this node.
	 * @return name the name of the vertex.
	 ************************************************************/	
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	/************************************************************
	 * Gets the predecessor of this vertex.
	 * @return predecessor the predecessor of this vertex.
	 ************************************************************/
	public Vertex getPredecessor(){
		return predecessor;
	}
	
	/************************************************************
	 * Compares two vertices. 
	 * @param other the vertex to compare with this vertex 
	 * @return sign whether this vertex is greater than, less than 
	 * or equal to another vertex.
	 ************************************************************/
	public int compareTo(Vertex other){
		return Double.compare(totalCost, other.totalCost);
	}
	
	@Override
	public String toString(){
		return "Vertex " + position.getX() + ", " + position.getY();
	}
	
}
