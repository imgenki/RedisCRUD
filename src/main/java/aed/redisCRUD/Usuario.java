package aed.redisCRUD;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	private StringProperty nombreUsuario = new SimpleStringProperty();
	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty mail = new SimpleStringProperty();
	private StringProperty password = new SimpleStringProperty();
	private StringProperty nacionalidad = new SimpleStringProperty();
	public final StringProperty nombreUsuarioProperty() {
		return this.nombreUsuario;
	}
	
	public final String getNombreUsuario() {
		return this.nombreUsuarioProperty().get();
	}
	
	public final void setNombreUsuario(final String nombreUsuario) {
		this.nombreUsuarioProperty().set(nombreUsuario);
	}
	
	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	
	public final String getNombre() {
		return this.nombreProperty().get();
	}
	
	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	
	public final StringProperty mailProperty() {
		return this.mail;
	}
	
	public final String getMail() {
		return this.mailProperty().get();
	}
	
	public final void setMail(final String mail) {
		this.mailProperty().set(mail);
	}
	
	public final StringProperty passwordProperty() {
		return this.password;
	}
	
	public final String getPassword() {
		return this.passwordProperty().get();
	}
	
	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	public final StringProperty nacionalidadProperty() {
		return this.nacionalidad;
	}
	
	public final String getNacionalidad() {
		return this.nacionalidadProperty().get();
	}
	
	public final void setNacionalidad(final String nacionalidad) {
		this.nacionalidadProperty().set(nacionalidad);
	}
	
	
}
