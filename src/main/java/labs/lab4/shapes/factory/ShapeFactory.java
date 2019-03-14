package labs.lab4.shapes.factory;

import labs.lab4.shapes.circle.Circle;
import labs.lab4.shapes.factory.parameters.*;
import labs.lab4.shapes.line_segment.LineSegment;
import labs.lab4.shapes.rectangle.Rectangle;
import labs.lab4.shapes.shape.IShape;
import labs.lab4.shapes.shape.ISolidShape;
import labs.lab4.shapes.triangle.Triangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

public class ShapeFactory {
    public IShape createShape(CircleParameters parameters) {
        val circle = new Circle(parameters.getCenter(), parameters.getRadius());
        setShapeColors(circle, parameters);
        return circle;
    }

    public IShape createShape(RectangleParameters parameters) {
        val rectangle = new Rectangle(parameters.getLeftTop(), parameters.getRightBottom(), parameters.getWidth(), parameters.getHeight());
        setShapeColors(rectangle, parameters);
        return rectangle;
    }

    public IShape createShape(TriangleParameters parameters) {
        val triangle = new Triangle(parameters.getVertex1(), parameters.getVertex2(), parameters.getVertex3());
        setShapeColors(triangle, parameters);
        return triangle;
    }

    public IShape createShape(LineSegmentParameters parameters) {
        val line = new LineSegment(parameters.getStart(), parameters.getEnd());
        setShapeColors(line, parameters);
        return line;
    }

    private void setShapeColors(ISolidShape shape, SolidShapeParameters parameters) {
        setShapeColors(shape, (ShapeParameters) parameters);
        if (parameters.getFillColor() != null) {
            shape.setFillColor(parameters.getFillColor());
        } else {
            shape.setFillColor(EColor.WHITE.getColor());
        }
    }

    private void setShapeColors(IShape shape, ShapeParameters parameters) {
        if (parameters.getOutlineColor() != null) {
            shape.setOutlineColor(parameters.getOutlineColor());
        } else {
            shape.setOutlineColor(EColor.BLACK.getColor());
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
