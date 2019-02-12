import java.util.ArrayList;
import java.util.HashMap;

public class VectorEmbedding {
    private ArrayList vectorList;

    public VectorEmbedding(){
        this.vectorList = new ArrayList();
    }

    public void add(Vector v){
        this.vectorList.add(v);
    }

    public HashMap<String, Float[]> query(String word){
        

        // Print stuff here:

    }

    public HashMap<String, Float[]> addVectors(String v1, String v2){

    }

    public HashMap<String, Float[]> cosineSimilarity(String s1, String s2){

    }
}
