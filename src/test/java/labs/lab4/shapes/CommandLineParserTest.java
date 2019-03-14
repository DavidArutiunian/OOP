package labs.lab4.shapes;

import labs.lab4.shapes.factory.ShapeFactory;
import labs.lab4.shapes.factory.parameters.*;
import labs.lab4.shapes.point.Point;
import labs.lab4.shapes.shape.IShape;
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
    private final ShapeFactory factory = new ShapeFactory();

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
            val leftTop = new Point(10.3, 20.15);
            val rightBottom = new Point(leftTop.x + width, leftTop.y + height);
            val parameters = new RectangleParameters(leftTop, rightBottom, width, height);
            if (!isShortForm) {
                setParametersColors(parameters);
            }
            val rectangle = factory.createShape(parameters);
            expected.add(rectangle);
        }
        {
            val center = new Point(12.5, 26.2);
            val parameters = new CircleParameters(center, 13.4);
            if (!isShortForm) {
                setParametersColors(parameters);
            }
            val circle = factory.createShape(parameters);
            expected.add(circle);
        }
        {
            val vertex1 = new Point(10.3, 20.15);
            val vertex2 = new Point(30.7, 40.4);
            val vertex3 = new Point(12.5, 26.2);
            val parameters = new TriangleParameters(vertex1, vertex2, vertex3);
            if (!isShortForm) {
                setParametersColors(parameters);
            }
            val triangle = factory.createShape(parameters);
            expected.add(triangle);
        }
        {
            val start = new Point(10.3, 20.15);
            val end = new Point(30.7, 40.4);
            val parameters = new LineSegmentParameters(start, end);
            if (!isShortForm) {
                setParametersColors(parameters);
            }
            val line = factory.createShape(parameters);
            expected.add(line);
        }
        return expected;
    }

    private void setParametersColors(final ShapeParameters parameters) {
        parameters.setOutlineColor(0xFF0000);
    }

    private void setParametersColors(final SolidShapeParameters parameters) {
        setParametersColors((ShapeParameters) parameters);
        parameters.setFillColor(0x00FF00);
    }

    private void setSystemInput(final String input) {
        final var in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }
}
