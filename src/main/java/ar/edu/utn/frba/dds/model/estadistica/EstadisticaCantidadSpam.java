package ar.edu.utn.frba.dds.model.estadistica;

import ar.edu.utn.frba.dds.model.entities.solicitudes.SolicitudDeEliminacion;
import com.opencsv.CSVWriter;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

public class EstadisticaCantidadSpam implements Estadistica, WithSimplePersistenceUnit {
  int cantidadSpam;

  @Override
  public void calcularEstadistica() {
    List<SolicitudDeEliminacion> solicitudes = entityManager()
        .createQuery("from SolicitudDeEliminacion", SolicitudDeEliminacion.class)
        .getResultList();
    cantidadSpam = solicitudes.size();
  }

  @Override
  public void exportarEstadistica(String path) throws IOException {
    File file = new File(path);

    File parentDir = file.getParentFile();
    if (parentDir != null && !parentDir.exists()) {
      boolean creado = parentDir.mkdirs();
      if (!creado) {
        throw new IOException("No se pudo crear el directorio: " + parentDir.getAbsolutePath());
      }
    }

    // 🔹 Borrar archivo previo
    if (file.exists()) {
      boolean eliminado = file.delete();
      if (!eliminado) {
        System.err.println("⚠️ No se pudo eliminar el archivo existente: " + path);
      }
    }
    try (CSVWriter writer = new CSVWriter(
        new OutputStreamWriter(new FileOutputStream(file, true), StandardCharsets.UTF_8))) {
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
