package br.com.letscode.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeituraArquivos {

    private final List<MenuItem> menuItemList;

    public LeituraArquivos(String filename) {
        this.menuItemList = lerArquivo(filename);
    }

    private List<MenuItem> lerArquivo(String filename) {
        try (Stream<String> fileLines = Files.lines(Paths.get(filename))) {
            return fileLines
                    .skip(1)
                    .map(MenuItem::of)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

}
