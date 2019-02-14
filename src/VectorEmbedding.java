import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        HashMap<String, Float[]> query = new HashMap<>();
        for (HashMap<String, Float[]> hashMap : vectorList) {
            //System.out.println(hashMap.keySet().toArray()[0]);
            if(hashMap.containsKey(word)){
//                System.out.println("Found word");
                query = hashMap;
                //System.out.println(hashMap.get(word));
                break;
            }
        }
        gatherCosSim(query);
        // Print stuff here:


    }
    public void gatherCosSim(HashMap<String, Float[]> query){
        ArrayList<Float> nums = new ArrayList<>();
        float current;
        HashMap<Float, String> table = new HashMap<>();
        for (HashMap<String, Float[]> hashMap : vectorList) {
            //System.out.println(hashMap.keySet());
            if(hashMap.containsKey(query.keySet().toArray()[0])){
                continue;
            }
            //System.out.println(query.keySet().toArray()[0]);
            current = cosineSimilarity(query.get(query.keySet().toArray()[0]), hashMap.get(hashMap.keySet().toArray()[0]));

            //System.out.println(current);
            nums.add(current);
            table.put(current, (String) hashMap.keySet().toArray()[0]);
            //System.out.println("Word: " + table.get(current));
        }
        Float test[] = query.get(query.keySet().toArray()[0]);
        for(Float f : test){
            System.out.println(f.toString());
        }
        //System.out.println("What's going on" + query.get(query.keySet().toArray()[0]));
        Collections.sort(nums);
        Collections.reverse(nums);
        //System.out.println(nums);
        for(int i = 0; i < 10; i++){
            System.out.print("Word: " + table.get(nums.get(i)));
            System.out.print("  CS Value: " + nums.get(i) + "\n");
        }
    }

    // Assuming that the vectors will of the same length as each other
    public static Float cosineSimilarity(Float[] vectorA, Float[] vectorB) {
        //System.out.println(Arrays.toString(vectorA));

        float dotProduct = 0;
        float normA = 0;
        float normB = 0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += (float) Math.pow(vectorA[i], 2);
            normB += (float) Math.pow(vectorB[i], 2);
        }
        return  dotProduct / (float) (Math.sqrt(normA) *  (float) Math.sqrt(normB));
    }
}
