//Завдання 2
//        Є текстовий файл file.txt. Необхідно прочитати файл, перетворити його в список об'єктів типу User,
//        і записати їх у новий файл user.json.
//
//        Формат файлу:
//
//        перший рядок - заголовок
//        кожний наступний рядок - окремий об'єкт, кожна колонка розділена пробілом

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        task(new File(new Scanner(System.in).nextLine())); //Ввід шляху до файлу з полями об'єктів
    }
    public static void task(File file) {
        try(FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> lines = new ArrayList<>();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            fileReader.close();
            bufferedReader.close();

            ArrayList<User> users = new ArrayList<>();
            for(int i = 1; i < lines.size(); i++) {
                String[] info = lines.get(i).split("\\s+");
                users.add(new User(info[0], Integer.parseInt(info[1])));
            }
            //Запис у файл
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter("users.json")) {
                gson.toJson(users, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}