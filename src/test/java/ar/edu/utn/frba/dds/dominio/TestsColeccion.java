package ar.edu.utn.frba.dds.dominio;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestsColeccion {
  Fuente dataset;
  Fuente datavacio;
  List<Criterio> criterios;
  Criterio rango;
  Criterio titulo;
  @BeforeEach
  public void fixtureColeccion() {
    rango = new CriterioRangoFechas(LocalDate.of(2023, 1, 1),
        LocalDate.of(2025,12,30));
    titulo = new CriterioTitulo("incendio");
    dataset = new FuenteDataSet("datos.csv");
    datavacio = new FuenteDataSet("vacio.csv");
    criterios = new ArrayList<>(Arrays.asList(titulo,rango));
  }

  @Test
  public void importarDesdeDataset() {
    Coleccion coleccion = new Coleccion("incendios forestales",
        "incendios en la patagonia", dataset, criterios);

    List<Hecho> hechos = coleccion.obtenerTodosLosHechos();

    Assertions.assertEquals(2, hechos.size());
  }

  //TODO testear que rompe con archivo vacio
  /*
  @Test
  public void importarDesdeDatasetVacio() {
    Coleccion coleccion = new Coleccion("incendios forestales",
        "incendios en la patagonia", datavacio, criterios);

    List<Hecho> hechos = coleccion.obtenerTodosLosHechos();
  }

   */

  //TODO testear y validar casos excepcionales del archivo csv

  @Test
  public void listaHechosDisponibles() {
    Coleccion coleccion = new Coleccion("incendios forestales",
        "incendios en la patagonia", dataset, criterios);

    List<Hecho> hechos = coleccion.listarHechosDisponibles();

    Assertions.assertEquals(2, hechos.size());
  }
}
