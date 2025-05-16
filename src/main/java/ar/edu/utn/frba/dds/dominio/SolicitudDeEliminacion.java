package ar.edu.utn.frba.dds.dominio;

import static java.util.Objects.requireNonNull;

public class SolicitudDeEliminacion {
  private Hecho hecho;
  private String motivo;
  private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;

  public SolicitudDeEliminacion(Hecho hecho, String motivo) {
    this.hecho = requireNonNull(hecho);
    if (motivo.length() > 500) {
      throw new RuntimeException("El motivo es demasiado extenso.");
    }
    this.motivo = requireNonNull(motivo);
    RepositorioSolicitudes.agregarSolicitud(this);
  }

  //el usuario establece si acepta o rechaza la solicitud
  //si la acepta se setea el estado del hecho en no disponible
  public void evaluar(EstadoSolicitud evaluacion) {
    this.estado = evaluacion;
    if (estado == EstadoSolicitud.ACEPTADA) {
      hecho.setDisponibilidad(false);
    }
  }


}
