package labs.lab4.shapes.rectangle;

import labs.lab4.shapes.canvas.ICanvas;
import labs.lab4.shapes.point.Point;
import labs.lab4.shapes.shape.ISolidShape;
import lombok.*;

import java.util.Arrays;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Rectangle implements ISolidShape {
    private final Point leftTop;
    private final Point rightBottom;
    private final double width;
    private final double height;
    @Setter
    private int outlineColor;
    @Setter
    private int fillColor;

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * width + 2 * height;
    }

    @Override
    public String toString() {
        return "Rectangle:\n" +
            "leftTop:\n" + leftTop.toString(4) +
            "rightBottom:\n" + rightBottom.toString(4) +
            "width = " + width + '\n' +
            "height = " + height + '\n' +
            "outlineColor = " + Integer.toHexString(outlineColor) + '\n' +
            "fillColor = " + Integer.toHexString(fillColor) + '\n' +
            "area = " + getArea() + '\n' +
            "perimeter = " + getPerimeter() + '\n';
    }

    @Override
    public void draw(ICanvas canvas) {
        val rightTop = new Point(rightBottom.x, leftTop.y);
        val leftBottom = new Point(leftTop.x, rightBottom.y);
        canvas.drawLine(leftTop, rightTop, outlineColor);
        canvas.drawLine(rightTop, rightBottom, outlineColor);
        canvas.drawLine(rightBottom, leftBottom, outlineColor);
        canvas.drawLine(leftBottom, leftTop, outlineColor);
        canvas.fillPolygon(Arrays.asList(leftTop, rightTop, rightBottom, leftBottom), fillColor);
    }
}
