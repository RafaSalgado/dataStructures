

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra {

  public static void main(String[] args) throws IOException {


	Graph g ;
	Vertex[] vertices;

	BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	int numCase, conex, pc,A, B;
	String str, spl[], cadena, arr[];


	numCase= Integer.parseInt(br.readLine());

	for (int i = 0; i < numCase; i++) {
	  System.out.println("Caso #" + (i+1)+":");

	  g= new Graph();

	  str=br.readLine();
	  spl=str.split("\\s+");
	  conex=Integer.parseInt(spl[1]);
	  pc=Integer.parseInt(spl[0]);
	  vertices = new Vertex[pc];

	  for (int j = 0; j < vertices.length; j++) {
		vertices[j] = new Vertex(j + "");
		g.addVertex(vertices[j], true);
	  }

	  Edge[] edges = new Edge[conex];

	  for (int j = 0; j < conex; j++) {
		cadena=br.readLine();
		arr=cadena.split("\\s+");
		A=Integer.parseInt(arr[0]);
		B=Integer.parseInt(arr[1]);
		edges[j] = new Edge(vertices[A], vertices[B], Integer.parseInt(arr[2]));
	  }

	  for(Edge e: edges){
		g.addEdge(e.getOne(), e.getTwo(), e.getWeight());
	  }


	  if(conex==0){
		System.out.println("Inalcanzable");
	  }
	  else{
		Dijkstra dijkstra = new Dijkstra(g, vertices[Integer.parseInt(spl[2])].getLabel());
		System.out.println(dijkstra.getDistanceTo(spl[3]));
	  }



	}




//	Graph graph = new Graph();
//	Vertex[] vertices = new Vertex[6];
//
//	for(int i = 0; i < vertices.length; i++){
//		vertices[i] = new Vertex(i + "");
//        graph.addVertex(vertices[i], true);
//
//		        }
//
//		        Edge[] edges = new Edge[9];
//		        edges[0] = new Edge(vertices[0], vertices[1], 7);
//		        edges[1] = new Edge(vertices[0], vertices[2], 9);
//		        edges[2] = new Edge(vertices[0], vertices[5], 14);
//		        edges[3] = new Edge(vertices[1], vertices[2], 10);
//		        edges[4] = new Edge(vertices[1], vertices[3], 15);
//		        edges[5] = new Edge(vertices[2], vertices[3], 11);
//		        edges[6] = new Edge(vertices[2], vertices[5], 2);
//		        edges[7] = new Edge(vertices[3], vertices[4], 6);
//		        edges[8] = new Edge(vertices[4], vertices[5], 9);
//
//
//		        for(Edge e: edges){
//		            graph.addEdge(e.getOne(), e.getTwo(), e.getWeight());
//		        }
//
//		        Dijkstra dijkstra = new Dijkstra(graph, vertices[0].getLabel());
//
//		        System.out.println(dijkstra.getDistanceTo("5"));
//		        System.out.println(dijkstra.getPathTo("5"));
  }




  public static class Vertex {

		    private ArrayList<Edge> neighborhood;
		    private String label;

		    /*
		      * @param label The unique label associated with this Vertex
		     */

		    public Vertex(String label){
		        this.label = label;
		        this.neighborhood = new ArrayList<Edge>();
		    }

		    /*
		     * This method adds an Edge to the incidence neighborhood of this graph iff
		     * the edge is not already present.
		     * @param edge The edge to add
		     */

		    public void addNeighbor(Edge edge){
			        if(this.neighborhood.contains(edge)){
		            return;
		        }

		        this.neighborhood.add(edge);
		    }

		    /*
		     * @param other The edge for which to search
		     * @return true iff other is contained in this.neighborhood
		     */

		    public boolean containsNeighbor(Edge other){
		        return this.neighborhood.contains(other);
		    }

		    /*
		     * @param index The index of the Edge to retrieve
		     * @return Edge The Edge at the specified index in this.neighborhood
		     */

		    public Edge getNeighbor(int index){
		        return this.neighborhood.get(index);
		    }

		    /*
			     * @param index The index of the edge to remove from this.neighborhood
		     * @return Edge The removed Edge
		     */

		    Edge removeNeighbor(int index){
			        return this.neighborhood.remove(index);
		    }

		    /*
		     * @param e The Edge to remove from this.neighborhood
		     */

		    public void removeNeighbor(Edge e){
		        this.neighborhood.remove(e);
		      }

		    /*
		     * @return int The number of neighbors of this Vertex
		     */

		    public int getNeighborCount(){
		        return this.neighborhood.size();
		    }

		    /*
		     * @return String The label of this Vertex
		     */

		    public String getLabel(){
		        return this.label;
		    }

		    /*
			     * @return String A String representation of this Vertex
		     */

		    public String toString(){
		        return "Vertex " + label;
		    }
		    /*
		     * @return The hash code of this Vertex's label
		     */

		    public int hashCode(){
		        return this.label.hashCode();
		    }

			/*
		     * @param other The object to compare
		     * @return true iff other instanceof Vertex and the two Vertex objects have the same label
		     */

		    public boolean equals(Object other){
		        if(!(other instanceof Vertex)){
		            return false;
		        }
		        Vertex v = (Vertex)other;
		        return this.label.equals(v.label);
		    }

		    /*
		     * @return ArrayList<Edge> A copy of this.neighborhood. Modifying the returned
		     * ArrayList will not affect the neighborhood of this Vertex
		     */

		    public ArrayList<Edge> getNeighbors(){
		        return new ArrayList<Edge>(this.neighborhood);
		    }

	}




  public static class Edge implements Comparable<Edge> {
		    private Vertex one, two;
		    private int weight;

		    /*
		     * @param one The first vertex in the Edge
		     * @param two The second vertex in the Edge
		     */

		    public Edge(Vertex one, Vertex two){
		        this(one, two, 1);
		    }



		    /*
		     * @param one The first vertex in the Edge
		     * @param two The second vertex of the Edge
		     * @param weight The weight of this Edge
		     */

		    public Edge(Vertex one, Vertex two, int weight){
		        this.one = (one.getLabel().compareTo(two.getLabel()) <= 0) ? one : two;
		        this.two = (this.one == one) ? two : one;
		        this.weight = weight;
		    }

		    /*
		     * @param current
		     * @return The neighbor of current along this Edge
		     */

		    public Vertex getNeighbor(Vertex current){
		        if(!(current.equals(one) || current.equals(two))){

		            return null;
		        }

		        return (current.equals(one)) ? two : one;
		    }


		    /*
		     * @return Vertex this.one
		     */

		    public Vertex getOne(){
		        return this.one;
		    }

		    /*
		     * @return Vertex this.two
		     */

		    public Vertex getTwo(){
		        return this.two;
		    }
		    /*
		     * @return int The weight of this Edge

		     */

		    public int getWeight(){
		        return this.weight;
		    }


		    /*
		     * @param weight The new weight of this Edge
		     */

		    public void setWeight(int weight){
		        this.weight = weight;
		    }

		    /*
		     * Note that the compareTo() method deviates from

		     * the specifications in the Comparable interface. A

		     * return value of 0 does not indicate that this.equals(other).

		     * The equals() method checks the Vertex endpoints, while the

		     * compareTo() is used to compare Edge weights

		     * @param other The Edge to compare against this

		     * @return int this.weight - other.weight

		     */

		    public int compareTo(Edge other){
		        return this.weight - other.weight;
		    }

		    /*
		     * @return String A String representation of this Edge
		     */

		    public String toString(){
		        return "({" + one + ", " + two + "}, " + weight + ")";
		    }


		    /*
		     * @return int The hash code for this Edge
		     */

		    public int hashCode(){
		        return (one.getLabel() + two.getLabel()).hashCode();
		    }



		    /*
		     * @param other The Object to compare against this

		     * @return ture iff other is an Edge with the same Vertices as this
	    */

		    public boolean equals(Object other){
		        if(!(other instanceof Edge)){
		            return false;
		        }

		        Edge e = (Edge)other;

		        return e.one.equals(this.one) && e.two.equals(this.two);
		    }

		}



  public static class Graph {
			private HashMap<String, Vertex> vertices;

		    private HashMap<Integer, Edge> edges;

		    public Graph(){

		        this.vertices = new HashMap<String, Vertex>();

		        this.edges = new HashMap<Integer, Edge>();
		    }

		    /*
		     * This constructor accepts an ArrayList<Vertex> and populates
		     * this.vertices. If multiple Vertex objects have the same label,
		     * then the last Vertex with the given label is used.
		     *
		     * @param vertices The initial Vertices to populate this Graph
		     */

		    public Graph(ArrayList<Vertex> vertices){
		        this.vertices = new HashMap<String, Vertex>();
		        this.edges = new HashMap<Integer, Edge>();

		        for(Vertex v: vertices){
		            this.vertices.put(v.getLabel(), v);
		        }
		    }

		    /*
		     * This method adds am edge between Vertices one and two

		     * of weight 1, if no Edge between these Vertices already

		     * exists in the Graph.
		     * @param one The first vertex to add
		     * @param two The second vertex to add
		     * @return true iff no Edge relating one and two exists in the Graph
		     */

		    public boolean addEdge(Vertex one, Vertex two){
		        return addEdge(one, two, 1);
		    }

		    /*
		     * Accepts two vertices and a weight, and adds the edge
		     * ({one, two}, weight) iff no Edge relating one and two
		     * exists in the Graph.

		     * @param one The first Vertex of the Edge
		     * @param two The second Vertex of the Edge
		     * @param weight The weight of the Edge
		     * @return true iff no Edge already exists in the Graph
		     */

		    public boolean addEdge(Vertex one, Vertex two, int weight){
		        if(one.equals(two)){
		            return false;
		        }

		        //ensures the Edge is not in the Graph
		        Edge e = new Edge(one, two, weight);
		        if(edges.containsKey(e.hashCode())){
		            return false;
		        }

		        //and that the Edge isn't already incident to one of the vertices

		        else if(one.containsNeighbor(e) || two.containsNeighbor(e)){
		            return false;
		        }

		        edges.put(e.hashCode(), e);
		        one.addNeighbor(e);
		        two.addNeighbor(e);
		        return true;
		    }

		    /*
		     * @param e The Edge to look up
		     * @return true iff this Graph contains the Edge e
		     */

		    public boolean containsEdge(Edge e){
		        if(e.getOne() == null || e.getTwo() == null){
		            return false;
			        }

		        return this.edges.containsKey(e.hashCode());
		    }


		    /*
		     * This method removes the specified Edge from the Graph,
		     * including as each vertex's incidence neighborhood.
		     * @param e The Edge to remove from the Graph
		     * @return Edge The Edge removed from the Graph
		     */

		    public Edge removeEdge(Edge e){
		       e.getOne().removeNeighbor(e);
		       e.getTwo().removeNeighbor(e);
		       return this.edges.remove(e.hashCode());
		    }

		    /*
		     * @param vertex The Vertex to look up
		     * @return true iff this Graph contains vertex
		     */

		    public boolean containsVertex(Vertex vertex){
		        return this.vertices.get(vertex.getLabel()) != null;
		    }

		    /*
		     * @param label The specified Vertex label
		     * @return Vertex The Vertex with the specified label
		     */

		    public Vertex getVertex(String label){
		        return vertices.get(label);
		    }

		    /*
		     * This method adds a Vertex to the graph. If a Vertex with the same label
		     * as the parameter exists in the Graph, the existing Vertex is overwritten
		     * only if overwriteExisting is true. If the existing Vertex is overwritten,
		     * the Edges incident to it are all removed from the Graph.
		     * @param vertex
		     * @param overwriteExisting
		     * @return true iff vertex was added to the Graph
		     */

		    public boolean addVertex(Vertex vertex, boolean overwriteExisting){
		        Vertex current = this.vertices.get(vertex.getLabel());
		        if(current != null){
		            if(!overwriteExisting){
		                return false;
		            }

		            while(current.getNeighborCount() > 0){
		                this.removeEdge(current.getNeighbor(0));
		            }
		        }

		        vertices.put(vertex.getLabel(), vertex);
		        return true;

		    }

		    /*
		     * @param label The label of the Vertex to remove
		     * @return Vertex The removed Vertex object
		     */

		    public Vertex removeVertex(String label){
		        Vertex v = vertices.remove(label);
		        while(v.getNeighborCount() > 0){
		            this.removeEdge(v.getNeighbor((0)));
		        }
	     return v;

		    }

		    /*
		     * @return Set<String> The unique labels of the Graph's Vertex objects
		     */

		    public Set<String> vertexKeys(){
		        return this.vertices.keySet();
		    }

		    /*
		     * @return Set<Edge> The Edges of this graph
		     */

		    public Set<Edge> getEdges(){
		        return new HashSet<Edge>(this.edges.values());
		    }
		}




  public static class Dijkstra {


		    private Graph graph;
		    private String initialVertexLabel;
		    private HashMap<String, String> predecessors;
		    private HashMap<String, Integer> distances;
		    private PriorityQueue<Vertex> availableVertices;
		    private HashSet<Vertex> visitedVertices;

		    /**
		     * This constructor initializes this Dijkstra object and executes
		     * Dijkstra's algorithm on the graph given the specified initialVertexLabel.
		     * After the algorithm terminates, the shortest a-b paths and the corresponding
		     * distances will be available for all vertices b in the graph.
		     *

		     * @param graph The Graph to traverse
		     * @param initialVertexLabel The starting Vertex label
		     * @throws IllegalArgumentException If the specified initial vertex is not in the Graph
		     */

		    public Dijkstra(Graph graph, String initialVertexLabel){

		        this.graph = graph;

		        Set<String> vertexKeys = this.graph.vertexKeys();

		        if(!vertexKeys.contains(initialVertexLabel)){
		            throw new IllegalArgumentException("The graph must contain the initial vertex.");
		        }

		        this.initialVertexLabel = initialVertexLabel;
		        this.predecessors = new HashMap<String, String>();
		        this.distances = new HashMap<String, Integer>();
		        this.availableVertices = new PriorityQueue<Vertex>(vertexKeys.size(), new Comparator<Vertex>(){



		            public int compare(Vertex one, Vertex two){

		                int weightOne = Dijkstra.this.distances.get(one.getLabel());

		                int weightTwo = Dijkstra.this.distances.get(two.getLabel());

		                return weightOne - weightTwo;

		            }

		        });


		        this.visitedVertices = new HashSet<Vertex>();



		        //for each Vertex in the graph

		        //assume it has distance infinity denoted by Integer.MAX_VALUE

		        for(String key: vertexKeys){

		            this.predecessors.put(key, null);

		            this.distances.put(key, Integer.MAX_VALUE);

		        }


		        //the distance from the initial vertex to itself is 0

		        this.distances.put(initialVertexLabel, 0);

		        //and seed initialVertex's neighbors

		        Vertex initialVertex = this.graph.getVertex(initialVertexLabel);

		        ArrayList<Edge> initialVertexNeighbors = initialVertex.getNeighbors();

		        for(Edge e : initialVertexNeighbors){

		            Vertex other = e.getNeighbor(initialVertex);

		            this.predecessors.put(other.getLabel(), initialVertexLabel);

		            this.distances.put(other.getLabel(), e.getWeight());

		            this.availableVertices.add(other);

		        }

		        this.visitedVertices.add(initialVertex);

		        //now apply Dijkstra's algorithm to the Graph

		        processGraph();

		    }



		    /*
		     * This method applies Dijkstra's algorithm to the graph using the Vertex
		     * specified by initialVertexLabel as the starting point.
		     *
		     * @post The shortest a-b paths as specified by Dijkstra's algorithm and
		     *       their distances are available

		     */

		    private void processGraph(){



		        //as long as there are Edges to process

		        while(this.availableVertices.size() > 0){
		          //pick the cheapest vertex

		            Vertex next = this.availableVertices.poll();

		            int distanceToNext = this.distances.get(next.getLabel());


		            //and for each available neighbor of the chosen vertex

		            List<Edge> nextNeighbors = next.getNeighbors();

		            for(Edge e: nextNeighbors){

		                Vertex other = e.getNeighbor(next);

		                if(this.visitedVertices.contains(other)){

		                    continue;

		                }



		                //we check if a shorter path exists

		                //and update to indicate a new shortest found path

		                //in the graph

		                int currentWeight = this.distances.get(other.getLabel());

		                int newWeight = distanceToNext + e.getWeight();

		                if(newWeight < currentWeight){

		                    this.predecessors.put(other.getLabel(), next.getLabel());

		                    this.distances.put(other.getLabel(), newWeight);

		                    this.availableVertices.remove(other);

		                    this.availableVertices.add(other);

		                }

		            }


		            // finally, mark the selected vertex as visited

		            // so we don't revisit it

		            this.visitedVertices.add(next);

		        }

		    }

		    /*
		     * @param destinationLabel The Vertex whose shortest path from the initial Vertex is desired
		     * @return LinkedList<Vertex> A sequence of Vertex objects starting at the
		     *         initial Vertex and terminating at the Vertex specified by destinationLabel.
		     *         The path is the shortest path specified by Dijkstra's algorithm.
		     */

		    public List<Vertex> getPathTo(String destinationLabel){

		        LinkedList<Vertex> path = new LinkedList<Vertex>();
		        path.add(graph.getVertex(destinationLabel));
		        while(!destinationLabel.equals(this.initialVertexLabel)){

		            Vertex predecessor = graph.getVertex(this.predecessors.get(destinationLabel));
		            destinationLabel = predecessor.getLabel();
		            path.add(0, predecessor);

		        }

		        return path;
		    }


		    /*
		     * @param destinationLabel The Vertex to determine the distance from the initial Vertex

		     * @return int The distance from the initial Vertex to the Vertex specified by destinationLabel

		     */

		    public int getDistanceTo(String destinationLabel){

		        return this.distances.get(destinationLabel);

		    }


		}



}
