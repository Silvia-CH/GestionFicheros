package control;

import java.time.LocalDate;
import java.time.Month;
import java.util.InputMismatchException;
import java.util.Scanner;

import objetos.Meses;

public class Validaciones {

	public static String pedirString(String enunciado) {
		String texto = null;

		Scanner lector = new Scanner(System.in);
		try {
			do {
				System.out.println(enunciado);
				texto = lector.nextLine();
			} while (texto.isBlank() || texto.isEmpty());
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println(Main.rojo("No es un texto"));
			pedirString(enunciado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return texto;
	}

	public static char pedirChar() {
		String letra = null;

		Scanner lector = new Scanner(System.in);
		try {
			do {
				System.out.print("->");
				letra = lector.nextLine();
			} while (letra.isBlank() || letra.isEmpty());
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println(Main.rojo("No es una letra"));
			pedirChar();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return letra.toUpperCase().charAt(0);
	}

	public static int pedirNumero(String enunciado) {
		int numero = 0;

		Scanner lector = new Scanner(System.in);
		try {
			do {
				System.out.println(enunciado);
				numero = lector.nextInt();
				lector.nextLine();
			} while (numero < 0);
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println(Main.rojo("No es un número válido"));
			pedirNumero(enunciado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return numero;
	}

	public static LocalDate pedirFecha(String enunciado) {
		LocalDate fecha = null;
		int anio = 0;
		int mes = 0;
		int dia = 0;

		Scanner lector = new Scanner(System.in);

		System.out.println(enunciado);
		try {
			do {
				System.out.println(Main.azul("->Introduce el año de nacimiento:"));
				anio = lector.nextInt();
				lector.nextLine();
			} while (anio > LocalDate.now().getYear() || anio < 1900);
			do {
				System.out.println(Main.azul("->Introduce el mes de nacimiento:"));
				mes = lector.nextInt();
				lector.nextLine();
			} while (validarMes(mes));
			do {
				System.out.println(Main.azul("->Introduce el día de nacimiento:"));
				dia = lector.nextInt();
				lector.nextLine();
			} while (dia > Month.of(mes).maxLength() || anio < 1);

			fecha = LocalDate.of(anio, mes, dia);
		} catch (InputMismatchException e) {
			// TODO: handle exception
			System.out.println(Main.rojo("No es una fecha"));
			pedirFecha(enunciado);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return fecha;
	}

	public static boolean validarMes(int mes) {
		boolean correcto = false;

		if (mes > 12 || mes < 1) {
			correcto = true;
		}
		return correcto;

	}

	public static boolean validarMes(String mes) {
		boolean correcto = false;
		Meses[] meses = Meses.values();

		for (int i = 0; i < meses.length; i++) {
			if (meses[i].toString().equals(mes.toUpperCase())) {
				correcto = true;
			}
		}
		return correcto;

	}

}
