package org.example;
/*
Домашнее задание
Необходимо реализовать код для
демонстрации парадокса Монти Холла (Парадокс Монти Холла
— Википедия) и наглядно убедиться в верности парадокса
(запустить игру в цикле на 1000 и вывести итоговый счет).
Необходимо:
● Создать свой Java Maven или Gradle проект;
● Самостоятельно реализовать прикладную задачу;
● Сохранить результат в HashMap<шаг теста, результат>
● Вывести на экран статистику по победам и поражениям
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    static Random random;
    static Map<Integer, Boolean> result1;   // статистика для игрока, не меняющего свой выбор
    static Map<Integer, Boolean> result2;   // статистика для игрока, изменяющего свой выбор
    static  int doorsNumber;                // кол-во дверей
    static int attempts;                    // кол-во попыток

    public static void main(String[] args) {
        random = new Random();
        result1 = new HashMap<>();
        result2 = new HashMap<>();
        doorsNumber = 3;
        attempts = 1000;

        for (int i = 0; i < attempts; i++) {  // Розыгрыш (1000 попыток)
            trial(i);
        }
        int win = 0;   // Статистика для первого игрока
        for (Map.Entry<Integer, Boolean> entry: result1.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.println("\nПарадокс Монти Холла");
        System.out.println("\nСтатистика выигрышей для игрока, не меняющего свой выбор: ");
        System.out.println("Количество побед: " + win + " раз из " + attempts + " попыток.");

        win = 0;      // Статистика для второго игрока
        for (Map.Entry<Integer, Boolean> entry: result2.entrySet()){
            if (entry.getValue()){
                win++;
            }
        }
        System.out.println("\nСтатистика выигрышей для игрока, изменяющего свой выбор: ");
        System.out.println("Количество побед: " + win + " раз из " + attempts + " попыток.");
    }
    private static void trial(int numRound){
        int success = random.nextInt(doorsNumber);
        int firstChoice = random.nextInt(doorsNumber);
        int freeOpenDoor = -1;
        int secondChoice = -1;

        for (int i = 0; i < doorsNumber; i++) {
            if (i != success && i != firstChoice){
                freeOpenDoor = i;
            }
        }

        for (int i = 0; i < doorsNumber; i++) {             // Игрок не изменяет свой выбор
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = firstChoice;
            }
        }
        result1.put(numRound, success == secondChoice);     // Статистика для первого игрока

        for (int i = 0; i < doorsNumber; i++) {             // Игрок не изменяет свой выбор
            if (i != freeOpenDoor && i != firstChoice){
                secondChoice = i;
            }
        }
        result2.put(numRound, success == secondChoice);     // Статистика для второго игрока
    }
}