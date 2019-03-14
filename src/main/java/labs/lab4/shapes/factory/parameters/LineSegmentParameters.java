package labs.lab4.shapes.factory.parameters;

import labs.lab4.shapes.point.Point;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class LineSegmentParameters extends ShapeParameters {
    private final Point start;
    private final Point end;
}
