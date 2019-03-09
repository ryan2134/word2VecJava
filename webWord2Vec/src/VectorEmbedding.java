import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class VectorEmbedding extends HashMap<String, Float[]>{

    // Sums two vectors of the same length together
    public Float[] sumVectors(Float[] v1, Float[] v2){
        int length = v1.length;
        Float[] values = new Float[length];
        for(int i = 0; i < length; i++){
            values[i] = v1[i] + v2[i];
        }
        return values;
    }

    // Subtracts one vectors values from another
    public Float[] subtractVectors(Float[] v1, Float[] v2){
        int length = v1.length;
        Float[] values = new Float[length];
        for(int i = 0; i < length; i++){
            values[i] = v1[i] - v2[i];
        }
        return values;
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
    
    
    // Given a String input, it will be checked within the word embedded vectors to see if it exists and checks what
    // type of query the user is executing
    public HashMap<String, Float> computeQuery(String input){
    	HashMap<String, Float> map = new HashMap<>();
        String[] words = input.split(" ");
        HashMap<String, Float[]> query = new HashMap<>();
        if(words.length == 1){
            if(this.containsKey(input)){
                System.out.println("Found word");
                query.put(input, this.get(input));
            }
        }
        // Has to check if the user has entered the input in correct format and if the words exist in the embedding
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
                return null;
            }
            query.put(newWord, newValues);
        }
        else{
            System.out.println("Too many words have been entered.");
            return null;
        }
        map = gatherCosSimularity(query, this);
        return map;
    }
    
    // Retrieves the top 10 similar words given the query via the cosine similarity, we go thru the embedding and
    // then sort the whole ArrayList values and reverse to obtain the top 10
    public HashMap<String, Float> gatherCosSimularity(HashMap<String, Float[]> query, VectorEmbedding v){
    	HashMap<String, Float> map = new HashMap<>();
    	HashMap<Float, String> table = new HashMap<>();
        ArrayList<Float> nums = new ArrayList<>();
        float current;
        for (String word : v.keySet()) {
            // If we come across the queried word in the embedding, ignore it or it will return as the top similarity
            if(((String) (query.keySet().toArray()[0])).contains(word)){
                continue;
            }
            current = cosineSimilarity(query.get(query.keySet().toArray()[0]), v.get(word));
            nums.add(current);
            table.put(current, word);
        }

        Collections.sort(nums);
        Collections.reverse(nums);
        for(int i = 0; i < 10; i++){
            map.put(table.get(nums.get(i)), nums.get(i));
        }
        HashMap<String, Float> sortedMapDesc = sortByValue(map, false);
        return sortedMapDesc;
    }
    
    // When given an unsorted Hashmap, applies numerous operations to sort based on values
    private static HashMap<String, Float> sortByValue(HashMap<String, Float> unsortMap, boolean order){
        List<Entry<String, Float>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> order ? o1.getValue().compareTo(o2.getValue()) == 0
                ? o1.getKey().compareTo(o2.getKey())
                : o1.getValue().compareTo(o2.getValue()) : o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }
}