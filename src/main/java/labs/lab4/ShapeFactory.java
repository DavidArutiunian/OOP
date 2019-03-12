package labs.lab4;

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
    CPoint createPoint(final double x, final double y) {
        return new CPoint(x, y);
    }

    CCircle createCircle(final CPoint center, final double radius, final int outlineColor, final int fillColor) {
        return new CCircle(center, radius, fillColor, outlineColor);
    }

    CCircle createCircle(final CPoint center, final double radius) {
        return createCircle(center, radius, EColor.WHITE.getColor(), EColor.BLACK.getColor());
    }

    CLineSegment createLineSegment(final CPoint start, final CPoint end, final int outlineColor) {
        return new CLineSegment(start, end, outlineColor);
    }

    public CLineSegment createLineSegment(final CPoint start, final CPoint end) {
        return createLineSegment(start, end, EColor.WHITE.getColor());
    }

    CRectangle createRectangle(final CPoint leftTop, final CPoint rightBottom, final double width, final double height, final int outlineColor, final int fillColor) {
        return new CRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
    }

    CRectangle createRectangle(final CPoint leftTop, final CPoint rightBottom, final double width, final double height) {
        return createRectangle(leftTop, rightBottom, width, height, EColor.WHITE.getColor(), EColor.BLACK.getColor());
    }

    CTriangle createTriangle(final CPoint vertex1, final CPoint vertex2, final CPoint vertex3, final int outlineColor, final int fillColor) {
        return new CTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
    }

    CTriangle createTriangle(final CPoint vertex1, final CPoint vertex2, final CPoint vertex3) {
        return createTriangle(vertex1, vertex2, vertex3, EColor.WHITE.getColor(), EColor.BLACK.getColor());
    }

    @RequiredArgsConstructor
    private enum EColor {
        BLACK(0x000000),
        WHITE(0xFFFFFF);
        @Getter
        private final int color;
    }
}
