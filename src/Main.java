import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String filename;
        int[] currCoords;
        int distanceTraveled;
        int[] destCoords;

        if(args.length > 0) {
            filename = args[0];
            ArrayList<String[]> map;
            distanceTraveled = 0;

            MapReader reader = new MapReader(filename);
            currCoords = reader.getInitCoords();
            destCoords = reader.getDestinationCoords();
            Node startNode = new Node(currCoords[0],currCoords[1],destCoords[0],destCoords[1],distanceTraveled);
            Route path = new Route(startNode);
            PriorityQueue<String> visitedCoords = new PriorityQueue<>();
            visitedCoords.add(Integer.toString(startNode.node_x)+","+Integer.toString(startNode.node_y));

            while(currCoords != destCoords) {

            }



        }
        else {
            System.out.println("No filename provided. Quitting...");
            System.exit(1);
        }


    }
}
