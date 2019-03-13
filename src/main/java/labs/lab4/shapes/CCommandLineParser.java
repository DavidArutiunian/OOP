package labs.lab4.shapes;

import labs.lab4.shapes.factory.CShapeFactory;
import labs.lab4.shapes.factory.parameters.*;
import labs.lab4.shapes.point.CPoint;
import labs.lab4.shapes.shape.IShape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@UtilityClass
class CCommandLineParser {
    private CShapeFactory factory = new CShapeFactory();

    void parseCommandLine(final List<IShape> shapes, final Scanner scanner) throws IOException {
        if (!scanner.hasNext()) {
            throw new IOException("No input found!");
        }
        while (scanner.hasNext()) {
            val shape = scanner.next();
            if (EShape.RECTANGLE.getType().equals(shape)) {
                val leftTop = getNextPoint(scanner);
                checkHasNextDouble(scanner);
                val width = scanner.nextDouble();
                checkHasNextDouble(scanner);
                val height = scanner.nextDouble();
                val rightBottom = new CPoint(leftTop.x + width, leftTop.y + height);
                val parameters = new CRectangleParameters(leftTop, rightBottom, width, height);
                appendColorsIfExist(scanner, parameters);
                val rectangle = factory.createShape(parameters);
                shapes.add(rectangle);
            } else if (EShape.CIRCLE.getType().equals(shape)) {
                val center = getNextPoint(scanner);
                checkHasNextDouble(scanner);
                val radius = scanner.nextDouble();
                val parameters = new CCircleParameters(center, radius);
                appendColorsIfExist(scanner, parameters);
                val circle = factory.createShape(parameters);
                shapes.add(circle);
            } else if (EShape.LINE.getType().equals(shape)) {
                val start = getNextPoint(scanner);
                val end = getNextPoint(scanner);
                val parameters = new CLineSegmentParameters(start, end);
                appendColorsIfExist(scanner, parameters);
                val line = factory.createShape(parameters);
                shapes.add(line);
            } else if (EShape.TRIANGLE.getType().equals(shape)) {
                val vertex1 = getNextPoint(scanner);
                val vertex2 = getNextPoint(scanner);
                val vertex3 = getNextPoint(scanner);
                val parameters = new CTriangleParameters(vertex1, vertex2, vertex3);
                appendColorsIfExist(scanner, parameters);
                val triangle = factory.createShape(parameters);
                shapes.add(triangle);
            } else {
                throw new IOException("Bad input!");
            }
        }
    }

    private void appendColorsIfExist(final Scanner scanner, final CShapeParameters parameters) {
        if (scanner.hasNextInt(ERadix.HEX.getRadix())) {
            val outlineColor = getNextHex(scanner);
            parameters.setOutlineColor(outlineColor);
        }
    }

    private void appendColorsIfExist(final Scanner scanner, final CSolidShapeParameters parameters) {
        appendColorsIfExist(scanner, (CShapeParameters) parameters);
        if (scanner.hasNextInt(ERadix.HEX.getRadix())) {
            val fillColor = getNextHex(scanner);
            parameters.setFillColor(fillColor);
        }
    }

    private void checkHasNextDouble(final Scanner scanner) throws IOException {
        if (scanner.hasNextDouble()) {
            return;
        }
        throw new IOException("Bad shape arguments!");
    }

    IShape getShapeWithMaxArea(final List<IShape> shapes) {
        return shapes.stream().max(Comparator.comparing(IShape::getArea)).orElseThrow();
    }

    IShape getShapeWithMinPerimeter(final List<IShape> shapes) {
        return shapes.stream().min(Comparator.comparing(IShape::getPerimeter)).orElseThrow();
    }

    private CPoint getNextPoint(final Scanner scanner) throws IOException {
        checkHasNextDouble(scanner);
        val x = scanner.nextDouble();
        checkHasNextDouble(scanner);
        val y = scanner.nextDouble();
        return new CPoint(x, y);
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
