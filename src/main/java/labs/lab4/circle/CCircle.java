package labs.lab4.circle;

import labs.lab4.point.CPoint;
import labs.lab4.shape.ISolidShape;
import lombok.Value;

@Value
public class CCircle implements ISolidShape {
    private final CPoint center;
    private final double radius;
    private final int fillColor;
    private final int outlineColor;

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle:\n" +
            "center:\n" + center.toString(4) +
            "radius = " + radius + '\n' +
            "fillColor = " + Integer.toHexString(fillColor) + '\n' +
            "outlineColor = " + Integer.toHexString(outlineColor) + '\n' +
            "area = " + getArea() + '\n' +
            "perimeter = " + getPerimeter() + '\n';
    }
}
