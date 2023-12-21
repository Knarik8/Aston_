package IO;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Tasks {
    public static void main(String[] args) throws IOException {
        String file = "src/IO/text.txt";
        System.out.println(getWordsWithVowels(file));
        System.out.println(getOneWordLastLetterEqualsWithNextWordFirstLetter(file));
        copyReversedText(file, "here.txt");
        replaceWords(file, "newFile.txt", "private", "public");
        System.out.println(greatestConsecutiveDigitsNumber(file));

    }

    //1. Задан файл с текстом, найти и вывести в консоль все слова, начинающиеся с гласной буквы.
    public static List<String> getWordsWithVowels(String fileToRead) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead));
        List<String> list = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Arrays.stream(line.split(" "))
                    .filter(i -> i.substring(0, 1).matches("[AaEeUuIiOoYy]"))
                    .forEach(list::add);
        }
        return list;
    }

    //2. Задан файл с текстом, найти и вывести в консоль все слова,
    //для которых последняя буква одного слова совпадает с первой буквой следующего слова

    public static StringBuilder getOneWordLastLetterEqualsWithNextWordFirstLetter(String fileToRead) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead));
        List<String> list = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            list.addAll(Arrays.asList(line.split(" ")));
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < list.size() - 1; j++) {
            if (list.get(j).charAt(list.get(j).length() - 1) == list.get(j + 1).charAt(0))
                stringBuilder.append(list.get(j)).append(" ");
        }
        return stringBuilder;
    }

    //3. Задан файл с текстом. В каждой строке найти и вывести наибольшее число цифр, идущих подряд.
    public static List<Integer> greatestConsecutiveDigitsNumber(String fileToRead) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead));
        return bufferedReader.lines()
                .map(line -> line.replaceAll("\\D+", " ").trim())
                .map(line -> Arrays.stream(line.split("\\s+"))
                        .mapToInt(String::length)
                        .max().orElse(0))
                .collect(Collectors.toList());
    }


    //4. Задан файл с java-кодом. Прочитать текст программы из файла и все слова public в объявлении атрибутов
    //и методов класса заменить на слово private. Результат сохранить в другой заранее созданный файл.

    public static void replaceWords(String fileToRead, String fileToWrite, String wordToReplace, String replacement) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String lineToWrite = line.replaceAll(wordToReplace, replacement);
                bufferedWriter.write(lineToWrite);
                bufferedWriter.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //5. Задан файл с java-кодом. Прочитать текст программы из файла и записать в другой файл
    //в обратном порядке символы каждой строки.
    public static void copyReversedText(String fileToRead, String fileToWrite) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToRead));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileToWrite));
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                StringBuffer sbr = new StringBuffer(line);
                line = String.valueOf(sbr.reverse());
                bufferedWriter.write(line);
                bufferedWriter.write('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}