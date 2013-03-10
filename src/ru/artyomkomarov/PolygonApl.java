package ru.artyomkomarov;


import java.awt.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class PolygonApl {
	static FoolDataCheckException ex = new FoolDataCheckException();
	public static void throwExcep() {
		try {
			ex.MyThrowing();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean cw (Point a, Point b, Point c) {
		return a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y) < 0;
	}

	public static boolean ccw (Point a, Point b, Point c) {
		return a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y) > 0;
	}
	
	public static Point[] Graham(int n, Point[] a) {
		Arrays.sort(a);
		Point p1 = a[0], p2 = a[n - 1];
		ArrayList<Point> up = new ArrayList<Point>();
		ArrayList<Point> down = new ArrayList<Point>();
		up.add(p1);
		down.add(p1);
		for(int i = 1; i < n; i++) {
			if (i == n-1 || cw (p1, a[i], p2)) {
				while (up.size() >= 2 && !cw (up.get(up.size()-2), up.get(up.size()-1), a[i]))
					up.remove(up.size() - 1);
				up.add(a[i]);
			}
			if (i == n - 1 || ccw (p1, a[i], p2)) {
				while(down.size()>=2 && !ccw (down.get(down.size()-2), down.get(down.size()-1), a[i]))
					down.remove(down.size() - 1);
				down.add(a[i]);
			}
		}
		int k = 0;
		Point[] res = new Point[n];
		for(int i = 0; i < up.size(); i++) {
			res[k] = up.get(i);
			k++;
		}
		for(int i = down.size() - 2; i > 0; i--) {
			res[k] = down.get(i);
			k++;
		}
		if(k != n) throwExcep();
		return res;
		
	}
	
	public static void main(String[] args) {
		int n;
		double[] u;
		double[] v;
		args = new String[]{"3", "1", "1", "2", "2", "1", "2"};
		Point[] mn, kk;
		Polygon2DImpl qq = null;
		Polygon2D poly = null;
		n = Integer.parseInt(args[0]);
		mn = new Point[n];
		u = new double[n];
		v = new double[n];
		if(n != args.length / 2 || n < 3) throwExcep();
		for(int i = 0; i < n; i++) {
			u[i] = (double)Double.parseDouble(args[2 * i + 1]);
			v[i] = (double)Double.parseDouble(args[2 * i + 2]);
			mn[i] = new Point(u[i], v[i]);
		}
		
		mn = Graham(n, mn);
		for(int i = 0; i < n; i++) {
			System.out.println(mn[i].x + " " + mn[i].y);
		}
		/*
		 	poly = new Polygon2DImpl(n, mn);
			poly = new Polygon2DImpl(n, u, v);
		*/

	}

}