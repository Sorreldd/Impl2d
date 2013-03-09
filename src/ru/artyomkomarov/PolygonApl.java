package ru.artyomkomarov;

import java.util.Scanner;

public class PolygonApl {
	public static void main(String[] args) {
		int n;
		double[] u;
		double[] v;
		Point[] mn, kk;
		Polygon2DImpl qq = null;
		Polygon2D poly = null;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		mn = new Point[n];
		u = new double[n];
		v = new double[n];
		
		for(int i = 0; i < n; i++) {
			u[i] = sc.nextDouble();
			v[i] = sc.nextDouble();
			mn[i] = new Point(u[i], v[i]);
		}
		//poly = new Polygon2DImpl(n, mn);
		poly = new Polygon2DImpl(n, u, v);
		
		kk = poly.getPoints();
		
		for(int i = 0; i < n; i++) {
			System.out.println(kk[i].x + " " + kk[i].y);
		}
		
	}
}