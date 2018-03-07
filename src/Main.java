import java.util.*;

public class Main {

    public static void main(String[] args) {
        String filename;
        int[] currCoords = new int[2];
        int[] startCoords;

        // coordinates of the destination node in array format [x,y]
        int[] destCoords;

        // Contains all obstacles in string format
        PriorityQueue<String> obstacles;

        if(args.length > 0) {
            // pull filename from command line args
            filename = args[0];
            //initialize empty map
            ArrayList<String[]> map;

            //temporary new route object
            Route newPath;

            // Create new MapReader object
            MapReader reader = new MapReader(filename);
            // Get the start coordinates from the MapReader
            startCoords = reader.getInitCoords();
            currCoords = startCoords.clone();

            // Get the destination coordinates from the MapReader
            destCoords = reader.getDestinationCoords();
            // Get the coordinates of all obstacles from the MapReader
            obstacles = reader.getObstacles();
            int mapDim = reader.getMap().size();

            // Create the start node using the currCoords initial value
            Node startNode = new Node(startCoords[0], startCoords[1], destCoords[0], destCoords[1], 0);

            System.out.println("" + startNode.manDistance);
            // Create a new Route with the startNode as the only element

            // ACTUAL ALGORITHM
            System.out.println(startCoords[0] + "," + startCoords[1]);
            System.out.println(destCoords[0] + "," + destCoords[1]);


            for (int distType = 0; distType < 3; distType++) {
                Route path = new Route(startNode, mapDim, obstacles, distType);

                // Create an array of Routes to store different paths
                ArrayList<Route> routes = new ArrayList<Route>();

                // Store the first path
                routes.add(path);

                // Add the coordinates of the startNode to the visitedCoords
                routes.get(0).addVisited(startNode);
                currCoords = startCoords.clone();
                while (!Arrays.equals(currCoords,destCoords)) {


                    //for each path in routes, peak on the path (take first val), and insert resunt into edge edgeNodes

                    // iterate through all current routes
                    for (Route r : routes) {
                        r.explore(currCoords,destCoords);

                        currCoords[0] = r.path.get(r.path.size()-1).node_x;
                        currCoords[1] = r.path.get(r.path.size()-1).node_y;

                        if(Arrays.equals(currCoords,destCoords)){
                            System.out.println("Route Found");
                            System.out.println("" + (r.getDistanceTraveled()-1) + " steps taken");
                            ArrayList<ArrayList<String>> outputMap = reader.getMap();
                            for (Node n : r.path) {

                                System.out.println("\n" + n.node_x + "," + n.node_y);
                                if (!n.equals(r.path.get(0)) || !n.equals(r.path.get(r.path.size() - 1))) {
                                    outputMap.get(n.node_y).set(n.node_x, "o");
                                }
                                else{
                                    outputMap.get(n.node_y).set(n.node_x, "&");
                                }
                            }

                            for (ArrayList<String> row : outputMap) {
                                StringBuilder sb = new StringBuilder();
                                for (String c : row) {
                                    sb.append(c);
                                }
                                System.out.println(sb.toString());
                            }
                            for (Node n : r.path) {
                                outputMap.get(n.node_y).set(n.node_x, ".");
                            }
                            break;
                        }

//                        if (up.isObstacle) {
//                            up_print = " + ";
//                        } else {
//                            up_print = Integer.toString(up.totalCost);
//                        }
//
//
//                        if (down.isObstacle) {
//                            down_print = " + ";
//                        } else {
//                            down_print = Integer.toString(down.totalCost);
//                        }
//
//
//                        if (right.isObstacle) {
//                            right_print = " + ";
//                        } else {
//                            right_print = Integer.toString(right.totalCost);
//                        }
//
//
//                        if (left.isObstacle) {
//                            left_print = " + ";
//                        } else {
//                            left_print = Integer.toString(left.totalCost);
//                        }

                        //                        System.out.println("**************************");
                        //                        System.out.println("COST: " + headNode.totalCost);
                        //                        System.out.println("STEP: " + headNode.cost);
                        //                        System.out.println("[" + headNode.node_x + ", " + headNode.node_y + "]");
                        //                        System.out.println("\n  " + up_print + "  " + "\n" + left_print + " O " + right_print + "\n  " + down_print + "  ");

                        //                        if(Integer.toString(headNode.node_x)+","+Integer.toString()
                        //                    System.out.println(best_node.totalCost);


                        //                        try {
                        //                            Thread.sleep(100);
                        //                        } catch (InterruptedException e) {
                        //                            e.printStackTrace();
                        //                        }
                    }
                }
            }
            System.out.println("Exiting");
            System.exit(0);
        }
        else {
            System.out.println("No filename provided. Quitting...");
            System.exit(1);
        }


    }
}
