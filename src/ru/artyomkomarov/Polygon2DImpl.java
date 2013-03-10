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
		double a, b, c, pp, ans = 0;
		for(int i = 2; i < n; i++) {
			a = dist(0, i);
			b = dist(0, i - 1);
			c = dist(i, i - 1);
			pp = (a + b + c) / 2;
			ans += Math.sqrt(pp * (pp - a) * (pp - b) * (pp - c));
		}
		return ans;
	}

	@Override
	public double getPerimeter() {
		double perim = 0;
		for(int i = 0; i < n - 1; i++) {
			perim += dist(i, i + 1);
		}
		perim += dist(0, n - 1);		
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
	public void rotateRelativeToPoint(Point p0, double angle) {
		double X, Y;
		for(int i = 0; i < n; i++) {
			X = p0.x + (p[i].x - p0.x) * Math.cos(angle) - (p[i].y - p0.y) * Math.sin(angle);
			Y = p0.y + (p[i].y - p0.y) * Math.cos(angle) + (p[i].x - p0.x) * Math.sin(angle); 
			p[i] = new Point(X, Y);
		}
	}

}
