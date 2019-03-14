package labs.lab4.shapes.canvas;

import labs.lab4.shapes.point.Point;

import java.util.List;

public interface ICanvas {
    void drawLine(final Point from, final Point to, final int lineColor);

    void fillPolygon(final List<Point> points, final int fillColor);

    void drawCircle(final Point center, final double radius, final int lineColor);

    void fillCircle(final Point center, final double radius, final int fillColor);
}
