package com.hspedu.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileInsertRow {

    public static void main(String[] args) throws IOException {
        String filePath = "E:\\company\\byd\\global-region\\src\\main\\java\\com\\hspedu\\io\\a.txt";

        insertStringInFile(filePath, 2, "你好 世界");

    }

    public static void insertStringInFile(String filePath, int lineno, String lineToBeInserted) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

        lines.add(lineno, lineToBeInserted);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
