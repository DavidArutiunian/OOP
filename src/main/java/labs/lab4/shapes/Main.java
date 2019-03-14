package labs.lab4.shapes;

import labs.lab4.shapes.canvas.Canvas;
import labs.lab4.shapes.shape.IShape;
import lombok.val;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        try {
            final List<IShape> shapes = new ArrayList<>();
            val scanner = new Scanner(System.in);
            UserInputParser.parse(shapes, scanner);
            System.out.println("Max area has shape:\n" + UserInputParser.getShapeWithMaxArea(shapes).toString());
            System.out.println("Min perimeter has shape:\n" + UserInputParser.getShapeWithMinPerimeter(shapes).toString());
            EventQueue.invokeLater(() -> {
                val canvas = new Canvas();
                shapes.forEach(shape -> shape.draw(canvas));
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
