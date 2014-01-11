Pathfinding Visualizer
======================

Description
-----------

Pathfinding Visualizer is a simple java swing application for visualizing pathfinding algorithms. It consists of a 50x50 grid that simply steps through the pathfinding algorithm highlighting each grid cell in a different color depending on its state.

####  Color Key

* Green   - Cell has been visited and all its edges examined.
* Blue    - Cell has been found by the pathfinder but is yet to be examined
* Cyan    - Cell is part of the shortest path from the start cell to the goal
* Magenta - Start cell
* Red     - Goal cell


Algorithms
----------
The program currently allows you to search the grid with either dijkstra's algorithm or A*.

UI
--

* Basic user controls (start & reset)
* User defined execution speed
* User defined start/goal cells (select which to place from a drop down list and then click on the desired grid cell)

How to Run
----------
1. Download the [latest version of java] (http://www.oracle.com/technetwork/java/javase/downloads/java-se-jre-7-download-432155.html)
2. Download the [latest release](https://github.com/apbeecham/pathfinding/releases)
3. Run the jar file

Feel free to use/edit the source files for any purpose you require.
