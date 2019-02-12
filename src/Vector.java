import java.util.Arrays;
import java.util.HashMap;

public class Vector {
    private HashMap<String, Float[]> table = new HashMap<>();

    public Vector(String word, Float values[]){
        table.put(word, values);
        System.out.println(this.table.keySet());
        System.out.println(Arrays.toString(values));
    }


}
