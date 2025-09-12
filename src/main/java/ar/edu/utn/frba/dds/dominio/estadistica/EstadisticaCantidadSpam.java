package ar.edu.utn.frba.dds.dominio.estadistica;

import ar.edu.utn.frba.dds.dominio.Hecho;
import ar.edu.utn.frba.dds.dominio.solicitudes.SolicitudDeEliminacion;
import com.opencsv.CSVWriter;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class EstadisticaCantidadSpam implements Estadistica, WithSimplePersistenceUnit {
   int cantidadSpam;


  @Override public void calcularEstadistica() {
    List<SolicitudDeEliminacion> solicitudes = entityManager()
        .createQuery("from SolicitudDeEliminacion", SolicitudDeEliminacion.class)
        .getResultList();

    cantidadSpam = solicitudes.size();
  }

  @Override public void exportarEstadistica(String path) throws IOException {
    java.io.File file = new java.io.File(path);
    try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
      String[] header = {"Fecha", "CantidadSpam"};
      String[] data = {LocalDateTime.now().toString(), String.valueOf(cantidadSpam)};

      if (file.length() == 0) {
        writer.writeNext(header);
      }
      writer.writeNext(data);
    }
  }

  public int getCantidadSpam() {
    return cantidadSpam;
  }
}
