package ar.edu.utn.frba.dds.dominio.repositorios;

import ar.edu.utn.frba.dds.dominio.fuentes.Fuente;
import java.util.ArrayList;
import java.util.List;

public class RepositorioFuentes {
  private final List<Fuente> fuentes = new ArrayList<>();
  static RepositorioFuentes INSTANCE = new RepositorioFuentes();
  public static RepositorioFuentes getInstance() {
    return INSTANCE;
  }

  public void registrarFuente(Fuente fuente) {
    fuentes.add(fuente);
  }

  public void eliminarFuente(Fuente fuente) {
    fuentes.remove(fuente);
  }

  public List<Fuente> getFuentes() {
    return new ArrayList<>(fuentes);
  }

  public void actualizarHechos() {
      fuentes.forEach(Fuente::actualizarHechos);
  }

  private RepositorioFuentes(){}

}