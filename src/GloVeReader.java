import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GloVeReader {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new FileReader("glove.6B.50d.txt"));

        VectorEmbedding vEmbedding = new VectorEmbedding();
        // Just to check how many wordVectors have gone through the system
        int j = 0;
        while (scanner.hasNextLine() && j < 31) {
            // split space between word and float number embedding
            String[] words = scanner.nextLine().split(" ");
            // Array of Floats which will keep as values for words
            // Because we are not going to store word as its value
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }

            // Now all the values are stored in array, store it in the hashMap.
            HashMap<String, Float[]> v = new HashMap<>();
            v.put(words[0],values);
            vEmbedding.add(v);
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