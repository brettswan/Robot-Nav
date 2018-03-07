import java.util.*;

public class Route {
    //    Stack<Node> path = new Stack<>();
    ArrayList<Node> path;
    ArrayList<String> visitedNodes = new ArrayList<String>();
    Boolean isLeaf;
    Boolean hasArrived;
    int mapDim;
    int pathLength;
    PriorityQueue<String> obstacles;
    int distanceType;

    public Route(Node n, int mapDimensions, PriorityQueue<String> obstacles, int distanceType) {
        this.path = new ArrayList<>();
        this.path.add(n);
        this.isLeaf = false;
        this.hasArrived = false;
        this.mapDim = mapDimensions;
        this.pathLength = this.path.size();
        this.obstacles = obstacles;
        this.distanceType = distanceType;

    }

    public Route() {
        this.path = new ArrayList<>();
        this.isLeaf = false;
        this.hasArrived = false;
        this.pathLength = this.path.size();
    }

    public void addNode(Node n) {
        this.path.add(n);
    }

    public int getDistanceTraveled() {
        return this.path.size();
    }

    public void addVisited(Node n) {
        this.visitedNodes.add("" + n.node_x + "," + n.node_y);
    }

    public void addVisitedString(String coords) {
        this.visitedNodes.add(coords);
    }

    public void setLeaf() {
        this.isLeaf = true;
    }

    public String obs_string_format(int x, int y) {

        String output = Integer.toString(x) + "," + Integer.toString(y);
        return output;
    }

    public void explore(int[] currCoords, int[] destCoords) {

        //create a new node to contain the headnode data
        Node headNode = this.path.get(this.path.size() - 1);
        int curr_x = headNode.node_x;
        int curr_y = headNode.node_y;

        currCoords[0] = curr_x;
        currCoords[1] = curr_y;

        //check if the headnode is the destination
        if (Arrays.equals(currCoords, destCoords)) {
            this.hasArrived = true;
        }

//        // if it is, break the for loop and set a boolean that will break the while loop
//        if(r.hasArrived){
//            successRoute = r;
//            done = true;
//            break;
//        }


        //if the route has not hit a dead end and has not arrived, explore the fringe
        if (!this.isLeaf && !this.hasArrived) {
            // create new arraylist to hold the fringe nodes
            ArrayList<Node> choices = new ArrayList<>(); // but how are we treating the obstacles


            // printing variables for terminal
            String up_print = "";
            String down_print = "";
            String right_print = "";
            String left_print = "";
            ////


            //up
            if (!this.obstacles.contains(obs_string_format(curr_x, curr_y - 1))) {
                Node up = new Node(curr_x, curr_y - 1, destCoords[0], destCoords[1], headNode.cost + 1);
                if ((curr_y - 1 >= 0) && (!this.visitedNodes.contains("" + up.node_x + "," + up.node_y))) {
                    choices.add(up);
                }
            }

            //down
            if (!this.obstacles.contains(obs_string_format(curr_x, curr_y + 1))) {
                Node down = new Node(curr_x, curr_y + 1, destCoords[0], destCoords[1], headNode.cost + 1);
                if ((curr_y + 1 < this.mapDim) && (!this.visitedNodes.contains("" + down.node_x + "," + down.node_y))) {
                    choices.add(down);
                }
            }

            //right
            if (!this.obstacles.contains(obs_string_format(curr_x + 1, curr_y))) {
                Node right = new Node(curr_x + 1, curr_y, destCoords[0], destCoords[1], headNode.cost + 1);
                if ((curr_x + 1 < this.mapDim) && (!this.visitedNodes.contains("" + right.node_x + "," + right.node_y))) {
                    choices.add(right);
                }
            }

            //left
            if (!this.obstacles.contains(obs_string_format(curr_x - 1, curr_y))) {
                Node left = new Node(curr_x - 1, curr_y, destCoords[0], destCoords[1], headNode.cost + 1);
                if ((curr_x - 1 >= 0) && (!this.visitedNodes.contains("" + left.node_x + "," + left.node_y))) {
                    choices.add(left);
                }
            }

            if(choices.size() > 0) {
                Node bestChoice = choices.get(0);
                Node choice_node = choices.get(0);
                // iterate through the choice nodes
                for (int i = 0; i < choices.size(); i++) {
                    choice_node = choices.get(i);
                    switch (distanceType) {
                        case 0:
                            if (choice_node.manDistance <= bestChoice.manDistance) {
                                bestChoice = choice_node;
                            }
                        case 1:
                            if (choice_node.euclideanDistance <= bestChoice.euclideanDistance) {
                                bestChoice = choice_node;
                            }
                        case 2:
                            if (choice_node.totalCost < bestChoice.totalCost) {
                                bestChoice = choice_node;
                            }
                    }
                }
                // add the choice node and new coordinates to the new path
                this.addVisited(bestChoice);
                this.addNode(bestChoice);
            }
            else{
                this.path.remove(this.path.size()-1);
            }
        }
    }
}
