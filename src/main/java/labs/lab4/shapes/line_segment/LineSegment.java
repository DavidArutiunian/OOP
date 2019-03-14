package labs.lab4.shapes.line_segment;

import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.point.Point;
import labs.lab4.shapes.shape.IShape;
import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LineSegment implements IShape {
    private final Point startPoint;
    private final Point endPoint;
    @Setter
    private int outlineColor;

    @Override
    public double getArea() {
        return 0;
    }

    @Override
    public double getPerimeter() {
        val left = Math.pow(endPoint.x - startPoint.x, 2);
        val right = Math.pow(endPoint.y - startPoint.y, 2);
        return Math.sqrt(left + right);
    }

    @Override
    public String toString() {
        return "Line:\n" +
            "startPoint:\n" + startPoint.toString(4) +
            "endPoint:\n" + endPoint.toString(4) +
            "outlineColor = " + Integer.toHexString(outlineColor) + '\n' +
            "area = " + getArea() + '\n' +
            "perimeter = " + getPerimeter() + '\n';
    }

    @Override
    public void draw(final ICanvas canvas) {
        canvas.drawLine(startPoint, endPoint, outlineColor);
    }
}
