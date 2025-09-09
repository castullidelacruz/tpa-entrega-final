package ar.edu.utn.frba.dds.dominio.solicitudes;

import static java.util.Objects.requireNonNull;

import ar.edu.utn.frba.dds.dominio.Hecho;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Entity;
import javax.persistence.Transient;


@Entity
@DiscriminatorValue("CARGA")
public class SolicitudDeCarga extends Solicitud {
  @Transient
  private Hecho hecho;
  @Column
  private boolean registrado;
  @Column
  private String sugerencia = "";
  @Enumerated(EnumType.STRING)
  private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

  public SolicitudDeCarga(Hecho hecho, boolean registerBoolean) {
    this.hecho = requireNonNull(hecho);
    this.registrado = registerBoolean;
  }

  public SolicitudDeCarga() {
  }

  public EstadoSolicitud getEstado() {
    return estado;
  }

  public String getSugerencia() {
    return sugerencia;
  }

  public void setEstado(EstadoSolicitud estado) {
    this.estado = estado;
  }

  public void setSugerencia(String s) {
    this.sugerencia = s;
  }

  //unicamente para test
  public void setFechaCargaOriginal(LocalDate fechaCargaMock) {
    this.hecho.setFechaDeCarga(fechaCargaMock);
  }

  public void aprobar() {
    if (estado.equals(EstadoSolicitud.ACEPTADA)) {

      throw new IllegalStateException("La solicitud ya fue evaluada.");
    } else {

      this.hecho.setFechaDeCarga(LocalDate.now());
      this.estado = EstadoSolicitud.ACEPTADA;
      //cuando pensemos en la persistencia de hechos modificados
      //por trazabilidad aca podria ser guardado el hecho original
      //borre la carga al repo, deberiamos pensar como funcionaria ahora
    }
  }

  public void rechazar() {
    this.estado = EstadoSolicitud.RECHAZADA;
  }

  public void sugerir(String sugerencia) {
    this.sugerencia = sugerencia;
  }

  public void cambiarEstado(EstadoSolicitud evaluacion) {
  }

  public boolean puedeModificar() {
    if (estado.equals(EstadoSolicitud.ACEPTADA) && registrado
        && (ChronoUnit.DAYS.between(hecho.getFechaDeCarga(), LocalDate.now())) <= 7) {
      return true;
    } else {
      throw new RuntimeException("No se puede modificar este hecho");
    }
  }
  /*
  public Hecho encontrarHecho() {
    if (puedeModificar()) {
      for (Hecho h : repositorioH.obtenerTodos()) {
        if (h.getTitulo().equals(this.titulo)
            && h.getDescripcion().equals(this.descripcion)
            && h.getCategoria().equals(this.categoria)
            && h.getLatitud().equals(this.latitud)
            && h.getLongitud().equals(this.longitud)
            && h.getFechaAcontecimiento().equals(this.fechaAcontecimiento)
            && h.getFechaDeCarga().equals(this.fechaCargaOriginal)
            && h.getOrigen().equals(this.origen)
            && h.getMultimedia().equals(this.multimedia)
            && h.getDisponibilidad().equals(this.disponibilidad)
        ) {
          return h;
        }
      }
    }
    throw new RuntimeException("No se puede modificar este hecho");
  }

   */

  public void modificarHecho(Hecho h) {
    //repensar este metodo
    //Hecho original = encontrarHecho();
    //original.modificar(h);
  }


}
