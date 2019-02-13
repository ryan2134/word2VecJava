import java.util.Arrays;
import java.util.HashMap;

public class Vector {
    private Float[] nums;
    private HashMap<String, Float[]> table = new HashMap<>();

    public Vector(String word, Float values[]){
        nums = values;
        table.put(word, nums);
        System.out.println(this.table.keySet());
        System.out.println(Arrays.toString(values));
    }


    public String getKey(){
        return this.table.keySet().toString();
    }

    public void printVector(){
        System.out.println(getKey());
        System.out.println(Arrays.toString(this.nums));
    }

}
