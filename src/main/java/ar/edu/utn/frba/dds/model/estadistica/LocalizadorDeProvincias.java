package ar.edu.utn.frba.dds.model.estadistica;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LocalizadorDeProvincias {

  public static String getProvincia(double lat, double lon) {
    try {
      String urlStr = "https://apis.datos.gob.ar/georef/api/ubicacion?lat="
          + lat + "&lon=" + lon;

      URL url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");

      BufferedReader in = new BufferedReader(
          new InputStreamReader(conn.getInputStream(), "UTF-8"));
      StringBuilder content = new StringBuilder();
      String line;
      while ((line = in.readLine()) != null) {
        content.append(line);
      }
      in.close();
      conn.disconnect();

      // Extraer el nombre de la provincia
      String respuesta = content.toString();
      int idx = respuesta.indexOf("\"provincia\"");
      if (idx != -1) {
        int nombreIdx = respuesta.indexOf("\"nombre\"", idx);
        int start = respuesta.indexOf(":", nombreIdx) + 2;
        int end = respuesta.indexOf("\"", start);
        return respuesta.substring(start, end);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  public static double getLongitud(String nombreProvincia) {
    try {
      String urlStr = "https://apis.datos.gob.ar/georef/api/provincias?nombre=" + nombreProvincia;
      URL url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader in = new BufferedReader(
          new InputStreamReader(conn.getInputStream(), "UTF-8"));
      StringBuilder content = new StringBuilder();
      String line;
      while ((line = in.readLine()) != null) {
        content.append(line);
      }
      in.close();
      conn.disconnect();

      String respuesta = content.toString();

      // Extraer la longitud
      int centroideIdx = respuesta.indexOf("\"centroide\"");
      int lonIdx = respuesta.indexOf("\"lon\"", centroideIdx);
      int lonStart = respuesta.indexOf(":", lonIdx) + 1;
      int lonEnd = respuesta.indexOf("}", lonStart);


      return Double.parseDouble(respuesta.substring(lonStart, lonEnd).trim());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return Double.parseDouble(null);
  }


  public static double getLatitud(String nombreProvincia) {
    try {
      String urlStr = "https://apis.datos.gob.ar/georef/api/provincias?nombre=" + nombreProvincia;
      URL url = new URL(urlStr);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader in = new BufferedReader(
          new InputStreamReader(conn.getInputStream(), "UTF-8"));
      StringBuilder content = new StringBuilder();
      String line;
      while ((line = in.readLine()) != null) {
        content.append(line);
      }
      in.close();
      conn.disconnect();

      String respuesta = content.toString();

      // Extraer la latitud
      int centroideIdx = respuesta.indexOf("\"centroide\"");
      int latIdx = respuesta.indexOf("\"lat\"", centroideIdx);
      int latStart = respuesta.indexOf(":", latIdx) + 1;
      int latEnd = respuesta.indexOf(",", latStart);


      return Double.parseDouble(respuesta.substring(latStart, latEnd).trim());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return Double.parseDouble(null);
  }
  public static String[] getProvinciaYMunicipio(double lat, double lon) {
    try {
      String urlStr = "https://apis.datos.gob.ar/georef/api/ubicacion?lat="
          + lat + "&lon=" + lon;

      String respuesta = hacerRequest(urlStr);

      // Provincia
      int provIdx = respuesta.indexOf("\"provincia\"");
      int nombreProvIdx = respuesta.indexOf("\"nombre\"", provIdx);
      int startProv = respuesta.indexOf(":", nombreProvIdx) + 2;
      int endProv = respuesta.indexOf("\"", startProv);
      String provincia = respuesta.substring(startProv, endProv);

      // Municipio
      int munIdx = respuesta.indexOf("\"municipio\"");
      int nombreMunIdx = respuesta.indexOf("\"nombre\"", munIdx);
      int startMun = respuesta.indexOf(":", nombreMunIdx) + 2;
      int endMun = respuesta.indexOf("\"", startMun);
      String municipio = respuesta.substring(startMun, endMun);

      return new String[]{provincia, municipio};

    } catch (Exception e) {
      e.printStackTrace();
    }
    return new String[]{"No encontrada", "No encontrada"};
  }
  private static String hacerRequest(String urlStr) throws Exception {
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(
        new InputStreamReader(conn.getInputStream(), "UTF-8"));

    StringBuilder content = new StringBuilder();
    String line;

    while ((line = in.readLine()) != null) {
      content.append(line);
    }

    in.close();
    conn.disconnect();

    return content.toString();
  }

}
