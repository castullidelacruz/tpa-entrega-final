package ar.edu.utn.frba.dds.dominio;

public class SolicitudDeCarga{
  private Hecho hecho;
  private boolean registrado;
  private String sugerencia = "";
  private EstadoSolicitud estado = EstadoSolicitud.PENDIENTE;
  private RepositorioHechos repositorioH;
  private RepositorioSolicitudes repositorioS;

  public SolicitudDeCarga(Hecho h, RepositorioHechos rh,RepositorioSolicitudes rs) {
    this.hecho = h;
    this.repositorioH = rh;
    this.repositorioS = rs;
    repositorioS.agregarSolicitudDeCarga(this);
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

  public void evaluarSolicitud(EstadoSolicitud evaluacion) {
    if (!estado.equals(EstadoSolicitud.PENDIENTE)) {
      throw new IllegalStateException("La solicitud ya fue evaluada.");
    }

    this.estado = evaluacion;
    if (estado.equals(EstadoSolicitud.ACEPTADA)) {
      repositorioH.CargarHecho(hecho);
    }

  }

  //public void modificarHecho(Hecho hechoModificado) {
  //  hecho.modificar(hechoModificado);
  //}

}
