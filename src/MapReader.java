import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class MapReader {
    private ArrayList<ArrayList<String>> map;
    private int[] destinationCoords;
    private int[] initCoords;
    private PriorityQueue<String> obstacles;
    private ArrayList<ArrayList<String>> tempMap;

    public MapReader(String filename){
        this.tempMap = new ArrayList<>();
        this.obstacles = new PriorityQueue<>();
        this.destinationCoords = new int[2];
        this.initCoords = new int[2];

        try{
            BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
            String line;
            String[] lineArray;
            int dim = Integer.parseInt(reader.readLine());
            while ((line = reader.readLine()) != null)
            {
                lineArray = line.split("");
                ArrayList<String> tempArray = new ArrayList<>();
                for(String c : lineArray) {
                    tempArray.add(c);
                }
                tempMap.add(tempArray);
            }
            reader.close();
            this.map = tempMap;

            for(ArrayList<String> row : tempMap) {
                System.out.println(row);
            }
        }
        catch (Exception e){
            System.err.format("Exception occured trying to read '%s'.", filename);
            e.printStackTrace();
        }

        for(int i = 0; i < this.tempMap.size(); i++) {
            for(int j = 0; j < this.tempMap.size(); j++) {

                if(this.tempMap.get(i).get(j).equals("g")) {
                    this.destinationCoords[0] = j;
                    this.destinationCoords[1] = i;
//                    System.out.println(""+this.destinationCoords);
                }
                if(this.tempMap.get(i).get(j).equals("i")) {
                    this.initCoords[0] = j;
                    this.initCoords[1] = i;
                }
                if(this.tempMap.get(i).get(j).equals("+")) {
                    this.obstacles.add(Integer.toString(j)+","+Integer.toString(i));
                }
            }

        }
    }

    public int[] getDestinationCoords() {
        return destinationCoords;
    }

    public ArrayList<ArrayList<String>> getMap() {
        return map;
    }

    public int[] getInitCoords() {
        return initCoords;
    }

    public PriorityQueue<String> getObstacles() {
        return obstacles;
    }
}
