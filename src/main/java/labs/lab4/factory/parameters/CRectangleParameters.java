package labs.lab4.factory.parameters;

import labs.lab4.point.CPoint;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CRectangleParameters extends CSolidShapeParameters {
    private final CPoint leftTop;
    private final CPoint rightBottom;
    private final Double width;
    private final Double height;
}
