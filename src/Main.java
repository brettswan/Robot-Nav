import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static java.lang.Thread.sleep;

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
            Node startNode = new Node(currCoords[0],currCoords[1],destCoords[0],destCoords[1],0, obstacles);

            System.out.println(""+startNode.manDistance);
            // Create a new Route with the startNode as the only element

            Route path = new Route(startNode);

            // Create a queue to store all visited coordinates
            ArrayList<Node> visitedNodes = new ArrayList<Node>();

            // Create an array of Routes to store different paths
            ArrayList<Route> routes = new ArrayList<Route>();

            // Store the first path
            routes.add(path);

            // Add the coordinates of the startNode to the visitedCoords
            visitedNodes.add(startNode);

            // ACTUAL ALGORITHM
            System.out.println(currCoords[0] + "," + currCoords[1]);
            System.out.println(destCoords[0] + "," + destCoords[1]);

            while(!currCoords.equals(destCoords)) {
                // Intialize an empy array of Nodes to explore the fringe
                //ArrayList<Node> edgeNodes = new ArrayList<>();

                //for each path in routes, peak on the path (take first val), and insert resunt into edge edgeNodes

                for(Route r : routes){

                    ArrayList<Node> choices = new ArrayList<>(); // but how are we treating the obsticles

                    Node headNode = r.path.peek();

                    int curr_x = headNode.node_x;
                    int curr_y = headNode.node_y;

                    // printing variables for terminal
                    String up_print = "";
                    String down_print = "";
                    String right_print = "";
                    String left_print = "";
                    ////

                    //up
                    Node up = new Node(curr_x,curr_y-1,destCoords[0],destCoords[1],headNode.cost+1, obstacles);
                    if ((!up.isObstacle) && (!visitedNodes.contains(up))){choices.add(up);}

                    //down
                    Node down = new Node(curr_x,curr_y+1,destCoords[0],destCoords[1],headNode.cost+1,obstacles);
                    if ((!down.isObstacle) && (!visitedNodes.contains(down))){choices.add(down);}

                    //right
                    Node right = new Node(curr_x+1,curr_y,destCoords[0],destCoords[1],headNode.cost+1,obstacles);
                    if ((!right.isObstacle) && (!visitedNodes.contains(right))){choices.add(right);}

                    //left
                    Node left = new Node(curr_x-1,curr_y,destCoords[0],destCoords[1],headNode.cost+1,obstacles);
                    if ((!left.isObstacle) && (!visitedNodes.contains(left))){choices.add(left);}


                    Node best_node = choices.get(0);
                    for (Node choice_node : choices){
                        if (choice_node.totalCost < best_node.totalCost){
                            best_node = choice_node; // else best node stays the same
                        }
                        if (choice_node.totalCost == best_node.totalCost){
                            // pick one with smallest i value {i,j]
                            if(choice_node.node_x < best_node.node_x){
                                best_node = choice_node; // else best node stays the same
                            }
                        }
                    }

                    if(up.isObstacle){ up_print = " + "; }
                    else{ up_print = Integer.toString(up.totalCost); }


                    if(down.isObstacle){ down_print = " + ";}
                    else{down_print = Integer.toString(down.totalCost);}


                    if(right.isObstacle){ right_print = " + ";}
                    else{right_print = Integer.toString(right.totalCost);}


                    if(left.isObstacle){left_print = " + "; }
                    else{left_print = Integer.toString(left.totalCost);}

                    System.out.println("**************************");

                    System.out.println(headNode.node_x + ", " + headNode.node_y);
                    System.out.println("\n " + up_print + "  " + "\n" + left_print + " O " + right_print + "\n  " + down_print + "  ");
                    System.out.println(best_node.totalCost);


                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // re assign values
                    //curr_x = best_node.node_x;
                    //curr_y = best_node.node_y;

                    //currCoords[0] = best_node.node_x;
                    //currCoords[1] = best_node.node_y;

                    r.path.push(best_node);
                    visitedNodes.add(best_node);



                }

            }



        }
        else {
            System.out.println("No filename provided. Quitting...");
            System.exit(1);
        }


    }
}
