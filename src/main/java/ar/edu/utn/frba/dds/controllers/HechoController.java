package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.model.entities.fuentes.Fuente;
import ar.edu.utn.frba.dds.model.entities.fuentes.FuenteDinamica;
import ar.edu.utn.frba.dds.model.entities.solicitudes.SolicitudDeCarga;
import ar.edu.utn.frba.dds.repositories.RepositorioFuentes;
import ar.edu.utn.frba.dds.repositories.RepositorioSolicitudesDeCarga;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import io.javalin.http.Context;
import org.apache.avro.reflect.Nullable;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HechoController implements WithSimplePersistenceUnit {

  private final RepositorioSolicitudesDeCarga repoSolicitudes;
  private final RepositorioFuentes repoFuentes;

  private static final Map<String, Long> CONTEXTO_A_FUENTE = Map.of(
      "anonimo", 1L,
      "registrado", 2L
  );


  public HechoController() {
    this.repoSolicitudes = RepositorioSolicitudesDeCarga.getInstance();
    this.repoFuentes = RepositorioFuentes.getInstance();
  }

  // --- Mostrar formulario de creación de hecho ---
  public Map<String, Object> showCreationForm(@NotNull Context ctx) {
    boolean esRegistrado = ctx.sessionAttribute("usuarioRegistrado") != null;
    String contexto = esRegistrado ? "registrado" : "anonimo";

    Long fuenteId = CONTEXTO_A_FUENTE.get(contexto);
    Fuente fuente = repoFuentes.getFuente(fuenteId);

    if (fuente == null) {
      ctx.status(500);
      return Map.of(
          "titulo", "Error interno",
          "mensaje", "No se encontró la fuente configurada para el contexto: " + contexto
      );
    }

    List<String> categorias = List.of("Incendio", "Accidente Vial", "Contaminación", "Otro");

    return Map.of(
        "titulo", "Cargar Nuevo Hecho",
        "categorias", categorias,
        "fuente", fuente,
        "esRegistrado", esRegistrado
    );
  }

  public void create(@NotNull Context ctx) {
    boolean esRegistrado = ctx.sessionAttribute("usuarioRegistrado") != null;
    String contexto = esRegistrado ? "registrado" : "anonimo";
    Long fuenteId = CONTEXTO_A_FUENTE.get(contexto);

    try {
      String titulo = ctx.formParam("titulo");
      String descripcion = ctx.formParam("descripcion");
      String categoria = ctx.formParam("categoria");
      String fechaAcontecimientoStr = ctx.formParam("fechaAcontecimiento");
      String multimedia = ctx.formParam("multimedia");

      Double latitud = Double.parseDouble(ctx.formParam("latitud"));
      Double longitud = Double.parseDouble(ctx.formParam("longitud"));
      LocalDateTime fechaAcontecimiento = LocalDateTime.parse(fechaAcontecimientoStr);

      Fuente fuenteAsociada = repoFuentes.getFuente(fuenteId);
      if (fuenteAsociada == null) {
        ctx.sessionAttribute("flash_error", "Error interno: fuente no encontrada.");
        ctx.redirect("/home");
        return;
      }

      SolicitudDeCarga solicitud = new SolicitudDeCarga(
          titulo, descripcion, categoria, latitud, longitud,
          fechaAcontecimiento, multimedia, esRegistrado, fuenteAsociada
      );

      withTransaction(() -> repoSolicitudes.registrar(solicitud));

      ctx.sessionAttribute("flash_message", "Hecho cargado. Pendiente de revisión.");
      ctx.redirect("/hechos/confirmacion/" + solicitud.getId());

    } catch (Exception e) {
      ctx.sessionAttribute("flash_error", "Error al procesar el formulario: " + e.getMessage());
      ctx.status(400);
      ctx.redirect("/hechos/nuevo");
    }
  }

  // --- Confirmación ---
  public void showConfirmation(Context ctx) {
    Long solicitudId = ctx.pathParamAsClass("solicitudId", Long.class)
        .check(id -> id > 0, "ID de solicitud debe ser positivo")
        .get();

    SolicitudDeCarga solicitud = repoSolicitudes.getSolicitud(solicitudId);

    if (solicitud == null) {
      ctx.status(404);
      ctx.result("Solicitud no encontrada con ID " + solicitudId);
      return;
    }

    Map<String, Object> model = new HashMap<>();
    model.put("flash_message", ctx.sessionAttribute("flash_message"));
    model.put("solicitud", solicitud);
    model.put("registrado", solicitud.esRegistrado());

    ctx.render("confirmacion-solicitudCarga.hbs", model);
    ctx.sessionAttribute("flash_message", null);
  }


  public Map<String, Object> showBusquedaForm(@NotNull Context ctx) {
    Map<String, Object> modelo = new HashMap<>();

    // Obtener los parámetros de búsqueda
    String titulo = ctx.queryParam("titulo");
    String categoria = ctx.queryParam("categoria");
    String descripcion = ctx.queryParam("descripcion");
    String ubicacion = ctx.queryParam("ubicacion");
    String fechaAcontecimiento = ctx.queryParam("fechaAcontecimiento");
    String fechaCarga = ctx.queryParam("fechaCarga");
    String fechaDesde = ctx.queryParam("fechaDesde");
    String fechaHasta = ctx.queryParam("fechaHasta");

    // Guardar los filtros para mostrarlos en el formulario
    Map<String, Criterio> filtros = new HashMap<>();
    if (titulo != null) filtros.put("titulo", titulo);
    if (categoria != null) filtros.put("categoria", categoria);
    if (descripcion != null) filtros.put("descripcion", descripcion);
    if (ubicacion != null) filtros.put("ubicacion", ubicacion);
    if (fechaAcontecimiento != null) filtros.put("fechaAcontecimiento", fechaAcontecimiento);
    if (fechaCarga != null) filtros.put("fechaCarga", fechaCarga);
    if (fechaDesde != null) filtros.put("fechaDesde", fechaDesde);
    if (fechaHasta != null) filtros.put("fechaHasta", fechaHasta);

    modelo.put("filtros", filtros);

    // Si hay algún filtro, realizar la búsqueda
    boolean hayFiltros = !filtros.isEmpty();

    if (hayFiltros) {
      List<Hecho> resultados = repoHechos.buscarConFiltros(
          titulo, categoria, descripcion, ubicacion,
          fechaAcontecimiento, fechaCarga, fechaDesde, fechaHasta
      );

      // Convertir los hechos a un formato más amigable para la vista
      List<Map<String, String>> hechosFormateados = resultados.stream()
          .map(this::formatearHecho)
          .collect(Collectors.toList());

      modelo.put("resultadosBusqueda", true);
      modelo.put("hechos", hechosFormateados);
      modelo.put("cantidadResultados", resultados.size());
    }

    return modelo;
  }
}
