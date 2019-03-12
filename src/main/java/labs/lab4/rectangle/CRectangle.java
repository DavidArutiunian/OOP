package labs.lab4.rectangle;

import labs.lab4.point.CPoint;
import labs.lab4.shape.ISolidShape;
import lombok.Value;

@Value
public class CRectangle implements ISolidShape {
    private final CPoint leftTop;
    private final CPoint rightBottom;
    private final double width;
    private final double height;
    private final int outlineColor;
    private final int fillColor;

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
}
