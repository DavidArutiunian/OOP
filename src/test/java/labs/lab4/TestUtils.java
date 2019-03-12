package labs.lab4;

import com.github.javafaker.Faker;
import labs.lab4.factory.ShapeFactory;
import labs.lab4.point.CPoint;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {
    public final double DELTA = 5e-5;
    @Getter
    private final Faker faker = new Faker();

    public CPoint getRandomPoint() {
        return ShapeFactory.createPoint(faker.random().nextDouble(), faker.random().nextDouble());
    }

    public int getRandomHex() {
        return faker.number().numberBetween(0x000000, 0xffffff);
    }

}
