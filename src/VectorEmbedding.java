import java.util.ArrayList;
import java.util.HashMap;

public class VectorEmbedding {
    private ArrayList<HashMap<String, Float[]>> vectorList;

    public VectorEmbedding(){
        this.vectorList = new ArrayList<>();
    }

    public void add(HashMap<String, Float[]> v){
        this.vectorList.add(v);
    }

    public void query(String word){
//        Vector queriedVector = null;
//        for(HashMap<String, Float[]> v : vectorList){
//            System.out.println(v.getKey());
//            if(v.table.get(word).equals(word)){
//
//                v.printVector();
//            }
//        }
        for (HashMap<String, Float[]> hashMap : vectorList) {
            System.out.println(hashMap.keySet());
            if(hashMap.containsKey(word)){
                System.out.println("hello");
            }
        }
        // Print stuff here:

    }
//
//        public HashMap<String, Float[]> addVectors(String v1, String v2){
//
//        }
//
//        public HashMap<String, Float[]> cosineSimilarity(String s1, String s2){
//
//    }

//    public void printVectors() {
//        for(Vector v : vectorList){
//            v.printVector();
//        }
//    }
}
