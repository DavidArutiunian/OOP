package labs.lab4.factory.parameters;

import labs.lab4.point.CPoint;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class CLineSegmentParameters extends CShapeParameters {
    private final CPoint start;
    private final CPoint end;
}
