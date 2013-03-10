package ru.artyomkomarov;

public class FoolDataCheckException extends Exception { // Мой cозданный класс Exception
	public void MyThrowing() throws FoolDataCheckException { // метод для выброса Exception
		throw new FoolDataCheckException(); //бросаем наш Exeption
	}
}
