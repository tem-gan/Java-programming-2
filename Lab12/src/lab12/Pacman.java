package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {
	private static Queue<Node> neighbors= new LinkedList<Node>();
	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		// TODO for assignment12
		neighbors.add(end);
		end.visited=true;
		end.parent=null;
		while(!neighbors.isEmpty()) {
			Node current = neighbors.remove();
			if(current.content=='G') {
				while(current.parent!=null) {
					if(current.parent.content!='S') {
						current.parent.content='.';
					}
					current=current.parent;
				}
			}
			getNeighbors(current);
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.write(height+" "+width+"\n");
			for(int i=0;i<height;i++) {
				for(int x=0; x<width; x++) {
					output.write(maze[i][x].content);
				}
				output.write("\n");
			}
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}

	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			// FILL IN
			 String[] dimensions = input.readLine().split(" ");
			  height = Integer.parseInt(dimensions[0]);
			  width = Integer.parseInt(dimensions[1]);
			  System.out.println(height + " " + width);
			  maze = new Node[height][width];
			  
			  for(int i = 0; i < height; i++){
					String line = input.readLine();
					for(int x = 0; x < width; x++){
						char content = line.charAt(x);
						maze[i][x] = new Node(i,x, content);
						
						
						if(content == 'S'){
							startX= i;	
							startY=x;
						}
				 	}
				  }
				  input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNodeNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node north, south, east, west;
		north = maze[currentNode.row - 1][currentNode.col]; 
		if(north.content != 'X' && (north.visited == false)){
			neighbors.add(north);
			north.visited = true;
			north.parent = currentNode;
		}
		south = maze[currentNode.row + 1][currentNode.col]; 
		if(south.content != 'X' && (south.visited == false)){
			neighbors.add(south);
			south.visited = true;
			south.parent = currentNode;
		}
		east = maze[currentNode.row][currentNode.col - 1]; 
		if(east.content != 'X' && (east.visited == false)){
			neighbors.add(east);
			east.visited = true;
			east.parent = currentNode;
		}
		west = maze[currentNode.row][currentNode.col + 1]; 
		if(west.content != 'X' && (west.visited == false)){
			neighbors.add(west);
			west.visited = true;
			west.parent = currentNode;
		}

		return null;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		try{
			  
			BufferedReader input = new BufferedReader(
					new FileReader(outputFileName));
			  FileWriter translate = new FileWriter(outputFileName);
				
				translate.write(height + " " + width + "\n");
				for(int i = 0; i < height; i++){
					for(int x = 0; x < width; x++){
						translate.write(maze[i][x].content);
					}
					translate.write("\n");
					}
				translate.close();
			} 
		   catch(IOException ex){
				System.out.print("FileNotFoundExceptioin");
			}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
	}
	public static void main(String arg[]){
		String inputFileName="mazes/bigMaze.txt";
		String outputFileName="test.txt";
		Pacman test= new Pacman(inputFileName, outputFileName);
		test.writeOutput();
	}
}
