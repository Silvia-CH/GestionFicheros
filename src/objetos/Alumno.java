package objetos;

import java.time.LocalDate;

public class Alumno {

	private String nombre;
	private LocalDate fechaNac;
	private int edad;
	private String grupo;

	public Alumno(String nombre, LocalDate fechaNac, String grupo) {
		super();
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.edad = getEdad(fechaNac);
		this.grupo = grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", fechaNac=" + fechaNac + ", edad=" + edad + ", grupo=" + grupo + "]";
	}

	public int getEdad(LocalDate fechaNac) {
		int edad = 0;
		LocalDate ahora = LocalDate.now();

		edad = ahora.getYear() - fechaNac.getYear();
		if (ahora.getMonthValue() < fechaNac.getMonthValue()) {
			if (ahora.getDayOfMonth() < fechaNac.getDayOfMonth()) {
				edad = edad - 1;
			}
		}

		return edad;
	}

}
