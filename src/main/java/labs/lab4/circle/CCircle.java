package labs.lab4.circle;

import labs.lab4.point.CPoint;
import labs.lab4.shape.ISolidShape;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CCircle implements ISolidShape {
    private final CPoint center;
    private final double radius;
    private final int fillColor;
    private final int outlineColor;

    @Override
    public int GetFillColor() {
        return fillColor;
    }

    @Override
    public double GetArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double GetPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String ToString() {
        return "Circle:\n" +
            "center:\n" + center.ToString(4) +
            "radius = " + radius + '\n' +
            "fillColor = " + Integer.toHexString(fillColor) + '\n' +
            "outlineColor = " + Integer.toHexString(outlineColor) + '\n' +
            "area = " + GetArea() + '\n' +
            "perimeter = " + GetPerimeter() + '\n';
    }

    @Override
    public int GetOutlineColor() {
        return outlineColor;
    }

    CPoint GetCenter() {
        return center;
    }

    double GetRadius() {
        return radius;
    }
}
