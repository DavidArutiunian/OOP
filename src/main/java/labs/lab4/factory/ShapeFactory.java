package labs.lab4.factory;

import labs.lab4.circle.CCircle;
import labs.lab4.line_segment.CLineSegment;
import labs.lab4.point.CPoint;
import labs.lab4.rectangle.CRectangle;
import labs.lab4.triangle.CTriangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShapeFactory {
    public CPoint CreatePoint(final double x, final double y) {
        return new CPoint(x, y);
    }

    public CCircle CreateCircle(final CPoint center, final double radius, final int outlineColor, final int fillColor) {
        return new CCircle(center, radius, fillColor, outlineColor);
    }

    public CCircle CreateCircle(final CPoint center, final double radius) {
        return CreateCircle(center, radius, EColor.WHITE.getValue(), EColor.BLACK.getValue());
    }

    public CLineSegment CreateLineSegment(final CPoint start, final CPoint end, final int outlineColor) {
        return new CLineSegment(start, end, outlineColor);
    }

    public CLineSegment CreateLineSegment(final CPoint start, final CPoint end) {
        return CreateLineSegment(start, end, EColor.WHITE.getValue());
    }

    public CRectangle CreateRectangle(final CPoint leftTop, final CPoint rightBottom, final double width, final double height, final int outlineColor, final int fillColor) {
        return new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
    }

    public CRectangle CreateRectangle(final CPoint leftTop, final CPoint rightBottom, final double width, final double height) {
        return CreateRectangle(leftTop, rightBottom, width, height, EColor.WHITE.getValue(), EColor.BLACK.getValue());
    }

    public CTriangle CreateTriangle(final CPoint vertex1, final CPoint vertex2, final CPoint vertex3, final int outlineColor, final int fillColor) {
        return new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
    }

    public CTriangle CreateTriangle(final CPoint vertex1, final CPoint vertex2, final CPoint vertex3) {
        return CreateTriangle(vertex1, vertex2, vertex3, EColor.WHITE.getValue(), EColor.BLACK.getValue());
    }

    @RequiredArgsConstructor
    private enum EColor {
        BLACK(0x000000),
        WHITE(0xFFFFFF);
        @Getter
        private final int value;
    }
}
