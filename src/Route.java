import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Route {
    Stack<Node> path;

    public Route(Node n) {
        this.path.push(n);
    }

    public void addNode(Node n) {
        this.path.push(n);
    }

    public int getDistanceTraveled() {
        return this.path.size();
    }

}
