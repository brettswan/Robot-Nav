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

    public Node(int x, int y, int dest_x, int dest_y, int cost){
        this.cost = cost;
        this.node_x = x;
        this.node_y = y;
        this.dest_x = dest_x;
        this.dest_y = dest_y;
        this.manDistance = abs(dest_x - x) + abs(dest_y - y);
        this.totalCost = this.cost + this.manDistance;
    }
}
