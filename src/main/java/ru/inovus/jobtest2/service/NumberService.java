package ru.inovus.jobtest2.service;

import org.springframework.stereotype.Service;
import ru.inovus.jobtest2.model.Car;

import java.util.*;

@Service
public class NumberService {
    private static final String CONSTANTA = " 116 RUS";
    private final List<String> letters = Arrays.asList("А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х");
    private final List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private final List<Car> cars = new ArrayList<>();

    public String random() {
        Random random = new Random();
        StringBuilder carNumber = new StringBuilder();
        carNumber.append(letters.get(random.nextInt(12)));
        for (int i = 0; i < 3; i++) {
            carNumber.append(numbers.get(random.nextInt(10)));
        }
        for (int i = 0; i < 2; i++) {
            carNumber.append(letters.get(random.nextInt(12)));
        }
        carNumber.append(CONSTANTA);
        if (checkNumber(carNumber.toString())) {
            Car car = new Car(carNumber.toString());
            cars.add(car);
            return car.getNumber();
        } else {
            return random();
        }
    }

    public String next() {
        if (cars.isEmpty()) {
            Car car = new Car("A000AA" + CONSTANTA);
            cars.add(car);
            return car.getNumber();
        }
        String lastCarNumber = cars.stream().reduce((e1, e2) -> e2).get().getNumber();
        String nextCarNumber = createNextCarNumber(lastCarNumber);
        cars.add(new Car(nextCarNumber));
        return nextCarNumber;
    }

    public boolean checkNumber(String carNumber) {
        Car carExist = cars.stream().filter(car -> carNumber.equals(car.getNumber())).findFirst().orElse(null);
        return carExist == null;
    }

    public String createNextCarNumber(String carNumber) {
        String part1 = carNumber.substring(0, 1);
        String part2 = carNumber.substring(4, 5);
        String part3 = carNumber.substring(5, 6);
        int digit = Integer.parseInt(carNumber.substring(1, 4));
        digit++;
        if (digit > 999) {
            digit = 0;
            part3 = letters.get(letters.indexOf(part3) + 1);
            if (Objects.equals(part3, "А")) {
                part2 = letters.get(letters.indexOf(part2) + 1);
                if (Objects.equals(part2, "А")) {
                    part1 = letters.get(letters.indexOf(part1) + 1);
                }
            }
        }
        String newCarNumber = String.join("", part1, String.format("%03d", digit), part2, part3, CONSTANTA);
        if (checkNumber(newCarNumber))
            return newCarNumber;
        else
            return createNextCarNumber(newCarNumber);
    }
}
