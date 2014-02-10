package com.apbeecham.pathfinding;

import java.awt.Color;
import java.awt.Point;
import java.util.*;

/************************************************************
 * Finds the shortest path between 2 points in a grid based
 * map.
 * @author Adam Beecham
 ************************************************************/
public class Pathfinder {

	public static final int DIJKSTRA = 0;
	public static final int ASTAR = 1;
	//cells we have already looked at
	private ArrayList<Cell>closedList;
	//cells we need to look at
	private PriorityQueue<Cell>openList;
	private boolean isRunning;

	/************************************************************
	 * Constructs a pathfinder and initialises our data
	 * structures.
	 ************************************************************/
	public Pathfinder(){
		//priority queue means that we can just pull the cells
		//with the best path from the top of the list
		openList = new PriorityQueue<Cell>();
		closedList = new ArrayList<Cell>();
	}

	/************************************************************
	 * Finds the shortest path between 2 points in a grid.
	 * @param Start the start cell for the algorithm.
	 * @param Goal the cell this algorithm must find a path to
	 * @param grid the grid to be searched.
	 * @param step the time taken to make 1 iteration of the
	 * algorithm.
	 * @param algorithm the search algorithm for finding the
	 * shortest path.
	 ************************************************************/
	public ArrayList<Cell> findShortestPath(Cell start, Cell goal, Grid grid, int step, int algorithm){

		isRunning = true;
		start.setDistanceFromStart(0);
		if(algorithm == ASTAR)
			start.setCost(euclideanDistance(start.position, goal.position));
		if(algorithm == DIJKSTRA)
			start.setCost(0);
		//begin search from start cell
		openList.add(start);

		long currentTime = System.currentTimeMillis();

		//keep looping until we find the goal or search every
		//node
		while(!openList.isEmpty() && isRunning){

			if(!isRunning)
				break;

			long timeSinceLastStep = System.currentTimeMillis() - currentTime;

			//Check if its time to take another step in the search
			if(timeSinceLastStep >= step){
				currentTime = System.currentTimeMillis();

				//show the user the progress of the last step on the grid
				grid.update();

				//get the cell with the best path
				Cell current = openList.poll();
				//we've visited this cell so we color it green
				current.setColor(Color.GREEN);

				//if we're using A-Star search, we no longer need to look at this
				//node again so we add it to the closed list
				if(algorithm == ASTAR)
					closedList.add(current);

				//start node is always magenta
				if(current == start)
					start.setColor(Color.MAGENTA);

				//goal node is always red
				if(current == goal){
					goal.setColor(Color.RED);
					grid.update();
					break;
				}

				//examine the edges of the current cell to find the best
				//neighbouring cell to navigate to based on the algorithm the user chose
				for(Edge e : current.getEdges()){

					switch(algorithm){
						case DIJKSTRA: {
							//get the cell at the end of the current edge
							Cell next = (Cell) e.getDestination();
							//use the distance from the start cell to determine where to go next.
							//dijkstra chooses closest node to the start not yet examined
							double distanceFromStart = current.getDistanceFromStart() + e.getCost();

							//blue indicates the algorithm has found the cell but not yet visited it
							if(next.getColor() != Color.GREEN && next.getColor() != Color.MAGENTA)
								next.setColor(Color.BLUE);

							//if we havent visited this cell yet, or we find a better path to it
							//then we add it to the openlist so it can be examined at a later time
							if(distanceFromStart < next.getDistanceFromStart()){
								openList.remove(next);
								next.setDistanceFromStart(distanceFromStart);
								next.setCost(distanceFromStart);
								next.setPredecessor(current);
								//add the cell to the open list where it is sorted by
								//distance from start cell
								openList.add(next);
							}
							break;
						}

						case ASTAR: {
							//get the cell at the end of the current edge
							Cell next = (Cell) e.getDestination();

							//we only look at cells once with a star
							if(closedList.contains(next))
								continue;

							double distanceFromStart = current.getDistanceFromStart() + e.getCost();
							//we use a heuristic to estimate how far away from the goal we are
							double distanceToGoal = euclideanDistance(next.getPosition(), goal.getPosition());
							//sum the start and goal distances to get an idea of how good this cell would be
							//to navigate to next. this pushes the search towards the goal.
							double estimate = distanceFromStart + distanceToGoal;
							if(next.getColor() != Color.GREEN && next.getColor() != Color.MAGENTA)
								next.setColor(Color.BLUE);
							//add the cell to the open list if it isnt already there
							if(!openList.contains(next) || distanceFromStart < next.getDistanceFromStart()){
								next.setDistanceFromStart(distanceFromStart);
								next.setCost(estimate);
								next.setPredecessor(current);
								openList.add(next);
							}
							break;
						}
					}
				}
			}
		}

		//the search is finished so we get the shortest path and return it
		ArrayList<Cell> shortestPath = new ArrayList<Cell>();
		Cell current = goal;
		shortestPath.add(current);

		//navigate backwards from the goal to the start via the shortest path
		while(current.getPredecessor()!= null){
			//store the current cell in the shortest path
			shortestPath.add((Cell)current.getPredecessor());
			current = (Cell)current.getPredecessor();
			//highlight it
			if(current != start)
				current.setColor(Color.CYAN);
		}
		//show the path to the user
		grid.update();
		//reverse the list so the path is the correct way round
		Collections.reverse(shortestPath);

		return shortestPath;
	}

	//stop the search
	public void stop(){
		isRunning = false;
	}

	//a heauristic estimate of the distance between two points in the grid.
	//it slightly overestimates the distance between two cells but provides an
	//adequate estimate for our a star search
	public double euclideanDistance(Point p1, Point p2){
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
	}
}
