import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class Node {
    public int node_x;
    public int node_y;
    public double distanceFromDest;
    private int dest_x;
    private int dest_y;
    public int distanceTraveled;

    public Node(int x, int y, int dest_x, int dest_y, int distanceTraveled ){
        this.distanceTraveled = distanceTraveled;
        this.node_x = x;
        this.node_y = y;
        this.dest_x = dest_x;
        this.dest_y = dest_y;
        this.distanceFromDest = abs(dest_x - x) + abs(dest_y - y);
    }
}
