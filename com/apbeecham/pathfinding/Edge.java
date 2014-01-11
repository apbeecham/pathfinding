package com.apbeecham.pathfinding;

/************************************************************
 * A weighted edge that connects two vertices in a graph 
 * together.
 * @author Adam Beecham
 * @version 1.0
 ************************************************************/
public class Edge {	
	private int cost;	
	private Vertex destination;
	
	/************************************************************
	 * Constructs a weighted edge to connect two vertices together.
	 * @param cost the cost of traversing this edge to reach the 
	 * destination vertex from the source vertex.
	 * @param destination the destination vertex for this edge.
	 ************************************************************/
	public Edge(int cost, Vertex destination){
		this.cost = cost;
		this.destination = destination;
	}
	
	/************************************************************
	 * Gets the cost of traversing this edge.
	 * @return cost the cost of traversing this edge.
	 ************************************************************/
	public int getCost(){
		return cost;
	}
	
	/************************************************************
	 * Gets the destination vertex of this edge
	 * @return destination the destination vertex of this edge.
	 ************************************************************/
	public Vertex getDestination(){
		return destination;
	}	
	
	/************************************************************
	 * sets the cost of traversing this edge.
	 * @param cost the cost of traversing this edge.
	 ************************************************************/
	public void setCost(int cost){
		this.cost = cost;
	}
}
