package labs.lab4;

import labs.lab4.shape.IShape;
import lombok.val;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandLineParserTest {
    @Test
    public void parseCommandLine() throws IOException {
        val expected = getExpectedResults();
        setSystemInput("rectangle 10.3 20.15 30.7 40.4 ff0000 00ff00\n" +
            "circle 12.5 26.2 13.4 ff0000 00ff00\n" +
            "triangle 10.3 20.15 30.7 40.4 12.5 26.2 ff0000 00ff00\n" +
            "line 10.3 20.15 30.7 40.4 ff0000\n");
        final List<IShape> actual = new ArrayList<>();
        val scanner = new Scanner(System.in);
        CommandLineParser.parseCommandLine(actual, scanner);
        for (int i = 0; i < actual.size(); ++i) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    @Test
    public void parseCommandLineShort() throws IOException {
        val expected = getExpectedResults(true);
        setSystemInput("rectangle 10.3 20.15 30.7 40.4\n" +
            "circle 12.5 26.2 13.4\n" +
            "triangle 10.3 20.15 30.7 40.4 12.5 26.2\n" +
            "line 10.3 20.15 30.7 40.4\n");
        final List<IShape> actual = new ArrayList<>();
        val scanner = new Scanner(System.in);
        CommandLineParser.parseCommandLine(actual, scanner);
        for (int i = 0; i < actual.size(); ++i) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    @Test
    public void parseCommandLineThrowsOnEmptyInput() {
        setSystemInput("");
        final List<IShape> actual = new ArrayList<>();
        val scanner = new Scanner(System.in);
        assertThrows(IOException.class, () -> CommandLineParser.parseCommandLine(actual, scanner));
    }

    @Test
    public void parseCommandLineThrowsIfBadArguments() {
        setSystemInput("rectangle 10.3 20.15\n");
        final List<IShape> actual = new ArrayList<>();
        val scanner = new Scanner(System.in);
        assertThrows(IOException.class, () -> CommandLineParser.parseCommandLine(actual, scanner));
    }

    @Test
    public void getShapeWithMaxArea() {
        val expected = getExpectedResults();
        val actual = CommandLineParser.getShapeWithMaxArea(expected);
        assertEquals(expected.get(0), actual);
    }

    @Test
    public void getShapeWithMinPerimeter() {
        val expected = getExpectedResults();
        val actual = CommandLineParser.getShapeWithMinPerimeter(expected);
        assertEquals(expected.get(3), actual);
    }

    private List<IShape> getExpectedResults() {
        return getExpectedResults(false);
    }

    private List<IShape> getExpectedResults(final boolean isShortForm) {
        final List<IShape> expected = new ArrayList<>();
        {
            val width = 30.7;
            val height = 40.4;
            val leftTop = ShapeFactory.createPoint(10.3, 20.15);
            val rightBottom = ShapeFactory.createPoint(leftTop.x + width, leftTop.y + height);
            IShape rectangle;
            if (isShortForm) {
                rectangle = ShapeFactory.createRectangle(leftTop, rightBottom, width, height);
            } else {
                rectangle = ShapeFactory.createRectangle(leftTop, rightBottom, width, height, 0xFF0000, 0x00FF00);
            }
            expected.add(rectangle);
        }
        {
            val center = ShapeFactory.createPoint(12.5, 26.2);
            IShape circle;
            if (isShortForm) {
                circle = ShapeFactory.createCircle(center, 13.4);
            } else {
                circle = ShapeFactory.createCircle(center, 13.4, 0xFF0000, 0x00FF00);
            }
            expected.add(circle);
        }
        {
            val vertex1 = ShapeFactory.createPoint(10.3, 20.15);
            val vertex2 = ShapeFactory.createPoint(30.7, 40.4);
            val vertex3 = ShapeFactory.createPoint(12.5, 26.2);
            IShape triangle;
            if (isShortForm) {
                triangle = ShapeFactory.createTriangle(vertex1, vertex2, vertex3);
            } else {
                triangle = ShapeFactory.createTriangle(vertex1, vertex2, vertex3, 0xFF0000, 0x00FF00);
            }
            expected.add(triangle);
        }
        {
            val start = ShapeFactory.createPoint(10.3, 20.15);
            val end = ShapeFactory.createPoint(30.7, 40.4);
            IShape line;
            if (isShortForm) {
                line = ShapeFactory.createLineSegment(start, end);
            } else {
                line = ShapeFactory.createLineSegment(start, end, 0xFF0000);
            }
            expected.add(line);
        }
        return expected;
    }

    private void setSystemInput(final String input) {
        final var in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
