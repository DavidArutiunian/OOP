package labs.lab4.shapes;

import com.github.javafaker.Faker;
import labs.lab4.shapes.point.Point;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {
    public final double DELTA = 5e-5;
    @Getter
    private final Faker faker = new Faker();

    public Point getRandomPoint() {
        return new Point(faker.random().nextDouble(), faker.random().nextDouble());
    }

    public int getRandomHex() {
        return faker.number().numberBetween(0x000000, 0xffffff);
    }

}
