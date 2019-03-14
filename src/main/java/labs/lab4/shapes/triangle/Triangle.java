package labs.lab4.shapes.triangle;

import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.line_segment.LineSegment;
import labs.lab4.shapes.point.Point;
import labs.lab4.shapes.shape.ISolidShape;
import lombok.*;

import java.util.Arrays;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Triangle implements ISolidShape {
    private final Point vertex1;
    private final Point vertex2;
    private final Point vertex3;
    @Setter
    private int outlineColor;
    @Setter
    private int fillColor;

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
        val edge1 = new LineSegment(vertex1, vertex2);
        val edge2 = new LineSegment(vertex2, vertex3);
        val edge3 = new LineSegment(vertex3, vertex1);
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

    @Override
    public void draw(final ICanvas canvas) {
        canvas.drawLine(vertex1, vertex2, outlineColor);
        canvas.drawLine(vertex2, vertex3, outlineColor);
        canvas.drawLine(vertex3, vertex1, outlineColor);
        canvas.fillPolygon(Arrays.asList(vertex1, vertex2, vertex3), fillColor);
    }
}
