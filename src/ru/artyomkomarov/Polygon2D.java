package ru.artyomkomarov;

class Point implements Comparable { // имплементим Comparable для перезагрузки метода compareTo
    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
	@Override
	public int compareTo(Object obj) { // перезагружаем этот метод, для того, чтобы мы могли сортировать экземпляры данного класса
		Point tmp = (Point)obj;
		if(this.x < tmp.x) return -1;
		if(this.x == tmp.x && this.y < tmp.y) return -1;
		return 1;
	}
}

public interface Polygon2D {
    public Point[] getPoints();
    public double getArea();
    public double getPerimeter();
    public Point getMassCenter();
    public void rotateRelativeToPoint(Point point, double angle);
}

