package labs.lab4.line_segment;

import labs.lab4.point.CPoint;
import labs.lab4.shape.IShape;
import lombok.RequiredArgsConstructor;
import lombok.val;

@RequiredArgsConstructor
public class CLineSegment implements IShape {
    private final CPoint start;
    private final CPoint end;
    private final int outlineColor;

    @Override
    public double GetArea() {
        return 0;
    }

    @Override
    public double GetPerimeter() {
        val left = Math.pow(end.x - start.x, 2);
        val right = Math.pow(end.y - start.y, 2);
        return Math.sqrt(left + right);
    }

    @Override
    public String ToString() {
        return "Line:\n" +
            "start:\n" + start.ToString(4) +
            "end:\n" + end.ToString(4) +
            "outlineColor = " + Integer.toHexString(outlineColor) + '\n' +
            "area = " + GetArea() + '\n' +
            "perimeter = " + GetPerimeter() + '\n';
    }

    @Override
    public int GetOutlineColor() {
        return outlineColor;
    }

    CPoint GetStartPoint() {
        return start;
    }

    CPoint GetEndPoint() {
        return end;
    }
}
