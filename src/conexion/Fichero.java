package conexion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import control.Validaciones;
import objetos.Alumno;
import objetos.Meses;

public class Fichero {

	private String ruta;
	private ArrayList<Alumno> lista = new ArrayList<Alumno>();

	public Fichero(String ruta) {
		super();
		this.ruta = ruta;
		this.lista = devolverFichero();
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public ArrayList<Alumno> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Alumno> lista) {
		this.lista = lista;
	}

	@Override
	public String toString() {
		return "Fichero [ruta=" + ruta + "]";
	}

	// -----------------------MÉTODOS-----------------------

	// LEER FICHERO
	@SuppressWarnings("finally")
	public ArrayList<Alumno> devolverFichero() {

		FileReader fichero = null;
		BufferedReader lector = null;
		String cadena;
		ArrayList<Alumno> listado = new ArrayList<Alumno>();

		Alumno alumno = null;
		String nombre = null;
		String fechaTemp = null;
		LocalDate fechaNac = null;
		String grupo = null;

		try {
			fichero = new FileReader(ruta);
			lector = new BufferedReader(fichero);

			while ((cadena = lector.readLine()) != null) {

				nombre = cadena.substring(0, cadena.indexOf(","));

				fechaTemp = cadena.substring(cadena.indexOf(",") + 1, cadena.lastIndexOf(","));
				fechaNac = LocalDate.of(Integer.parseInt(fechaTemp.substring(0, 4)),
						Integer.parseInt(fechaTemp.substring(5, 7)), Integer.parseInt(fechaTemp.substring(8)));

				grupo = cadena.substring(cadena.lastIndexOf(",") + 1);

				alumno = new Alumno(nombre, fechaNac, grupo);
				listado.add(alumno);
			}

		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error inesperado");
			e.printStackTrace();
		} finally {
			try {
				if (lector != null) {
					lector.close();
				}
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
				e.printStackTrace();
			}
			return listado;

		}

	}

	// INSERT EN FICHERO
	public void escribirFichero(Alumno alumno) {
		FileWriter guardar = null;
		FileReader fichero = null;
		BufferedReader lector = null;

		try {
			guardar = new FileWriter(ruta, true);
			fichero = new FileReader(ruta);
			lector = new BufferedReader(fichero);

			guardar.write(alumno.getNombre() + "," + alumno.getFechaNac() + "," + alumno.getGrupo() + (char) 13);

			lista.add(alumno);
		} catch (FileNotFoundException e) {
			System.out.println("No se encuentra el fichero");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error de entrada/salida");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error inesperado");
			e.printStackTrace();
		} finally {
			try {
				if (guardar != null) {
					guardar.close();
				}
				if (lector != null) {
					lector.close();
				}
				if (fichero != null) {
					fichero.close();
				}

			} catch (IOException e) {
				System.out.println("Error al cerrar el fichero");
				e.printStackTrace();

			}

		}

	}

	// 1. ¿Quien cumple en X mes? (por nombre o numero)
	public ArrayList<Alumno> buscarPorMes(int mes) {
		ArrayList<Alumno> listaTemp = new ArrayList<Alumno>();

		if (!Validaciones.validarMes(mes)) {
			lista.stream().filter(alumno -> alumno.getFechaNac().getMonthValue() == mes)
					.forEach(alumno -> listaTemp.add(alumno));
		}

		return listaTemp;

	}

	public ArrayList<Alumno> buscarPorMes(String mes) {
		ArrayList<Alumno> listaTemp = new ArrayList<Alumno>();

		if (Validaciones.validarMes(mes)) {
			lista.stream().filter(
					alumno -> alumno.getFechaNac().getMonthValue() == Meses.valueOf(mes.toUpperCase()).ordinal() + 1)
					.forEach(alumno -> listaTemp.add(alumno));
		}

		return listaTemp;

	}

	// 2. Mayor y menor
	public void mayorAndMenor() {
		Alumno menorAlumno = menorEdad();
		Alumno mayorAlumno = mayorEdad();

		System.out.println(
				"El alumno más viejo es " + mayorAlumno.getNombre() + " con " + mayorAlumno.getEdad() + " años.");
		System.out.println(
				"El alumno más joven es " + menorAlumno.getNombre() + " con " + menorAlumno.getEdad() + " años.");

	}

	private Alumno menorEdad() {
		int menor = Integer.MAX_VALUE;
		Alumno menorAlumno = null;
		for (Alumno alumno : lista) {
			if (alumno.getEdad() < menor) {
				menor = alumno.getEdad();
				menorAlumno = null;
				menorAlumno = alumno;
			}
		}
		return menorAlumno;
	}

	private Alumno mayorEdad() {
		int mayor = Integer.MIN_VALUE;
		Alumno mayorAlumno = null;
		for (Alumno alumno : lista) {
			if (alumno.getEdad() > mayor) {
				mayor = alumno.getEdad();
				mayorAlumno = null;
				mayorAlumno = alumno;
			}
		}
		return mayorAlumno;
	}

	// 3. Todos los alumnos >18
	public ArrayList<Alumno> mayorDeEdad() {
		ArrayList<Alumno> listaTemp = new ArrayList<Alumno>();

		lista.stream().filter(alumno -> alumno.getEdad() >= 18).forEach(alumno -> listaTemp.add(alumno));

		return listaTemp;

	}

	// 4. Alumnos Rango edades(ej: 18-22)
	public ArrayList<Alumno> edadEntreRangos(int start, int end) {
		ArrayList<Alumno> listaTemp = new ArrayList<Alumno>();
		int temp;

		if (end < start) {
			temp = start;
			start = end;
			end = temp;
		}

		for (Alumno alumno : lista) {
			if (alumno.getEdad() >= start && alumno.getEdad() <= end)
				listaTemp.add(alumno);
		}

		return listaTemp;

	}

	// 5. Lista ordenada por edad (menor a mayor)
	public ArrayList<Alumno> ordenarPorEdad() {
		ArrayList<Alumno> listaTemp = new ArrayList<Alumno>();
		int[] edades = new int[lista.size()];
		int contador = 0;

		for (Alumno alumno : lista) {
			edades[contador] = alumno.getEdad();
			contador++;
		}

		// menor a mayor
		Arrays.sort(edades);

		for (int i = 0; i < edades.length; i++) {
			for (Alumno alumno : lista) {
				if (alumno.getEdad() == edades[i]) {
					listaTemp.add(alumno);
				}
			}
		}

		return listaTemp;

	}

	// 6. Edad media
	public float edadMedia() {
		float suma = 0;
		int contador = 0;
		float total = 0;

		for (Alumno alumno : lista) {
			suma += alumno.getEdad();
			contador++;
		}

		total = suma / contador;
		return total;
	}

}
