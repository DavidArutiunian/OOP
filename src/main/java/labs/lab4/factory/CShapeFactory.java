package labs.lab4.factory;

import labs.lab4.circle.CCircle;
import labs.lab4.factory.parameters.*;
import labs.lab4.line_segment.CLineSegment;
import labs.lab4.rectangle.CRectangle;
import labs.lab4.shape.IShape;
import labs.lab4.shape.ISolidShape;
import labs.lab4.triangle.CTriangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

public class CShapeFactory {
    public IShape createShape(final CCircleParameters parameters) {
        val circle = new CCircle(parameters.getCenter(), parameters.getRadius());
        setShapeColors(circle, parameters);
        return circle;
    }

    public IShape createShape(final CRectangleParameters parameters) {
        val rectangle = new CRectangle(parameters.getLeftTop(), parameters.getRightBottom(), parameters.getWidth(), parameters.getHeight());
        setShapeColors(rectangle, parameters);
        return rectangle;
    }

    public IShape createShape(final CTriangleParameters parameters) {
        val triangle = new CTriangle(parameters.getVertex1(), parameters.getVertex2(), parameters.getVertex3());
        setShapeColors(triangle, parameters);
        return triangle;
    }

    public IShape createShape(final CLineSegmentParameters parameters) {
        val line = new CLineSegment(parameters.getStart(), parameters.getEnd());
        setShapeColors(line, parameters);
        return line;
    }

    private void setShapeColors(final ISolidShape shape, final CSolidShapeParameters parameters) {
        setShapeColors(shape, (CShapeParameters) parameters);
        if (parameters.getFillColor() != null) {
            shape.setFillColor(parameters.getFillColor());
        } else {
            shape.setFillColor(EColor.BLACK.getColor());
        }
    }

    private void setShapeColors(final IShape shape, final CShapeParameters parameters) {
        if (parameters.getOutlineColor() != null) {
            shape.setOutlineColor(parameters.getOutlineColor());
        } else {
            shape.setOutlineColor(EColor.WHITE.getColor());
        }
    }

    @RequiredArgsConstructor
    private enum EColor {
        BLACK(0x000000),
        WHITE(0xFFFFFF);
        @Getter
        private final int color;
    }
}
