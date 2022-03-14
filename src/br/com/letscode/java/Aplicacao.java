package br.com.letscode.java;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Aplicacao {

    private static LeituraArquivos leituraFemale;
    private static LeituraArquivos leituraMale;

    public static void main(String[] args) {

        leituraFemale = new LeituraArquivos("oscar-age-female.csv");
        leituraMale = new LeituraArquivos("oscar-age-male.csv");

        // 1 - Quem foi o ator mais jovem a ganhar um Oscar?
        atorMaisJovem();

        // 2 - Quem foi a atriz que mais vezes foi premiada?
        atrizMaisPremiada();

        // 3 - Qual atriz entre 20 e 30 anos que mais vezes foi vencedora?
        atrizJovemQueMaisVenceu();

        // 4 - Quais atores ou atrizes receberam mais de um Oscar?
        atoresAtrizesMaisDeUmOscar();

        // 5 - Quando informado o nome de um ator ou atriz, dê um resumo de quantos prêmios ele/ela recebeu e liste ano, idade e nome de cada filme pelo qual foi premiado(a).
        infoAtorAtriz("Spencer Tracy");
        infoAtorAtriz("Elizabeth Taylor");
    }

    private static void atorMaisJovem() {
        List<MenuItem> menuItemList = leituraMale.getMenuItemList();
        Optional<MenuItem> itemOptional = menuItemList.stream()
                .min(Comparator.comparing(MenuItem::getAge));
        itemOptional.ifPresent(it -> System.out.println("O ator mais jovem a ganhar um oscar foi o " + it.getName() + " com " + it.getAge() + " anos."));
    }

    private static void atrizMaisPremiada() {
        List<MenuItem> menuItemList = leituraFemale.getMenuItemList();
        Map<String, Long> map = menuItemList.stream()
                .map(MenuItem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(c -> System.out.println(c.getKey() + " premiada " + c.getValue() + " vezes."));
    }

    private static void atrizJovemQueMaisVenceu() {
        List<MenuItem> menuItemList = leituraFemale.getMenuItemList();
        Map<String, Long> map = menuItemList.stream()
                .filter(menuItem -> (menuItem.getAge() >= 20 && menuItem.getAge() <= 30))
                .map(MenuItem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(c -> System.out.println(c.getKey() + " foi a maior vencedora entre 20 e 30 anos com " + c.getValue() + " premios."));
    }

    private static void atoresAtrizesMaisDeUmOscar() {
        System.out.println("Atores e atrizes que receberam mais de um Oscar");
        List<MenuItem> atrizes = leituraFemale.getMenuItemList();
        List<MenuItem> atores = leituraMale.getMenuItemList();
        Map<String, Long> map = atrizes.stream()
                .map(MenuItem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream()
                .filter(c -> (c.getValue() > 1))
                .forEach(System.out::println);
        map = atores.stream()
                .map(MenuItem::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream()
                .filter(c -> (c.getValue() > 1))
                .forEach(System.out::println);

    }


    private static void infoAtorAtriz(String name) {
        List<MenuItem> menuItemList = leituraFemale.getMenuItemList();
        menuItemList.stream()
                .filter(menuItem -> (menuItem.getName().equals(name)))
                .forEach(it -> System.out.println(it.getName() + " venceu o prêmio em " + it.getYear() + " com " + it.getAge() + " anos, com o filme " + it.getMovie()));
        menuItemList = leituraMale.getMenuItemList();
        menuItemList.stream()
                .filter(menuItem -> (menuItem.getName().equals(name)))
                .forEach(it -> System.out.println(it.getName() + " venceu o prêmio em " + it.getYear() + " com " + it.getAge() + " anos, com o filme " + it.getMovie()));
    }

}
