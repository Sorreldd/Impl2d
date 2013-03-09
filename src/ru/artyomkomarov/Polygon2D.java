package ru.artyomkomarov;

class Point {
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public interface Polygon2D {
    public Point[] getPoints();
    public double getArea();
    public double getPerimeter();
    public Point getMassCenter();
    public void rotateRelativeToPoint(Point point, double angle);
}

