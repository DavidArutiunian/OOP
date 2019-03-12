package labs.lab4;

import labs.lab4.factory.ShapeFactory;
import labs.lab4.shape.IShape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.ArrayList;
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
                    val leftTop = ShapeFactory.CreatePoint(scanner.nextDouble(), scanner.nextDouble());
                    val width = scanner.nextDouble();
                    val height = scanner.nextDouble();
                    val rightBottom = ShapeFactory.CreatePoint(leftTop.x + width, leftTop.y + height);
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val fillColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val rectangle = ShapeFactory.CreateRectangle(leftTop, rightBottom, width, height, outlineColor, fillColor);
                    shapes.add(rectangle);
                } else if (EShape.CIRCLE.getType().equals(shape)) {
                    val center = ShapeFactory.CreatePoint(scanner.nextDouble(), scanner.nextDouble());
                    val radius = scanner.nextDouble();
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val fillColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val circle = ShapeFactory.CreateCircle(center, radius, outlineColor, fillColor);
                    shapes.add(circle);
                } else if (EShape.LINE.getType().equals(shape)) {
                    val start = ShapeFactory.CreatePoint(scanner.nextDouble(), scanner.nextDouble());
                    val end = ShapeFactory.CreatePoint(scanner.nextDouble(), scanner.nextDouble());
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val line = ShapeFactory.CreateLineSegment(start, end, outlineColor);
                    shapes.add(line);
                } else if (EShape.TRIANGLE.getType().equals(shape)) {
                    val vertex1 = ShapeFactory.CreatePoint(scanner.nextDouble(), scanner.nextDouble());
                    val vertex2 = ShapeFactory.CreatePoint(scanner.nextDouble(), scanner.nextDouble());
                    val vertex3 = ShapeFactory.CreatePoint(scanner.nextDouble(), scanner.nextDouble());
                    val outlineColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val fillColor = scanner.nextInt(ERadix.HEX.getRadix());
                    val triangle = ShapeFactory.CreateTriangle(vertex1, vertex2, vertex3, outlineColor, fillColor);
                    shapes.add(triangle);
                }
            }
            shapes.forEach(shape -> System.out.println('\n' + shape.ToString()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
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
