package labs.lab4;

import labs.lab4.factory.ShapeFactory;
import labs.lab4.shape.IShape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        try {
            final List<IShape> shapes = new ArrayList<>();
            val scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                val shape = scanner.next();
                if (EShape.RECTANGLE.getType().equals(shape)) {
                    val leftTop = ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
                    val width = scanner.nextDouble();
                    val height = scanner.nextDouble();
                    val rightBottom = ShapeFactory.createPoint(leftTop.x + width, leftTop.y + height);
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val fillColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val rectangle = ShapeFactory.createRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
                    shapes.add(rectangle);
                } else if (EShape.CIRCLE.getType().equals(shape)) {
                    val center = ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
                    val radius = scanner.nextDouble();
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val fillColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val circle = ShapeFactory.createCircle(center, radius, outlineColor, fillColor);
                    shapes.add(circle);
                } else if (EShape.LINE.getType().equals(shape)) {
                    val start = ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
                    val end = ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val line = ShapeFactory.createLineSegment(start, end, outlineColor);
                    shapes.add(line);
                } else if (EShape.TRIANGLE.getType().equals(shape)) {
                    val vertex1 = ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
                    val vertex2 = ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
                    val vertex3 = ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val fillColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val triangle = ShapeFactory.createTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
                    shapes.add(triangle);
                }
            }
            System.out.println("Max area has shape:\n" + getShapeWithMaxArea(shapes).toString());
            System.out.println("Min perimeter has shape:\n" + getShapeWithMinPerimeter(shapes).toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static IShape getShapeWithMaxArea(final List<IShape> shapes) {
        return shapes.stream().max(Comparator.comparing(IShape::getArea)).orElseThrow();
    }

    private static IShape getShapeWithMinPerimeter(final List<IShape> shapes) {
        return shapes.stream().min(Comparator.comparing(IShape::getPerimeter)).orElseThrow();
    }

    @RequiredArgsConstructor
    private enum EShape {
        RECTANGLE("rectangle"),
        CIRCLE("circle"),
        LINE("line"),
        TRIANGLE("triangle");
        @Getter
        private final String type;
    }

    @RequiredArgsConstructor
    private enum ERadix {
        HEX(16);
        @Getter
        private final int radix;
    }
}
