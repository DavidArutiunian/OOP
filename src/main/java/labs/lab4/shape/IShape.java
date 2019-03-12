package labs.lab4.shape;

public interface IShape {
    double getArea();

    double getPerimeter();

    String toString();

    int getOutlineColor();

    void setOutlineColor(final int color);
}
