import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GloVeReader {
    // Main method that simply reads in the txt file and parses them correctly into the program
    public static void main(String[] args) throws IOException {
        String input;
        Scanner scanner = new Scanner(new FileReader("glove.6B.50d.txt"));
        VectorEmbedding vEmbedding = new VectorEmbedding();
        while (scanner.hasNextLine() ) {
            // Split space between word and float number embedding
            String[] words = scanner.nextLine().split(" ");
            // Array of Floats which will keep as values for words
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }

            vEmbedding.put(words[0],values);
        }
        input = getInput();
        vEmbedding.query(input);
    }

    // Simple query method that asks for the users input for the word2vec
    private static String getInput() {
        System.out.println("Type a word to find words with similar meaning (type exit to finish simulation)");
        Scanner sc = new Scanner(System.in);
        String input = "";
        input = sc.nextLine();
        return input;
    }
}