package labs.lab4.shapes;

import labs.lab4.shapes.canvas.Canvas;
import labs.lab4.shapes.shape.IShape;
import lombok.val;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int FRAME_WIDTH = 1280;
    private static final int FRAME_HEIGHT = 720;
    private static final String FRAME_TITLE = "Shapes 2D";

    public static void main(String... args) {
        try {
            final List<IShape> shapes = new ArrayList<>();
            val parser = new UserInputParser();
            val scanner = new Scanner(System.in);
            parser.parse(shapes, scanner);
            System.out.println("Max area has shape:\n" + parser.getShapeWithMaxArea(shapes).toString());
            System.out.println("Min perimeter has shape:\n" + parser.getShapeWithMinPerimeter(shapes).toString());
            EventQueue.invokeLater(() -> {
                val canvas = new Canvas();
                initUI(canvas);
                draw(shapes, canvas);
            });
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void initUI(Canvas canvas) {
        val frame = new JFrame();
        frame.setTitle(FRAME_TITLE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.add(canvas);
    }

    private static void draw(List<IShape> shapes, Canvas canvas) {
        shapes.forEach(shape -> shape.draw(canvas));
    }
}
