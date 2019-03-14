package labs.lab4.shapes.circle;

import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.point.Point;
import labs.lab4.shapes.shape.ISolidShape;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Circle implements ISolidShape {
    private final Point center;
    private final double radius;
    @Setter
    private int fillColor;
    @Setter
    private int outlineColor;

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

    @Override
    public void draw(final ICanvas canvas) {
        canvas.drawCircle(center, radius, outlineColor);
        canvas.fillCircle(center, radius, fillColor);
    }
}
