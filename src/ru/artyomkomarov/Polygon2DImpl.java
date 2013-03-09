package ru.artyomkomarov;

public class Polygon2DImpl implements Polygon2D {
	private int n;
	private Point[] p;
	
	private double dist(int i, int j) {
		return Math.sqrt((p[i].x - p[j].x) * (p[i].x - p[j].x) + (p[i].y - p[j].y) * (p[i].y - p[j].y));
	}
	public Polygon2DImpl(int n, Point[] p) {
		this.n = n;
		this.p = p;
	}
	public Polygon2DImpl(int n, double[] u, double[] v) {
		this.n = n;
		p = new Point[n];
		for(int i = 0; i < n; i++) {
			p[i] = new Point(u[i], v[i]);
		}
	}
	@Override
	public Point[] getPoints() {
		return p;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPerimeter() {
		double perim = 0;
		for(int i = 0; i < n - 1; i++) {
			perim += dist(i, i + 1);
		}
		return perim;
	}

	@Override
	public Point getMassCenter() {
		Point massCen = new Point(0, 0);
		for(int i = 0; i < n; i++) {
			massCen.x += p[i].x;
			massCen.y += p[i].y;
		}
		massCen.x = massCen.x / n;
		massCen.y = massCen.y / n;
		return massCen;
	}

	@Override
	public void rotateRelativeToPoint(Point point, double angle) {
		// TODO Auto-generated method stub
		
	}

}
