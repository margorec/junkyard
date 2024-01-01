package manning;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Math.sqrt;

public class PitagoreanTriplets {

    public void generate(int rangeEnd) {
        IntStream.rangeClosed(1, rangeEnd)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(1, rangeEnd)
                        .filter(b -> sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) sqrt(a * a + b * b)})
                )
                .collect(Collectors.toList());
    }

    public void generateOptimized(int rangeEnd) {
        IntStream.rangeClosed(1, rangeEnd)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(1, rangeEnd)
                        .mapToObj(b -> new int[]{a*a, b*b, a*a+b*b}))
                .filter(triplet -> sqrt(triplet[2])%1 ==0)
                .collect(Collectors.toList());
    }

}
