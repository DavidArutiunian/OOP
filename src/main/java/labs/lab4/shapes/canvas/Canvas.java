package labs.lab4.shapes.canvas;

import labs.lab4.shapes.point.Point;
import lombok.Value;
import lombok.val;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Canvas implements ICanvas, ICanvasPanelDrawable {
    private List<CShape> shapes = new ArrayList<>();
    private List<CPolygonShape> polygons = new ArrayList<>();
    private List<CCircleShape> circles = new ArrayList<>();

    public void draw(Graphics2D g2d) {
        shapes.forEach(shape -> {
            g2d.setPaint(getHexColor(shape.getColor()));
            g2d.draw(shape.getShape());
        });
        polygons.forEach(polygon -> {
            g2d.setColor(getHexColor(polygon.getColor()));
            val path = new Path2D.Double();
            val points = polygon.getPoints();
            path.moveTo(points.get(0).x, points.get(0).y);
            points.stream().skip(1).forEach(point -> path.lineTo(point.x, point.y));
            path.closePath();
            g2d.fill(path);
        });
        circles.forEach(circle -> {
            g2d.setColor(getHexColor(circle.getColor()));
            val center = circle.getCenter();
            val radius = circle.getRadius();
            g2d.fill(new Ellipse2D.Double(center.x, center.y, radius, radius));
        });
    }

    @Override
    public void drawLine(Point from, Point to, int lineColor) {
        shapes.add(new CShape(new Line2D.Double(from.x, from.y, to.x, to.y), lineColor));
    }

    @Override
    public void fillPolygon(List<Point> points, int fillColor) {
        polygons.add(new CPolygonShape(points, fillColor));
    }

    @Override
    public void drawCircle(Point center, double radius, int lineColor) {
        shapes.add(new CShape(new Ellipse2D.Double(center.x, center.y, radius, radius), lineColor));
    }

    @Override
    public void fillCircle(Point center, double radius, int fillColor) {
        circles.add(new CCircleShape(center, radius, fillColor));
    }

    private Color getHexColor(int color) {
        return Color.decode('#' + Integer.toHexString(color));
    }

    @Value
    private class CCircleShape {
        private final Point center;
        private final double radius;
        private final int color;
    }

    @Value
    private class CPolygonShape {
        private final List<Point> points;
        private final int color;
    }

    @Value
    private class CShape {
        private final Shape shape;
        private final int color;
    }
}
