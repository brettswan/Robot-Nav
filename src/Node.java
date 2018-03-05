import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Node {
    public int node_x;
    public int node_y;
    public int manDistance;
    private int dest_x;
    private int dest_y;
    public int cost;
    public int totalCost;
    public boolean isObstacle;

    public Node(int x, int y, int dest_x, int dest_y, int cost, PriorityQueue<String> obstacles){
        this.cost = cost;
        this.node_x = x;
        this.node_y = y;
        this.dest_x = dest_x;
        this.dest_y = dest_y;
        this.manDistance = abs(dest_x - x) + abs(dest_y - y);
        this.totalCost = this.cost + this.manDistance;

        if (obstacles.contains(obs_string_format(node_x,node_y))){
            this.isObstacle = true;
        }

    }

    public String obs_string_format(int nodex, int nodey){
        String output = Integer.toString(nodex)+","+Integer.toString(nodey);
        return output;
    }

    // outputs which path node should take
    //public Node decisionFunction(int u, int d, int r, int l){}

    // dont need
    // (int u, int d, int r, int l)
    public static int minIndex(ArrayList<Integer> list) {
        return list.indexOf (Collections.min(list));
    }

}
