package control;

import java.util.ArrayList;

import conexion.Fichero;
import objetos.Alumno;
import usuario.RecogerDatos;

public class Menu {

	public static void elegir(Fichero fichero) {
		boolean continuar = true;
		boolean repetir = false;

		do {
			System.out.println(Main
					.azul("\n¿QUÉ QUIERES HACER? \n" + "1: Añadir Alumno\n" + "2: Mostrar Fichero\n\n"
							+ "A: ¿Quien cumple en X mes? (por nombre o numero)\n" + "B: Mayor y menor\n"
							+ "C: Todos los alumnos >18\n" + "D: Alumnos Rango edades(ej: 18-22)\n"
							+ "E: Lista ordenada por edad (menor a mayor)\n" + "F: Edad media\n\n")
					+ Main.rojo("Z: Terminar proceso\n"));
			switch (Validaciones.pedirChar()) {
			case '1':
				System.out.println(Main.verde("1: Añadir Alumno\n"));
				fichero.escribirFichero(RecogerDatos.pedirAlumno());
				break;
			case '2':
				System.out.println(Main.verde("2: Mostrar Fichero\n"));
				mostrarLista(fichero.getLista());
				break;

			case 'A':
				System.out.println(Main.verde("A: ¿Quien cumple en X mes? (por nombre o numero)\n"));
				do {
					System.out.println(Main.azul("¿Como vas a elegir el mes?\n -A: Nombre\n -B: Número\n")
							+ Main.rojo("Z: Terminar proceso\n"));
					switch (Validaciones.pedirChar()) {
					case 'A':
						mostrarLista(fichero.buscarPorMes(Validaciones.pedirString(Main.azul("Introduzca un mes:"))));
						repetir = false;
						break;
					case 'B':
						mostrarLista(fichero.buscarPorMes(Validaciones.pedirNumero(Main.azul("Introduzca un mes:"))));
						repetir = false;
						break;
					case 'Z':
						repetir = false;
						break;
					default:
						System.out.println(Main.rojo("Esta opción no existe"));
						repetir = true;
					}
				} while (repetir);
				break;
			case 'B':
				System.out.println(Main.verde("B: Mayor y menor\n"));
				fichero.mayorAndMenor();
				break;
			case 'C':
				System.out.println(Main.verde("C: Todos los alumnos >18\n"));
				mostrarLista(fichero.mayorDeEdad());
				break;
			case 'D':
				System.out.println(Main.verde("D: Alumnos Rango edades(ej: 18-22)\n"));
				mostrarLista(fichero.edadEntreRangos(Validaciones.pedirNumero("Introduzca el primer número del rango:"),
						Validaciones.pedirNumero("Introduzca segundo número del rango:")));
				break;
			case 'E':
				System.out.println(Main.verde("E: Lista ordenada por edad (menor a mayor)\n"));
				mostrarLista(fichero.ordenarPorEdad());
				break;
			case 'F':
				System.out.println(Main.verde("F: Edad media\n"));
				System.out.println("La edad media es de " + fichero.edadMedia() + " años.");
				break;

			case 'Z':
				System.out.println("Gracias por utilizar este programa :-)");
				continuar = false;
				break;

			default:
				System.out.println(Main.rojo("Esta opción no existe"));
			}
		} while (continuar);
	}

	private static void mostrarLista(ArrayList<Alumno> listado) {
		listado.forEach(alumno -> System.out.println(alumno));

		if (listado.isEmpty()) {
			System.out.println(Main.rojo("No hay elementos"));
		}
	}

}