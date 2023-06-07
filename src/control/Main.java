package control;

import conexion.Fichero;

public class Main {
	public static final String blue = "\u001B[34m";
	public static final String red = "\u001B[31m";
	public static final String green = "\u001B[32m";
	public static final String reset = "\u001B[0m";

	public static void main(String[] args) {

		Fichero miFichero = new Fichero("datos/alumnos.txt");

		System.out.println("MODIFICACIÓN FICHERO");
		Menu.elegir(miFichero);

	}

	public static String azul(String texto) {
		return blue + texto + reset;
	}

	public static String rojo(String texto) {
		return red + texto + reset;
	}

	public static String verde(String texto) {
		return green + texto + reset;
	}

}