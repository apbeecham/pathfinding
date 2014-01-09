Pathfinding Visualizer
======================

### Note


This program is in its early stages and currently only offers basic funtionality.

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

* Dijkstra's Algorithm
* TODO - Breadth First
* TODO - Best First
* A*

Features
--------

* Basic user controls (start & reset)
* User defined execution speed
* TODO - User defined start/goal cells
* TODO - Obstacles
