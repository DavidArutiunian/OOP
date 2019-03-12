package labs.lab4.rectangle;

import labs.lab4.point.CPoint;
import labs.lab4.shape.ISolidShape;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CRectangle implements ISolidShape {
    private final CPoint leftTop;
    private final CPoint rightBottom;
    private final double width;
    private final double height;
    private final int outlineColor;
    private final int fillColor;

    @Override
    public int GetFillColor() {
        return fillColor;
    }

    @Override
    public double GetArea() {
        return width * height;
    }

    @Override
    public double GetPerimeter() {
        return 2 * width + 2 * height;
    }

    @Override
    public String ToString() {
        return "Rectangle:\n" +
            "leftTop:\n" + leftTop.ToString(4) +
            "rightBottom:\n" + rightBottom.ToString(4) +
            "width = " + width + '\n' +
            "height = " + height + '\n' +
            "outlineColor = " + Integer.toHexString(outlineColor) + '\n' +
            "fillColor = " + Integer.toHexString(fillColor) + '\n' +
            "area = " + GetArea() + '\n' +
            "perimeter = " + GetPerimeter() + '\n';
    }

    @Override
    public int GetOutlineColor() {
        return outlineColor;
    }

    CPoint GetLeftTop() {
        return leftTop;
    }

    CPoint GetRightBottom() {
        return rightBottom;
    }

    double GetWidth() {
        return width;
    }

    double GetHeight() {
        return height;
    }
}
