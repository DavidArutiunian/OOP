package labs.lab4.triangle;

import labs.lab4.ShapeFactory;
import labs.lab4.point.CPoint;
import labs.lab4.shape.ISolidShape;
import lombok.Value;
import lombok.val;

@Value
public class CTriangle implements ISolidShape {
    private final CPoint vertex1;
    private final CPoint vertex2;
    private final CPoint vertex3;
    private final int outlineColor;
    private final int fillColor;

    @Override
    public double getArea() {
        double a = vertex1.x * (vertex2.y - vertex3.y);
        double b = vertex2.x * (vertex3.y - vertex1.y);
        double c = vertex3.x * (vertex1.y - vertex2.y);
        val area = 0.5 * (a + b + c);
        return Math.abs(area);
    }

    @Override
    public double getPerimeter() {
        val edge1 = ShapeFactory.createLineSegment(vertex1, vertex2);
        val edge2 = ShapeFactory.createLineSegment(vertex2, vertex3);
        val edge3 = ShapeFactory.createLineSegment(vertex3, vertex1);
        return edge1.getPerimeter() + edge2.getPerimeter() + edge3.getPerimeter();
    }

    @Override
    public String toString() {
        return "Triangle:\n" +
            "vertex 1:\n" + vertex1.toString(4) +
            "vertex 2:\n" + vertex2.toString(4) +
            "vertex 3:\n" + vertex3.toString(4) +
            "fillColor = " + Integer.toHexString(fillColor) + '\n' +
            "outlineColor = " + Integer.toHexString(outlineColor) + '\n' +
            "area = " + getArea() + '\n' +
            "perimeter = " + getPerimeter() + '\n';
    }
}
