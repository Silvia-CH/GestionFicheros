package usuario;

import java.time.LocalDate;

import control.Main;
import control.Validaciones;
import objetos.Alumno;

public class RecogerDatos {

	public static Alumno pedirAlumno() {
		String nombre;
		LocalDate fechaNac = null;
		String grupo;

		Alumno usuario;

		nombre = Validaciones.pedirString(Main.azul("Introduce un nombre"));

		fechaNac = Validaciones.pedirFecha(Main.azul("Introduce la fecha de nacimiento:\n"));

		grupo = Validaciones.pedirString(Main.azul("Introduce su grupo"));

		usuario = new Alumno(nombre, fechaNac, grupo);

		return usuario;
	}

}
