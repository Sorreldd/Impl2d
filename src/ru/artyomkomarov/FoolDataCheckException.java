package ru.artyomkomarov;

public class FoolDataCheckException extends Exception {
	public void MyThrowing() throws FoolDataCheckException {
		throw new FoolDataCheckException();
	}
}
