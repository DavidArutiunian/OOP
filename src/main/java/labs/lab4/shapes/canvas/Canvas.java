package labs.lab4.shapes.canvas;

import labs.lab4.shapes.point.Point;
import lombok.Value;
import lombok.val;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Canvas extends JPanel implements ICanvas {
    private static final int STROKE_WIDTH = 5;

    private List<CShape> shapes = new ArrayList<>();
    private List<CPolygonShape> polygons = new ArrayList<>();
    private List<CCircleShape> circles = new ArrayList<>();

    public Canvas() throws HeadlessException {
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        initGraphics2D(g2d);
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
        add(new CShape(new Line2D.Double(from.x, from.y, to.x, to.y), lineColor));
    }

    @Override
    public void fillPolygon(List<Point> points, int fillColor) {
        add(new CPolygonShape(points, fillColor));
    }

    @Override
    public void drawCircle(Point center, double radius, int lineColor) {
        add(new CShape(new Ellipse2D.Double(center.x, center.y, radius, radius), lineColor));
    }

    @Override
    public void fillCircle(Point center, double radius, int fillColor) {
        add(new CCircleShape(center, radius, fillColor));
    }

    private void initGraphics2D(final Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
    }

    private void add(final CShape shape) {
        shapes.add(shape);
        repaint();
    }

    private void add(final CPolygonShape shape) {
        polygons.add(shape);
        repaint();
    }

    private void add(final CCircleShape shape) {
        circles.add(shape);
        repaint();
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
