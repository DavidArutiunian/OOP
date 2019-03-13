package labs.lab4.shapes.factory.parameters;

import labs.lab4.shapes.point.CPoint;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CLineSegmentParameters extends CShapeParameters {
    private final CPoint start;
    private final CPoint end;
}
