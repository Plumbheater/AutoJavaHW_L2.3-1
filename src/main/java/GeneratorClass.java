import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;

public class GeneratorClass {
    private GeneratorClass() {
    }

    public static class Registration {
        private Registration() {
        }

        public static TestGenerator generateByCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            String[] cities = {"Красноярск", "Томск", "Новосибирск", "Омск", "Ишим", "Челябинск",
                    "Пермь", "Уфа", "Самара", "Казань", "Нижний Новгород",
                    "Владимир", "Москва", "Санкт-Петербург"};
            Random rnd = new Random();

            return new TestGenerator(faker.name().name(),
                    faker.phoneNumber().phoneNumber(),
                    cities[rnd.nextInt(cities.length)]);
        }
    }
}
