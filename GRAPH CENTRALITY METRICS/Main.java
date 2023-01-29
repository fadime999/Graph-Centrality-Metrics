import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static Stack path;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		path = new Stack<>();
		Graph graph_karate = new Graph();
		Graph graph_facebook = new Graph();
		// Read from the file and add a graph a
		File f = new File("karate_club_network.txt");
		Scanner text = new Scanner(f);
		while (text.hasNextLine()) {
			String[] karate_graph_edges = text.nextLine().split(" ");
			graph_karate.addEdge(karate_graph_edges[0], karate_graph_edges[1]);
		}

		for (int i = 1; i < 35; i++) {
			for (int j = i + 1; j < 35; j++) {				
				graph_karate.getShortestPath(String.format("%s", i), String.format("%s", j), path);
			}
		}
	
		System.out.println("2019510045--Fadime KAPLAN"+'\n');
		graph_karate.highestBetweenness("graph_karate");
		closenessCentralityKarate(graph_karate);
		path.clear();
		
		File f2 = new File("facebook_social_network.txt");
		Scanner text2 = new Scanner(f2);
		while (text2.hasNextLine()) {
			String[] facebook_grap_edges = text2.nextLine().split(" ");
			graph_facebook.addEdge(facebook_grap_edges[0], facebook_grap_edges[1]);
		}
//		for (int i = 1; i < 1519; i++) {
//			for (int j = i + 1; j < 1519; j++) {
//				graph_facebook.getShortestPath(String.format("%s", i), String.format("%s", j), path);
//
//			}
//		}
//		graph_facebook.highestBetweenness("graph_facebook");
//		closenessCentralityFacebook(graph_facebook);
		
	}

	public static double closenessCentralityKarateValues(Graph graph, String a) {
		double value = 0;
		for (int j = 1; j < 35; j++) {
			if (!a.equalsIgnoreCase(String.format("%s", j))) {
				value = value + graph.getShortestPath(String.format("%s", a), String.format("%s", j), path);
			}
		}
		return (1 / value);
	}

	public static void closenessCentralityKarate(Graph graph) {
		double max = 0.0;
		double temp = 0.0;
		String node = null;
		for (int i = 1; i < 35; i++) {
			temp = closenessCentralityKarateValues(graph, String.format("%s", i));
			if (temp> max) {
				max = temp;
				node = String.format("%s", i);
			}
		}
		System.out.println("Zachary Karate Club Network – The Highest Node for Closeness and the value: " + "Node->"
				+ node + " Value->" + max+'\n');
	}

	public static double closenessCentralityFacebookValues(Graph graph, String a) {
		double value = 0;
		for (int j = 1; j < 1519; j++) {
			if (!a.equalsIgnoreCase(String.format("%s", j))) {
				value = value + graph.getShortestPath(String.format("%s", a), String.format("%s", j), path);
			}
		}
		return (1 / value);
	}

	public static void closenessCentralityFacebook(Graph graph) {
		double max = 0.0;
		double temp = 0.0;
		String node = null;
		for (int i = 1; i < 1519; i++) {
			temp = closenessCentralityFacebookValues(graph, String.format("%s", i));
			if (temp != 1) {
				if (temp > max) {
					max = temp;
					node = String.format("%s", i);
				}
			}
		}
		System.out.println("Facebook Social Network - The Highest Node for Betweennes and the value: " + "Node->" + node
				+ " Value->" + max);
	}
}
