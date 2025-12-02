package ar.edu.utn.frba.dds.server.templates;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Template;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.rendering.FileRenderer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;


public class JavalinHandlebars implements FileRenderer {

  Handlebars handlebars = new Handlebars();

  public JavalinHandlebars() {
    // Registrar helper para formatear fechas
    handlebars.registerHelper("formatDate", (Helper<Object>) (context, options) -> {
      if (context == null) {
        return "";
      }

      if (context instanceof LocalDateTime) {
        LocalDateTime dateTime = (LocalDateTime) context;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return formatter.format(dateTime);
      }

      if (context instanceof String) {
        try {
          LocalDateTime dateTime = LocalDateTime.parse((String) context);
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
          return formatter.format(dateTime);
        } catch (Exception e) {
          return context.toString();
        }
      }

      return context.toString();
    });

    // Helper para formatear solo fecha (sin hora)
    handlebars.registerHelper("formatDateOnly", (Helper<Object>) (context, options) -> {
      if (context == null) {
        return "";
      }

      if (context instanceof LocalDateTime) {
        LocalDateTime dateTime = (LocalDateTime) context;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatter.format(dateTime);
      }

      if (context instanceof String) {
        try {
          LocalDateTime dateTime = LocalDateTime.parse((String) context);
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          return formatter.format(dateTime);
        } catch (Exception e) {
          return context.toString();
        }
      }

      return context.toString();
    });
  }

  @NotNull
  @Override
  public String render(@NotNull String path, @NotNull Map<String, ?> model,
                       @NotNull Context context) {
    Template template = null;
    try {
      // Agregar variables globales al modelo
      Map<String, Object> extendedModel = new HashMap<>(model);
      extendedModel.put("year", Year.now().getValue());

      template = handlebars.compile("templates/" + path.replace(".hbs", ""));
      return template.apply(extendedModel);
    } catch (IOException e) {
      e.printStackTrace();
      context.status(HttpStatus.NOT_FOUND);
      return "No se encuentra la página indicada...";
    }
  }
}
