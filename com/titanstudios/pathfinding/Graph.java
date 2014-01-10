package com.titanstudios.pathfinding;

import java.util.*;

/************************************************************
 * A graph consisting of vertices connected to each+other via
 * weighted edges.
 * @author Adam Beecham
 * @version 1.0
 ************************************************************/
public class Graph {
	private ArrayList<Vertex> vertices;
	
	public Graph(){
		vertices = new ArrayList<Vertex>();
	}
	
	public void addVertex(Vertex vertex){
		vertices.add(vertex);
	}
	
	public int getNumVertices(){
		return vertices.size();
	}
	
	public Vertex getVertex(int id){
		return vertices.get(id);		
	}
	
	public ArrayList<Vertex> getVertices(){
		return vertices;
	}
}
