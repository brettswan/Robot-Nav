import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String filename;
        int[] currCoords;

        // coordinates of the destination node in array format [x,y]
        int[] destCoords;

        // Contains all obstacles in string format
        PriorityQueue<String> obstacles;

        if(args.length > 0) {
            // pull filename from command line args
            filename = args[0];
            //initialize empty map
            ArrayList<String[]> map;

            // Create new MapReader object
            MapReader reader = new MapReader(filename);
            // Get the start coordinates from the MapReader
            currCoords = reader.getInitCoords();
            // Get the destination coordinates from the MapReader
            destCoords = reader.getDestinationCoords();
            // Get the coordinates of all obstacles from the MapReader
            obstacles = reader.getObstacles();

            // Create the start node using the currCoords initial value
            Node startNode = new Node(currCoords[0],currCoords[1],destCoords[0],destCoords[1],0);

            System.out.println(""+startNode.distanceFromDest);
            // Create a new Route with the startNode as the only element
            Route path = new Route(startNode);

            // Create a queue to store all visited coordinates
            PriorityQueue<String> visitedCoords = new PriorityQueue<>();

            // Create an array of Routes to store different paths
            ArrayList<Route> routes = new ArrayList<Route>();

            // Store the first path
            routes.add(path);

            // Add the coordinates of the startNode to the visitedCoords
            visitedCoords.add(Integer.toString(startNode.node_x)+","+Integer.toString(startNode.node_y));

            // ACTUAL ALGORITHM
            while(currCoords != destCoords) {
                // Intialize an empy array of Nodes to explore the fringe
                ArrayList<Node> edgeNodes = new ArrayList<>();

                for(int i=0; i<routes.size(); i++) {

                }

                for(Node node : edgeNodes){
                    int curr_x = node.node_x;
                    int curr_y = node.node_y;

                    Node up = new Node(curr_x,curr_y-1,destCoords[0],destCoords[1],node.distanceTraveled+1);
                    Node down = new Node(curr_x,curr_y+1,destCoords[0],destCoords[1],node.distanceTraveled+1);
                    Node right = new Node(curr_x+1,curr_y,destCoords[0],destCoords[1],node.distanceTraveled+1);
                    Node left = new Node(curr_x-1,curr_y,destCoords[0],destCoords[1],node.distanceTraveled+1);
                }

            }



        }
        else {
            System.out.println("No filename provided. Quitting...");
            System.exit(1);
        }


    }
}
