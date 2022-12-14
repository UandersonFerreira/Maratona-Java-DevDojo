package estudo.uanderson.javadevdojo.javacore.ZZEstreams.test;

import estudo.uanderson.javadevdojo.javacore.ZZEstreams.domain.LightNovel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest11 {
    private static List<LightNovel> lightNovels = new ArrayList<>(List.of(
            new LightNovel("Eleceed", 2.55),
            new LightNovel("The Challegen", 3.00),
            new LightNovel("Unordinary", 10.00),
            new LightNovel("Nano Machine", 2.99),
            new LightNovel("Fullmetal Alchemist", 2.55),
            new LightNovel("Martial Peark", 3.98),
            new LightNovel("Martial Peark", 3.98),
            new LightNovel("Martial God Asura", 4.00)
    ));

    public static void main(String[] args) {
        System.out.println(lightNovels.stream().count());//stream
        System.out.println(lightNovels.stream().collect(Collectors.counting()));//collect

        lightNovels.stream()
                .max(Comparator.comparing(LightNovel::getPrice))
                .ifPresent(System.out::println);

        lightNovels.stream()
                .collect(Collectors.maxBy(Comparator.comparing(LightNovel::getPrice)))
                .ifPresent(System.out::println);//collect

        System.out.println(lightNovels.stream().mapToDouble(LightNovel::getPrice).sum());
        System.out.println(lightNovels.stream().collect(Collectors.summingDouble(LightNovel::getPrice)));

        lightNovels.stream()
                .mapToDouble(LightNovel::getPrice)
                .average()
                .ifPresent(System.out::println);

        System.out.println(
                lightNovels.stream()
                        .collect(Collectors.averagingDouble(LightNovel::getPrice))
        );

        DoubleSummaryStatistics collectSummaring = lightNovels.stream().collect(Collectors.summarizingDouble(LightNovel::getPrice));
        //System.out.println("Max: "+ collectSummary.getMax());
       // System.out.println("Count: "+ collectSummary.getCount());
        System.out.println(collectSummaring);

        String titlesSummaring = lightNovels.stream()
                .map(LightNovel::getTitle)
                .collect(Collectors.joining(", "));
        System.out.println(titlesSummaring);

    }//main
}//class
/*
summarizingDouble() retorna o count, sum, min, average e max
 de uma lista dentro de um objeto s??. E ?? poss??vel pegar
 cada elemento separadamente atrav??s da refer??ncia do summarizingDouble();
 ex:
DoubleSummaryStatistics{count=8, sum=33,050000, min=2,550000, average=4,131250, max=10,000000}

E para strings podemos usar o joining() ?? definir um delimitar para separar
as strings, caso n??o use o delimitar ir?? somente concatenar as strings
separando elas pelo espa??o em branco encontrado.
 (poss.uso ao receber varias strings de um banco de dados e querer separar)
 */