package ar.edu.utn.frba.dds.dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioSolicitudes {
  private static List<SolicitudDeEliminacion> solicitudesPrendientes =
      new ArrayList<>();

  public static List<SolicitudDeEliminacion> getSolicitudes() {
    return new ArrayList<>(solicitudesPrendientes);
  }


  public static void agregarSolicitud(SolicitudDeEliminacion solicitud) {
    List<SolicitudDeEliminacion> lista = new ArrayList<>(solicitudesPrendientes);
    lista.add(solicitud);
    solicitudesPrendientes = new ArrayList<>(lista);
  }

  public static List<SolicitudDeEliminacion> obtenerSolicitudesPendientes() {
    return new ArrayList<>(solicitudesPrendientes);
  }


}
