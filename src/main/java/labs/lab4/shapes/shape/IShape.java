package labs.lab4.shapes.shape;

import labs.lab4.shapes.canvas.ICanvasDrawable;

public interface IShape extends ICanvasDrawable {
    double getArea();

    double getPerimeter();

    String toString();

    int getOutlineColor();

    void setOutlineColor(final int color);
}
