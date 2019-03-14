package labs.lab4.shapes.factory.parameters;

import labs.lab4.shapes.point.Point;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class TriangleParameters extends SolidShapeParameters {
    private final Point vertex1;
    private final Point vertex2;
    private final Point vertex3;
}
