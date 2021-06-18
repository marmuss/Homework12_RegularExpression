package com.tms.homework12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocProcess {
    public static void main(String[] args) {
        int counterOfValidDocNumbers = 0;
        Document document;
        Map<String, Document> validDocNumbers = new HashMap<>();

        Pattern docNumberPattern = Pattern.compile("[0-9]{4}[-][A-zЁёА-я]{3}[-][0-9]{4}[-][A-zЁёА-я]{3}[-][0-9][A-zЁёА-я][0-9][A-zЁёА-я]");;
        Pattern phoneNumberPattern = Pattern.compile("[+][(][0-9]{2}[)][0-9]{7}");
        Pattern emailPattern = Pattern.compile("^(([.a-zа-я0-9_-]+\\.)*[a-zа-я0-9_-]+@[a-zа-я0-9-]+(\\.[a-zа-я0-9-]+)*\\.[a-zа-я]{2,6})?$");

        Matcher docNumber;
        Matcher phoneNumber;
        Matcher email;

        System.out.println("Введите путь к каталогу:");
        Scanner pathScanner = new Scanner(System.in);
        String path = pathScanner.nextLine();

        System.out.println("Введите количество файлов для обработки");
        Scanner numScanner = new Scanner(System.in);
        int numberOfFiles = numScanner.nextInt();

        File file = new File(path);
        int iterator = 0;

        if (file.exists()){
            File[] allFiles = file.listFiles();
            if (Arrays.stream(allFiles).count() > 0){
                ArrayList<File> validFiles = new ArrayList<>();
                for (File item : allFiles){
                    if (item.getName().matches("[.0-9A-zЁёА-я_-]+.txt$")){
                        validFiles.add(item);
                        counterOfValidDocNumbers++;
                    }
                }
                if (counterOfValidDocNumbers == 0) {
                    System.out.println("Все файлы имеют неподходящий формат");
                } else {
                    for (File validFile : validFiles) {
                        if (iterator == numberOfFiles) {
                            break;
                        }
                        iterator++;
                        FileReader fileReader = null;
                        ArrayList<String> tmpDocNumbers = new ArrayList<>();
                        String tmpPhoneNumber = "";
                        String tmpEmail = "";
                        int counter = 0;
                        try {
                            fileReader = new FileReader(validFile);
                            Scanner fileScanner = new Scanner(fileReader);
                            String tmpString = "";
                            while (fileScanner.hasNextLine()) {
                                tmpString = fileScanner.nextLine();
                                docNumber = docNumberPattern.matcher(tmpString);
                                phoneNumber = phoneNumberPattern.matcher(tmpString);
                                email = emailPattern.matcher(tmpString);
                                while (docNumber.find()) {
                                    tmpDocNumbers.add(docNumber.group());
                                    counter++;
                                }
                                if (phoneNumber.find()) {
                                    tmpPhoneNumber = phoneNumber.group();
                                }
                                if (email.find()) {
                                    tmpEmail = email.group();
                                }
                            }
                            if (counter > 0) {
                                document = new Document(tmpDocNumbers, tmpEmail, tmpPhoneNumber);
                                validDocNumbers.put(validFile.getName(), document);
                            } else {
                                System.out.println("В файле " + validFile.getName() + " нет номеров документов");
                            }
                            fileScanner.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fileReader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println(validDocNumbers);
                }
            } else {
                System.out.println("Каталог пустой");
            }
        } else {
            System.out.println("Неправильный путь");
        }
        pathScanner.close();
    }
}
