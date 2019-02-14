import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GloVeReader {
    public static void main(String[] args) throws IOException {
        String input;
        Scanner scanner = new Scanner(new FileReader("glove.6B.50d.txt"));
        VectorEmbedding vEmbedding = new VectorEmbedding();
        HashMap<String, Float[]> v = new HashMap<>();
        // Just to check how many wordVectors have gone through the system
        int j = 0;
        while (scanner.hasNextLine() ) {
            // Split space between word and float number embedding
            String[] words = scanner.nextLine().split(" ");
            // Array of Floats which will keep as values for words
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }

            // Now all the values are stored in array, store it in the hashMap.
//            HashMap<String, Float[]> x = new HashMap<>();
//            x.put(words[0],values);
            vEmbedding.put(words[0],values);
            j++;
        }
        input = getInput();
        vEmbedding.query(input);
        //vEmbedding.printVectors();
    }

    private static String getInput() {
        System.out.println("Type a word to find words with similar meaning (type exit to finish simulation)");
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();
        return input;
    }
}