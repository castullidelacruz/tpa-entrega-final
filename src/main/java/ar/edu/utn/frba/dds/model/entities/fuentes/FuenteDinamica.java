package ar.edu.utn.frba.dds.model.entities.fuentes;

import ar.edu.utn.frba.dds.model.entities.Hecho;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FUENTE_DINAMICA")
public class FuenteDinamica extends Fuente {

  /*
   * Se eliminó:
   * 1. La lista local listaDeHechos y el mapeo @ManyToMany.
   * 2. Los métodos setter para la lista y el método actualiza(RepositorioHechos).
   */

  public FuenteDinamica() {
  }

  @Override
  public List<Hecho> getHechos() {
    // Implementación real del método getHechos()
    // La Capa de Repositorio o Servicio usará el ID de esta instancia
    // para consultar la base de datos (SELECT * FROM Hechos WHERE fuente_origen_id = this.id).

    // Por motivos de diseño (no queremos lógica de persistencia aquí),
    // esta clase solo actúa como marcador para la consulta y no almacena la lista en memoria.
    return List.of();
  }

  @Override
  public void actualizarHechos() {
    // Una fuente dinámica no se actualiza periódicamente de forma externa.
    // Sus hechos se actualizan a través del flujo de Solicitudes de Carga.
  }
}