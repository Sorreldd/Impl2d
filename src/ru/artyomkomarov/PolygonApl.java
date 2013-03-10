package ru.artyomkomarov;

import java.util.ArrayList;
import java.util.Arrays;


public class PolygonApl {
	static FoolDataCheckException ex = new FoolDataCheckException(); // экземпляр класса который бросает мой Exception
////////////////////////////////////////////////////////////////////////////////////////////	
	public static void throwExcep() { // метод который я вызываю когда нужно кинуть Exception
		try {
			ex.MyThrowing();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////
	// методы проверки правого и левого поворота, ориентированная площадь
	public static boolean cw (Point a, Point b, Point c) { 
		return a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y) < 0;
	}
	public static boolean ccw (Point a, Point b, Point c) {
		return a.x*(b.y-c.y)+b.x*(c.y-a.y)+c.x*(a.y-b.y) > 0;
	}
////////////////////////////////////////////////////////////////////////////////////////////	
	//Построение выпуклой оболочки алгоритмом Грэхэма, на случай если точки задаются не в порядке обхода
	public static Point[] Graham(int n, Point[] a) {
		Arrays.sort(a); //Сортируем точки, это возможно, так как классу Point имплементим Comparable и перезагружаем compareTo
		/*
		 * Дальше Грэхэм, краткое описание, две точки самую левую нижнюю и самую правую верхнюю
		 * проводим через них прямую, оставшиеся точки разбились на два множества, 
		 * для верхнего и нижнего множества строим выпуклые оболочки потом сливаем их воедино.
		 * Чтобы получить, верхнюю оболочку, нужно отсортировать все точки по абсциссе, затем пройтись по всем точкам, 
		 * рассматривая на каждом шаге саму точку и две предыдущие точки, вошедшие в оболочку. 
		 * Если текущая тройка точек образует не правый поворот, то ближайшего соседа нужно удалить из оболочки. 
		 * В конце, останутся только точки, входящие в выпуклую оболочку. 
		 * Анологично для нижнего множества, но поворот левый
		 */
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
		if(k != n) throwExcep(); // если не удалось построить оболочку ровно из n точек, значит многоугольник не выпуклый или есть совпадающие точки, в любом случае Exception
		return res; // Возвращаем нашу построенную оболочку
		
	}
////////////////////////////////////////////////////////////////////////////////////////////	
	public static void main(String[] args) {
		int n;
		double[] u;
		double[] v;
		args = new String[]{"3", "1", "1", "2", "2", "1", "2"}; // наши входные данные, количество точек, затем точки парой чисел
		//args = new String[]{"3", "1", "1", "1", "1", "1", "2"}; // альтернатива, словим кучу Exception
		Point[] mn;
		Polygon2D poly = null;
		//ниже парсим наши входные данные и создаем необходимые массивы
		n = Integer.parseInt(args[0]);
		mn = new Point[n];
		u = new double[n];
		v = new double[n];
		if(n != args.length / 2 || n < 3) throwExcep(); // если во входных данных нам наврали или ввели недопустимое количество, кидаем Exception
		for(int i = 0; i < n; i++) {
			u[i] = (double)Double.parseDouble(args[2 * i + 1]);
			v[i] = (double)Double.parseDouble(args[2 * i + 2]);
			mn[i] = new Point(u[i], v[i]);
		}
		
		mn = Graham(n, mn); // строим выпуклую оболочку
		for(int i = 0; i < n; i++) {
			u[i] = mn[i].x; // разбиваем массив точек на два массива u - координаты x
			v[i] = mn[i].y; // разбиваем массив точек на два массива v - координаты y
		}
		poly = new Polygon2DImpl(n, mn); // создаем наш полигон
		//poly = new Polygon2DImpl(n, u, v); // 2-рой вариант создания полигона через другой конструктор
		System.out.println(poly.getArea()); // выводим площадь
		System.out.println(poly.getPerimeter()); // выводим периметр
		System.out.println(poly.getMassCenter().x + " " + poly.getMassCenter().y); // выводим центр масс
		
		//poly.rotateRelativeToPoint(new Point(0, 0), 1); //можем повращать полигон вокруг какой-то точки
		
		mn = poly.getPoints(); // получаем наш полигон
		for(int i = 0; i < n; i++) { // выводим полигон
			System.out.println(mn[i].x + " " + mn[i].y);
		}
	}

}