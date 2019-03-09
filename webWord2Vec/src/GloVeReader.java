import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GloVeReader {
	public static HashMap<String, Float> queryInput(String query) throws IOException{
		HashMap<String, Float> queryMap = new HashMap<>();
		FileReader fr = new FileReader("glove.6B.50d.txt");
		BufferedReader br = new BufferedReader(fr);
		VectorEmbedding vEmbedding = new VectorEmbedding();
		String read = null;
		while ((read = br.readLine()) != null)  {
            // Split space between word and float number embedding
            String[] words = read.split(" ");
            // Array of Floats which will keep as values for words
            Float[] values = new Float[words.length - 1];
            for( int i = 1; i < words.length; i++){
                values[i - 1] = Float.parseFloat(words[i]);
            }
            vEmbedding.put(words[0],values);
        }
        return queryMap = vEmbedding.computeQuery(query);
	}
}