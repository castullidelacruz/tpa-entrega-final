package ar.edu.utn.frba.dds.dominio.estadistica;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import ar.edu.utn.frba.dds.dominio.Hecho;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class EstadisticaCategoriaMaxima implements Estadistica, WithSimplePersistenceUnit {
  private String categoriaMax;


  @Override public void calcularEstadistica() {
    List<Hecho> hechos = entityManager()
        .createQuery("from Hecho", Hecho.class).getResultList();

    this.categoriaMax = hechos.stream()
        .map(Hecho::getCategoria)
        .collect(Collectors.toMap(
            c -> c,
            c -> 1L,
            Long::sum
        ))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null);
  }

  @Override
  public void exportarEstadistica(String path) throws IOException {
    java.io.File file = new java.io.File(path);

    try (CSVWriter writer = new CSVWriter(new FileWriter(file, true))) {
      String[] header = {"Fecha", "CategoriaMasFrecuente"};
      String[] data = {LocalDateTime.now().toString(), categoriaMax != null ? categoriaMax : "N/A"};

      // Escribir encabezado solo si el archivo está vacío
      if (file.length() == 0) {
        writer.writeNext(header);
      }
      writer.writeNext(data);
    }
  }

  public String getCategoriaMax() {
    return categoriaMax;
  }

}


