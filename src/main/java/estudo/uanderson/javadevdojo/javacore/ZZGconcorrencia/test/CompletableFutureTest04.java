package estudo.uanderson.javadevdojo.javacore.ZZGconcorrencia.test;

import estudo.uanderson.javadevdojo.javacore.ZZGconcorrencia.domain.Quote;
import estudo.uanderson.javadevdojo.javacore.ZZGconcorrencia.service.StoreServiceWithDiscount;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureTest04 {
    public static void main(String[] args) {
        StoreServiceWithDiscount service = new StoreServiceWithDiscount();
        //searchPricesWithDiscount(service);
        searchPricesWithDiscountAsync(service);

    }//main

    private static void searchPricesWithDiscount(StoreServiceWithDiscount service){
        long start =  System.currentTimeMillis();

        List<String> stores = List.of("Store 1","Store 2","Store 3","Store 4");
        //stores.forEach(s -> System.out.println(service.getPriceSync(s)));
        stores.stream()
                .map(service::getPriceSync)// neste ponto retorna storeName:price:discountCode
                .map(Quote::newQuote)
                .map(service::applyDiscount)
                .forEach(System.out::println);

        long end = System.currentTimeMillis();
        System.out.printf("Time passed to searchPricesWithDiscount: %dms%n", (end-start));
    }//method searchPricesWithDiscount

    private static void searchPricesWithDiscountAsync(StoreServiceWithDiscount service){
        long start =  System.currentTimeMillis();

        List<String> stores = List.of("Store 1","Store 2","Store 3","Store 4");

        List<CompletableFuture<String>> completableFutureList = stores.stream()
                //Getting the price async storeName:prices:discountCode
                .map(s -> CompletableFuture.supplyAsync(() -> service.getPriceSync(s)))
                //Instantiating a new quote from the string generated by getPricesSyns
                .map(cf -> cf.thenApply(Quote::newQuote))
                //está fzd com que seja aplicado um desconto para o primeiro preço gerado de forma sicronona
                .map(cf -> cf.thenCompose(quote -> CompletableFuture.supplyAsync(() -> service.applyDiscount(quote))))
                .collect(Collectors.toList());

        completableFutureList.stream()
                .map(CompletableFuture::join)
                .forEach(System.out::println);

        long end = System.currentTimeMillis();
        System.out.printf("Time passed to searchPricesWithDiscount: %dms%n", (end-start));
    }//method searchPricesWithDiscount

}//class