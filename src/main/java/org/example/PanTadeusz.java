package org.example;

import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.*;

public class PanTadeusz {

    private final Stream<String> contentStream;

    private static final String path = ("src/pantadeusz.txt");

    public PanTadeusz() {
        try {
            contentStream = Files.lines(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long liczbaWystapienSlowaTadeuszWDowolnejFormie() {
        return contentStream.
                flatMap(breakToWords).
                map(x -> x.trim()).
                filter(x -> x.contains("Tadeu")).
                count();
    }

    public long liczbaUnikalnychWystapienSlowaTadeuszWDowolnejFormie() {
        return contentStream.
                flatMap(breakToWords).
                filter(x -> x.startsWith("Tadeusz")).
                map(x -> x.replaceAll("[.—?:,!;»]", " ")).
                distinct().
                count();
    }


    final Function<String, Stream<String>> breakToWords = line -> Arrays.stream(line.trim().split(" "));


    public String drugeNajdluzszeSlowoWCalymUtworze() {
        return contentStream.
                flatMap(breakToWords).
                map(x -> x.replaceAll("[.—?:,!;»]", " ")).
                distinct().
                sorted(Comparator.comparing(String::length).reversed()).
                skip(1).
                findFirst().get();
    }

    public Map<String, BigDecimal> procentWystapienSlowaTadeuszWPoszczegolnychFormachDo2mpp() {
        Map<String, Long> formsCounted = contentStream.
                flatMap(breakToWords).
                map(x -> x.replaceAll("[.—?:,!;»]", " ").trim()).
                filter(x -> x.startsWith("Tadeusz")).
                collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting()));

        Long formsTotal = contentStream.
                flatMap(breakToWords).
                filter(x -> x.startsWith("Tadeusz")).
                map(x -> x.replaceAll("[.—?:,!;»]", " ")).
                count();

        Map<String, BigDecimal> result = new HashMap<>();
        for (Map.Entry<String, Long> entry : formsCounted.entrySet()) {
            result.put(entry.getKey(), new BigDecimal(entry.getValue().doubleValue() * 100 / formsTotal).setScale(2, RoundingMode.HALF_DOWN));
        }

        MapUtils.debugPrint(System.out, "result", result);

        return result;
    }

    public int sredniaLiczbaZnakowAlfabetycznychPrzypadajacychNaNiepustyWersWKsiedzeIV() {
        var res = contentStream.
                dropWhile(line -> !line.contains("Księga czwarta")).
                takeWhile(line -> !line.contains("Księga piąta")).
                filter(line -> line.trim().length() > 0).
                map(line -> line.replaceAll("[.—?:,!;»\\s+]", "").trim()).
                mapToDouble(line -> line.length()).
                average().
                getAsDouble();

        return (int) Math.floor(res);

    }

    public Map<String, Long> tytulOrazLiczbaWersowKazdejKsiegiPrzyZachowaniuKolejnosci() throws IOException {
        Pattern pattern = Pattern.compile("(?=\\nKsięga\\s[a-zA-Ząó]+\\n)");
        String file = Files.readString(Paths.get(path));
        List<String> chapters = List.of(pattern.split(file));

        Map<String, Long> res = chapters.stream()
                .skip(1)
                .map(c -> c.substring(1, c.length() - 2).replaceAll("\n+", "\n"))
                .collect(toMap(
                        c -> getKsiegaName(c) ,
                        c -> getLengthWithoutTitles(c))
                );

        return res;
    }

    Map<String, Long> tytulOrazLiczbaWersowKazdejKsiegiPrzyZachowaniuKolejnosci2() throws IOException {
        long start = System.currentTimeMillis();
        record Wers(String ksiega, String wers) {}
        class Ksiega { String tytul = ""; }
        Ksiega ksiega = new Ksiega();
        LinkedHashMap<String, Long> res = Files.lines(Path.of(path))
                .dropWhile(l -> !l.trim().matches("^Księga [^u].*"))
                .filter(not(String::isBlank))
                .peek(l -> {
                    if (l.matches("^Księga [^u].*")) {
                        ksiega.tytul = l;
                    }
                })
                .map(l -> new Wers(ksiega.tytul, l))
                .collect(groupingBy(Wers::ksiega, LinkedHashMap::new, collectingAndThen(counting(), c -> c - 2)));
        System.out.println("Execution time : [" + (System.currentTimeMillis() - start) + "ms]");
        return res;
    }

    private String getKsiegaName(String c) {
        StringBuilder sb = new StringBuilder(c);
        return sb.substring(0, c.indexOf("\n")).trim();
    }

    private Long getLengthWithoutTitles(String c) {
        return Long.valueOf(c.replaceAll("\n+", "\n").split("\n").length - 2);
    }
}
