package labs.lab4;

import labs.lab4.point.CPoint;
import labs.lab4.shape.IShape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@UtilityClass
class CommandLineParser {
    void parseCommandLine(final List<IShape> shapes, final Scanner scanner) throws IOException {
        if (!scanner.hasNext()) {
            throw new IOException("No input found!");
        }
        while (scanner.hasNext()) {
            val shape = scanner.next();
            if (EShape.RECTANGLE.getType().equals(shape)) {
                val leftTop = getNextPoint(scanner);
                val width = scanner.nextDouble();
                val height = scanner.nextDouble();
                val rightBottom = ShapeFactory.createPoint(leftTop.x + width, leftTop.y + height);
                val outlineColor = getNextHex(scanner);
                val fillColor = getNextHex(scanner);
                val rectangle = ShapeFactory.createRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
                shapes.add(rectangle);
            } else if (EShape.CIRCLE.getType().equals(shape)) {
                val center = getNextPoint(scanner);
                val radius = scanner.nextDouble();
                val outlineColor = getNextHex(scanner);
                val fillColor = getNextHex(scanner);
                val circle = ShapeFactory.createCircle(center, radius, outlineColor, fillColor);
                shapes.add(circle);
            } else if (EShape.LINE.getType().equals(shape)) {
                val start = getNextPoint(scanner);
                val end = getNextPoint(scanner);
                val outlineColor = getNextHex(scanner);
                val line = ShapeFactory.createLineSegment(start, end, outlineColor);
                shapes.add(line);
            } else if (EShape.TRIANGLE.getType().equals(shape)) {
                val vertex1 = getNextPoint(scanner);
                val vertex2 = getNextPoint(scanner);
                val vertex3 = getNextPoint(scanner);
                val outlineColor = getNextHex(scanner);
                val fillColor = getNextHex(scanner);
                val triangle = ShapeFactory.createTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
                shapes.add(triangle);
            }
        }
    }

    IShape getShapeWithMaxArea(final List<IShape> shapes) {
        return shapes.stream().max(Comparator.comparing(IShape::getArea)).orElseThrow();
    }

    IShape getShapeWithMinPerimeter(final List<IShape> shapes) {
        return shapes.stream().min(Comparator.comparing(IShape::getPerimeter)).orElseThrow();
    }

    private CPoint getNextPoint(final Scanner scanner) {
        return ShapeFactory.createPoint(scanner.nextDouble(), scanner.nextDouble());
    }

    private int getNextHex(final Scanner scanner) {
        return scanner.nextInt(ERadix.HEX.getRadix());
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
