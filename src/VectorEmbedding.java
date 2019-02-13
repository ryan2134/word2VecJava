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
    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }
}
