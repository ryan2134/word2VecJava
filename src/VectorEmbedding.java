import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class VectorEmbedding extends HashMap<String, Float[]>{

    public Float[] sumVectors(Float[] v1, Float[] v2){
        int length = v1.length;
        Float[] values = new Float[length];
        for(int i = 0; i < length; i++){
            values[i] = v1[i] + v2[i];
        }
        System.out.println(Arrays.toString(values));
        return values;
    }

    public Float[] subtractVectors(Float[] v1, Float[] v2){
        int length = v1.length;
        Float[] values = new Float[length];
        for(int i = 0; i < length; i++){
            values[i] = v1[i] - v2[i];
        }
        System.out.println(Arrays.toString(values));
        return values;
    }

    public void query(String input){
        String[] words = input.split(" ");
        System.out.println(Arrays.toString(words));
        HashMap<String, Float[]> query = new HashMap<>();
        if(words.length == 1){
            if(this.containsKey(input)){
                System.out.println("Found word");
                query.put(input, this.get(input));
            }
        } 
        else if (words.length == 3 && this.containsKey(words[1]) && this.containsKey(words[2])){
            String newWord = words[1] + words[2];
            Float[] newValues;
            if (words[0].equals("add")){
                newValues = sumVectors(this.get(words[1]), this.get(words[2]));
            }
            else if (words[0].equals("subtract")){
                newValues = subtractVectors(this.get(words[1]), this.get(words[2]));
            }
            else{
                System.out.println("Wrong keyword entered");
                return;
            }
            query.put(newWord, newValues);
        }
        else{
            System.out.println("Too many words have been entered.");
            return;
        }
        gatherCosSim(query, this);
    }

    public void gatherCosSim(HashMap<String, Float[]> query, VectorEmbedding v){
        ArrayList<Float> nums = new ArrayList<>();
//        int length = query.get(query.keySet().toArray()[0]).length;
//        System.out.println(length);
//        HashMap<String, Float[]> currentVector = new HashMap<>();
        float current;
        HashMap<Float, String> table = new HashMap<>();
        for (String word : v.keySet()) {
            //System.out.println(hashMap.keySet());

            /* CHECK */
            if(word.contains((String) (query.keySet().toArray()[0]))){
                System.out.println("hello");
                continue;
            }
            //System.out.println(query.keySet().toArray()[0]);
            current = cosineSimilarity(query.get(query.keySet().toArray()[0]), v.get(word));
            //System.out.println(current);
            nums.add(current);
            table.put(current, word);
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

    // Assuming that the vectors will of the same length as each other, finds the cosine similarity of
    // two vectors
    public Float cosineSimilarity(Float[] vectorA, Float[] vectorB) {
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
