package HW_6_Lunev;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter // пример того, что подключен Lombok
public class MontyHallParadox {

    public static String simulateGame(boolean changeChoice) {
        Random rand = new Random();

        // Инициализируем двери: 0 - коза, 1 - машина
        int[] doors = new int[3];
        int carPosition = rand.nextInt(3);  // Позиция машины (случайное число от 0 до 2)
        doors[carPosition] = 1;  // Машина за одной из дверей

        // Игрок делает свой первоначальный выбор (случайно)
        int playerChoice = rand.nextInt(3);

        // Ведущий открывает одну из дверей, за которой точно коза
        int hostChoice = -1;
        for (int i = 0; i < 3; i++) {
            if (i != playerChoice && doors[i] == 0) {
                hostChoice = i;
                break;
            }
        }

        // Если игрок решает сменить выбор
        if (changeChoice) {
            for (int i = 0; i < 3; i++) {
                if (i != playerChoice && i != hostChoice) {
                    playerChoice = i;
                    break;
                }
            }
        }

        // Возвращаем результат: если игрок выбрал машину, то он выиграл
        if (doors[playerChoice] == 1) {
            return "Победа";
        } else {
            return "Поражение";
        }
    }

    public static void main(String[] args) {
        int simulations = 10000;  // Количество симуляций

        // HashMap для хранения результатов каждого теста
        Map<Integer, String> testResultsNoChange = new HashMap<>();
        Map<Integer, String> testResultsWithChange = new HashMap<>();

        // Сначала симулируем случаи, когда игрок не меняет выбор
        int winsNoChange = 0;
        int lossesNoChange = 0;
        for (int i = 0; i < simulations; i++) {
            String result = simulateGame(false);
            testResultsNoChange.put(i + 1, result);
            if (result.equals("Победа")) {
                winsNoChange++;
            } else {
                lossesNoChange++;
            }
        }

        // Теперь симулируем случаи, когда игрок меняет выбор
        int winsWithChange = 0;
        int lossesWithChange = 0;
        for (int i = 0; i < simulations; i++) {
            String result = simulateGame(true);
            testResultsWithChange.put(i + 1, result);
            if (result.equals("Победа")) {
                winsWithChange++;
            } else {
                lossesWithChange++;
            }
        }

        // Выводим статистику по результатам
        System.out.println("Статистика без изменения выбора:");
        System.out.println("Победы: " + winsNoChange);
        System.out.println("Поражения: " + lossesNoChange);

        System.out.println("\nСтатистика с изменением выбора:");
        System.out.println("Победы: " + winsWithChange);
        System.out.println("Поражения: " + lossesWithChange);

        // Выводим примеры шагов теста
        System.out.println("\nПример шагов теста без изменения выбора:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Шаг " + (i + 1) + ": " + testResultsNoChange.get(i + 1));
        }

        System.out.println("\nПример шагов теста с изменением выбора:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Шаг " + (i + 1) + ": " + testResultsWithChange.get(i + 1));
        }
    }
}
