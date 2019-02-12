import java.util.ArrayList;
import java.util.HashMap;

public class VectorEmbedding {
    private ArrayList vectors;

    public VectorEmbedding(){
        this.vectors = new ArrayList();
    }

    public void add(Vector v){
        this.vectors.add(v);
    }

    public HashMap<String, Float[]> query(String word){

    }

    public HashMap<String, Float[]> addVectors(String v1, String v2){

    }

    public HashMap<String, Float[]> cosineSimilarity(String s1, String s2){

    }
}
