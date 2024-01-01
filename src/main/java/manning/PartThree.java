package manning;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Map.entry;

public class PartThree {

    public static void main(String[] args) {
        List<Integer> numbers = IntStream.rangeClosed(1, Integer.MAX_VALUE).boxed().collect(Collectors.toList());

        for(Iterator<Integer> i = numbers.iterator(); i.hasNext(); ) {
            Integer val = i.next();
            numbers.remove(val);
            
        }

    }
}
