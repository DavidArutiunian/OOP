package labs.lab4;

import labs.lab4.shape.IShape;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String... args) {
        try {
            final List<IShape> shapes = new ArrayList<>();
            val scanner = new Scanner(System.in);
            CommandLineParser.parseCommandLine(shapes, scanner);
            System.out.println("Max area has shape:\n" + CommandLineParser.getShapeWithMaxArea(shapes).toString());
            System.out.println("Min perimeter has shape:\n" + CommandLineParser.getShapeWithMinPerimeter(shapes).toString());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
