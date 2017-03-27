package edu.cmu.qatar.cs214.hw.hw1.graph;

import java.util.Arrays;

import edu.cmu.qatar.cs214.hw.hw1.staff.Vertex;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdjacencyMatrixGraph graph2 = new AdjacencyMatrixGraph(10);
		AdjacencyListGraph graph = new AdjacencyListGraph(100);
		
		Vertex shahan = new Vertex("Shahan");
		Vertex posha = new Vertex("Posha");
		Vertex hira = new Vertex("Hira");
		Vertex malath = new Vertex("Malath");
		Vertex sharjeel = new Vertex("Sharjeel");
		Vertex yasser = new Vertex("Yasser");
		Vertex musab = new Vertex("Musab");
		Vertex sannan = new Vertex("Sannan");
		Vertex ahmed = new Vertex("Ahmed");
		Vertex qasim = new Vertex("Qasim");
		
		graph.addVertex(shahan);
		graph.addVertex(posha);
		graph.addVertex(hira);
		graph.addVertex(malath);
		graph.addVertex(sharjeel);
		graph.addVertex(yasser);
		graph.addVertex(musab);
		graph.addVertex(sannan);
		graph.addVertex(ahmed);
		graph.addVertex(qasim);
		
		graph.addEdge(shahan, posha);
		graph.addEdge(shahan, hira);
		graph.addEdge(shahan, malath);
		graph.addEdge(shahan, malath);
		graph.addEdge(posha, sharjeel);
		graph.addEdge(sannan,ahmed);
		
		// Added later to test suggestFriends //
		//graph.addEdge(hira,malath);
		//graph.addEdge(hira,sannan);
		//graph.addEdge(posha, hira);
		///////////////////////////////////
		
		graph.addEdge(sannan, qasim);
		graph.addEdge(yasser, musab);
		graph.addEdge(posha, yasser);
		graph.addEdge(sannan, shahan);
		
		
		System.out.println(graph.isAdjacent(shahan, hira));
		System.out.println(graph.isAdjacent(posha, hira));
		System.out.println(graph.isAdjacent(shahan, sannan));
		System.out.println(graph.isAdjacent(sannan, shahan));
		System.out.println(graph.isAdjacent(shahan, shahan));
		System.out.println(graph.isAdjacent(qasim, sannan));
		
		System.out.println(Arrays.toString(graph.getNeighbors(shahan)));
		System.out.println(Arrays.toString(graph.getNeighbors(sannan)));
		System.out.println(Arrays.toString(graph.getNeighbors(qasim)));
		System.out.println(Arrays.toString(graph.getNeighbors(posha)));
		System.out.println(Arrays.toString(graph.getNeighbors(yasser)));
		System.out.println(Arrays.toString(graph.getNeighbors(malath)));
		System.out.println(Arrays.toString(graph.getNeighbors(hira)));
		System.out.println(Arrays.toString(graph.getNeighbors(ahmed)));
		System.out.println(Arrays.toString(graph.getNeighbors(sharjeel)));
		System.out.println(Arrays.toString(graph.getNeighbors(musab)));

		Algorithms temp = new Algorithms();
		System.out.println(Arrays.toString(temp.commonFriends(graph, posha, sannan)));
		System.out.println(temp.suggestFriend(graph, shahan));
		System.out.println(temp.shortestDistance(graph,shahan, musab));
		System.out.println(temp.shortestDistance(graph,shahan, yasser));
		System.out.println(temp.shortestDistance(graph,ahmed, musab));
		
		System.out.println(Arrays.toString(graph.getVertices()));
		//System.out.println(Arrays.toString(graph2.getVertices()));
		
		
		//Now we test graph2
		
		graph2.addVertex(shahan);
		graph2.addVertex(posha);
		graph2.addVertex(hira);
		graph2.addVertex(malath);
		graph2.addVertex(sharjeel);
		graph2.addVertex(yasser);
		graph2.addVertex(musab);
		graph2.addVertex(sannan);
		graph2.addVertex(ahmed);
		graph2.addVertex(qasim);
		
		graph2.addEdge(shahan, posha);
		graph2.addEdge(shahan, hira);
		graph2.addEdge(shahan, malath);
		graph2.addEdge(shahan, malath);
		graph2.addEdge(posha, sharjeel);
		graph2.addEdge(sannan,ahmed);
		
		// Added later to test suggestFriends //
		//graph2.addEdge(hira,malath);
		//graph2.addEdge(hira,sannan);
		//graph2.addEdge(posha, hira);
		///////////////////////////////////
		
		graph2.addEdge(sannan, qasim);
		graph2.addEdge(yasser, musab);
		graph2.addEdge(posha, yasser);
		graph2.addEdge(sannan, shahan);
		
		
		System.out.println(graph2.isAdjacent(shahan, hira));
		System.out.println(graph2.isAdjacent(posha, hira));
		System.out.println(graph2.isAdjacent(shahan, sannan));
		System.out.println(graph2.isAdjacent(sannan, shahan));
		System.out.println(graph2.isAdjacent(shahan, shahan));
		System.out.println(graph2.isAdjacent(qasim, sannan));
		
		System.out.println(Arrays.toString(graph2.getNeighbors(shahan)));
		System.out.println(Arrays.toString(graph2.getNeighbors(sannan)));
		System.out.println(Arrays.toString(graph2.getNeighbors(qasim)));
		System.out.println(Arrays.toString(graph2.getNeighbors(posha)));
		System.out.println(Arrays.toString(graph2.getNeighbors(yasser)));
		System.out.println(Arrays.toString(graph2.getNeighbors(malath)));
		System.out.println(Arrays.toString(graph2.getNeighbors(hira)));
		System.out.println(Arrays.toString(graph2.getNeighbors(ahmed)));
		System.out.println(Arrays.toString(graph2.getNeighbors(sharjeel)));
		System.out.println(Arrays.toString(graph2.getNeighbors(musab)));

		Algorithms temp2 = new Algorithms();
		System.out.println(Arrays.toString(temp2.commonFriends(graph2, posha, sannan)));
		System.out.println(temp2.suggestFriend(graph2, shahan));
		System.out.println(temp2.shortestDistance(graph2,shahan, musab));
		System.out.println(temp2.shortestDistance(graph2,shahan, yasser));
		System.out.println(temp2.shortestDistance(graph2,ahmed, musab));
		
		System.out.println(Arrays.toString(graph2.getVertices()));
		
	}

}
