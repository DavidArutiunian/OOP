package labs.lab4.shapes.canvas;

import labs.lab4.shapes.point.CPoint;

import java.util.List;

public interface ICanvas {
    void drawLine(final CPoint from, final CPoint to, final int lineColor);

    void fillPolygon(final List<CPoint> points, final int fillColor);

    void drawCircle(final CPoint center, final double radius, final int lineColor);

    void fillCircle(final CPoint center, final double radius, final int fillColor);
}
