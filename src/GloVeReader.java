import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GloVeReader {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("glove.6B.50d.txt"));
        Scanner water = new Scanner(new FileReader("water.txt"));
        Scanner wet = new Scanner(new FileReader("wet.txt"));
        VectorEmbedding vEmbedding = new VectorEmbedding();
        HashMap<String, Float[]> v = new HashMap<>();
        HashMap<String, Float[]> y = new HashMap<>();
        while (water.hasNextLine()) {
            // split space between word and float number embedding
            String[] words = water.nextLine().split(" ");

            // Array of Floats which will keep as values for words
            // Because we are not going to store word as its value
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }
            System.out.println(words[0]);
            System.out.println(Arrays.toString(values));
            // Now all the values are stored in array, store it in the hashMap.

            v.put(words[0],values);
            vEmbedding.add(v);
        }
        while (wet.hasNextLine()) {
            // split space between word and float number embedding
            String[] words = wet.nextLine().split(" ");
            // Array of Floats which will keep as values for words
            // Because we are not going to store word as its value
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }

            // Now all the values are stored in array, store it in the hashMap.

            y.put(words[0],values);
            vEmbedding.add(y);
        }
        float current;
        current = vEmbedding.cosineSimilarity(y.get(y.keySet().toArray()[0]), v.get(v.keySet().toArray()[0]));
        System.out.println(y.keySet().toArray()[0]);























































        // Just to check how many wordVectors have gone through the system
        int j = 0;
        while (scanner.hasNextLine()) {
            // split space between word and float number embedding
            String[] words = scanner.nextLine().split(" ");
            // Array of Floats which will keep as values for words
            // Because we are not going to store word as its value
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }

            // Now all the values are stored in array, store it in the hashMap.
            HashMap<String, Float[]> x = new HashMap<>();
            x.put(words[0],values);
            vEmbedding.add(x);
            j++;
        }
//        vEmbedding.printVectors();
        System.out.println("Type a word to find words with similar meaning (type exit to finish simulation)");
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.next();
        vEmbedding.query(input);

    }
}