package labs.lab4.shapes.factory.parameters;

import labs.lab4.shapes.point.CPoint;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CTriangleParameters extends CSolidShapeParameters {
    private final CPoint vertex1;
    private final CPoint vertex2;
    private final CPoint vertex3;
}
