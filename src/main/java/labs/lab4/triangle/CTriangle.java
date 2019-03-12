package labs.lab4.triangle;

import labs.lab4.factory.ShapeFactory;
import labs.lab4.point.CPoint;
import labs.lab4.shape.ISolidShape;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class CTriangle implements ISolidShape {
    private final CPoint vertex1;
    private final CPoint vertex2;
    private final CPoint vertex3;
    private final int outlineColor;
    private final int fillColor;

    @Override
    public int GetFillColor() {
        return fillColor;
    }

    @Override
    public double GetArea() {
        double a = vertex1.x * (vertex2.y - vertex3.y);
        double b = vertex2.x * (vertex3.y - vertex1.y);
        double c = vertex3.x * (vertex1.y - vertex2.y);
        val area = 0.5 * (a + b + c);
        return Math.abs(area);
    }

    @Override
    public double GetPerimeter() {
        val edge1 = ShapeFactory.CreateLineSegment(vertex1, vertex2);
        val edge2 = ShapeFactory.CreateLineSegment(vertex2, vertex3);
        val edge3 = ShapeFactory.CreateLineSegment(vertex3, vertex1);
        return edge1.GetPerimeter() + edge2.GetPerimeter() + edge3.GetPerimeter();
    }

    @Override
    public String ToString() {
        return "CTriangle{" +
            "vertex1=" + vertex1 +
            ", vertex2=" + vertex2 +
            ", vertex3=" + vertex3 +
            ", outlineColor=" + outlineColor +
            ", fillColor=" + fillColor +
            '}';
    }

    @Override
    public int GetOutlineColor() {
        return outlineColor;
    }

    CPoint GetVertex1() {
        return vertex1;
    }

    CPoint GetVertex2() {
        return vertex2;
    }

    CPoint GetVertex3() {
        return vertex3;
    }
}
