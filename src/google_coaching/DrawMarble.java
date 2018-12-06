package google_coaching;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DrawMarble {

    Map<Character, Integer> map = new HashMap<>();  // todo: if using integer to encode colors, then can use an array; or use two arrays: number of each color, and color names
    int total_num;
    Random random;

    // randomly draw a marble
    public char drawAMarble() throws Exception {
        // get a random integer between 0 and total_num - 1
        int rand = random.nextInt(total_num);   // todo: can use a better variable name, e.g., "selectedIndex"

        // find the marble pointed to by this random index
        int temp = 0;
        for (char color : map.keySet()) {
            int count = map.get(color);
            if (temp + count < rand) { // this color is picked !
                if (count == 1)
                    map.remove(color);
                else
                    map.put(color, count - 1);
                total_num--;
                return color;
            }
            temp += count;
        }
        throw new IllegalStateException(); // should never reach here
    }

    public int getRandomMarble(int[] marbles) throws Exception {
        if (marbles.length == 0) throw new InvalidArgumentException(new String[]{"empty marble array"});
        int selectedIndex = new Random().nextInt(marbles.length);

        // ....
        
        return 0;
    }
}
