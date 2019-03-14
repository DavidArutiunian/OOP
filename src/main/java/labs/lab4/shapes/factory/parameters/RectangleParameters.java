package labs.lab4.shapes.factory.parameters;

import labs.lab4.shapes.point.Point;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class RectangleParameters extends SolidShapeParameters {
    private final Point leftTop;
    private final Point rightBottom;
    private final Double width;
    private final Double height;
}
