import java.io.BufferedReader;
import java.util.ArrayList;

public class MapReader {
    private ArrayList<String[]> map;
    private int[] destinationCoords;
    private int[] initCoords;

    public MapReader(String filename){
        ArrayList<String[]> tempMap = null;
        this.destinationCoords[0] = -1;
        this.destinationCoords[1] = -1;
        this.initCoords[0] = -1;
        this.initCoords[1] = -1;

        try{
            BufferedReader reader = new BufferedReader(new java.io.FileReader(filename));
            String line;
            String[] lineArray;
            int dim = Integer.parseInt(reader.readLine());
            while ((line = reader.readLine()) != null)
            {
                lineArray = line.split("");
                tempMap.add(lineArray);
            }
            reader.close();
            this.map = tempMap;
        }
        catch (Exception e){
            System.err.format("Exception occured trying to read '%s'.", filename);
            e.printStackTrace();
        }

        for(int i = 0; i < tempMap.size(); i++) {
            for(int j = 0; j < tempMap.size(); j++) {

                if(tempMap.get(i)[j] == "g") {
                    this.destinationCoords[0] = j;
                    this.destinationCoords[1] = i;
                }
                if(tempMap.get(i)[j] == "i") {
                    this.initCoords[0] = j;
                    this.initCoords[1] = i;
                }
            }
            if((this.destinationCoords[0] != -1) && (this.initCoords[0] != -1)){break;}
        }
    }

    public int[] getDestinationCoords() {
        return destinationCoords;
    }

    public ArrayList<String[]> getMap() {
        return map;
    }

    public int[] getInitCoords() {
        return initCoords;
    }
}
