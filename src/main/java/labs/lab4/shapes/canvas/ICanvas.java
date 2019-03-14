package labs.lab4.shapes.canvas;

import labs.lab4.shapes.point.Point;

import java.util.List;

public interface ICanvas {
    void drawLine(Point from, Point to, int lineColor);

    void fillPolygon(List<Point> points, int fillColor);

    void drawCircle(Point center, double radius, int lineColor);

    void fillCircle(Point center, double radius, int fillColor);
}
