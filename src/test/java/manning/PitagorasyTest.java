package manning;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PitagorasyTest {

    @Test
    public void triplets() {
        PitagoreanTriplets subject = new PitagoreanTriplets();

        var range = 10;

        long start = System.currentTimeMillis();
        subject.generate(range);
        System.out.println("Execution time:" + (System.currentTimeMillis() - start)/1000 + "s");


        start = System.currentTimeMillis();
        subject.generateOptimized(range);
        System.out.println("Execution time:" + (System.currentTimeMillis() - start)/1000 + "s");

        var res = Stream.of("Ząb", null, "Zębowa")
                .flatMap(x -> Stream.ofNullable(x))
                .map(x -> x.length()).collect(Collectors.toList());

        System.out.println(res);


    }

}