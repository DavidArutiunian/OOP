package labs.lab4.shapes.factory.parameters;

import labs.lab4.shapes.point.Point;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CircleParameters extends SolidShapeParameters {
    private final Point center;
    private final double radius;
}
