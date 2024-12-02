import java.util.Scanner;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    private boolean checkType(String x, String typeName) {
        if (typeName == "double") {
            try {
                double value = Double.parseDouble(x);
                return true;
            } catch (NumberFormatException e) {
                out.println("Ошибка: введено не число типа double");
            }
        }
        if (typeName == "int") {
            try {
                double value = Integer.parseInt(x);
                return true;
            } catch (NumberFormatException e) {
                out.println("Ошибка: введено не число типа int");
            }
        }
        return false;
    }

    private static double readCoordinate(Scanner scanner, String coordinateName) {
        while (true) {
            out.print("Введите координату " + coordinateName + ": ");
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                if (value == -0.0){
                    value = 0.0;
                }
                return value;
            } else {
                out.println("Некорректный ввод");
                scanner.nextLine();
            }
        }
    }

    public static <T> void boxMethod(Box<T> box) {
        T res = box.getItem();
        out.println("В коробке лежит " + res);
    }

    public static <T> void storageMethod(Storage<T> st, T alt) {
        T res = st.getItemAlt(alt);
        out.println("В хранилище лежит: " + res);
    }

    public static void dotBox(Box<? super Dot2> box, Double x, Double y,  Double z) {
        Dot2 dot = new Dot2(x, y, z);
        box.setItem(dot);
    }

    public static <T, P> List<P> applyToList(List<T> inp, Function<T, P> func) {
        List<P> res = new ArrayList<>();
        for (T elem : inp) {
            try {
                res.add(func.apply(elem));
            } catch (Exception e) {
                System.err.println("Ошибка при обработке");
            }
        }
        return res;
    }

    public static <T> List<T> filterList(List<T> inp, Predicate<T> filter) {
        List<T> res = new ArrayList<>();
        for (T elem : inp) {
            try {
                if (filter.test(elem)) {
                    res.add(elem);
                }
            } catch (Exception e) {
                System.err.println("Ошибка при обработке");
            }
        }
        return res;
    }

    public static <T> T sumList(List<T> inp, BinaryOperator<T> summary, T initial) {
        T res = initial;
        for (T elem : inp) {
            try {
                res = summary.apply(res, elem);
            } catch (Exception e) {
                System.err.println("Ошибка при обработке");
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Main m = new Main();

        out.print("Введите номер задания и номер задачи через пробел: ");
        Scanner scanner = new Scanner(System.in);
        String task = scanner.nextLine();
        String[] parts = task.split(" ");
        if (!m.checkType(parts[0], "int") || !m.checkType(parts[1], "int")) {
            out.println("Неправильно введён номер задачи");
            return;
        }
        int num1 = Integer.parseInt(parts[0]);
        int num2 = Integer.parseInt(parts[1]);
        if (num1 == 1 && num2 == 1) {
            Box<Integer> intBox = new Box<>();
            intBox.setItem(3);
            boxMethod(intBox);
        }
        else if (num1 == 1 && num2 == 2){
            Storage<Integer> numberStorageNull = new Storage<>(null);
            storageMethod(numberStorageNull, 0);

            Storage<Integer> numberStorage = new Storage<>(99);
            storageMethod(numberStorage, -1);

            Storage<String> stringStorageNull = new Storage<>(null);
            storageMethod(stringStorageNull, "default");

            Storage<String> stringStorage = new Storage<>("hello");
            storageMethod(stringStorage, "hello world");
        }
        else if (num1 == 2 && num2 == 3) {
            double x = readCoordinate(scanner, "x");
            double y = readCoordinate(scanner, "y");
            double z = readCoordinate(scanner, "z");
            Box<Dot2> cordBox = new Box<>();
            dotBox(cordBox, x, y, z);
            out.println(cordBox);
        }
        else if (num1 == 3 && num2 == 1) {
            List<String> strings = List.of("qwerty", "asdfg", "zx");
            List<Integer> lengths = applyToList(strings, String::length);
            out.println("Длина строк: " + lengths);

            List<Integer> nums = List.of(1, -3, 7);
            List<Integer> absoluteValues = applyToList(nums, Math::abs);
            out.println("Модуль чисел: " + absoluteValues);

            List<int[]> arrays = List.of(new int[]{1, 3, 5}, new int[]{-1, -5, -3}, new int[]{10, 20, 30});
            List<Integer> maxValues = applyToList(arrays, array -> {
                int max = array[0];
                for (int value : array) {
                    if (value > max) {
                        max = value;
                    }
                }
                return max;
            });
            out.println("Максимумы: " + maxValues);
        }
        else if (num1 == 3 && num2 == 2) {
            List<String> strings = List.of("qwerty", "asdfg", "zx");
            List<String> resultStr = filterList(strings, str -> str.length() >= 3);
            out.println("Строки длиной больше 3: " + resultStr);

            List<Integer> numbers = List.of(1, -3, 7);
            List<Integer> negative = filterList(numbers, num -> num < 0);
            out.println("Отрицательные числа: " + negative);

            List<int[]> arrays = List.of(new int[]{1, 2, 3}, new int[]{-1, -2, -3}, new int[]{1, -2, 3});
            List<int[]> filteredArrays = filterList(arrays, array -> {
                for (int value : array) {
                    if (value > 0) {
                        return false;
                    }
                }
                return true;
            });
            out.println("Массивы с отрицательными числами: " + filteredArrays);
        }
        else if (num1 == 3 && num2 == 3) {
            List<String> strings = List.of("qwerty", "asdfg", "zx");
            String combinedString = sumList(strings, String::concat, "");
            System.out.println("Объединенная строка: " + combinedString);

            List<Integer> numbers = List.of(1, -3, 7);
            Integer sum = sumList(numbers, Integer::sum, 0);
            System.out.println("Сумма чисел: " + sum);
        }
        else if (num1 == 3 && num2 == 4) {

        }
        else {
            out.println("Нет такого задания");
        }

    }
}