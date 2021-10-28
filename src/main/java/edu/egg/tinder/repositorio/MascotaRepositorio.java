
package edu.egg.tinder.repositorio;

import edu.egg.tinder.entidad.Mascota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Semper Evincere
 */
@Repository
public interface MascotaRepositorio extends JpaRepository<Mascota, String>{
    @Query("SELECT c FROM Mascota c WHERE c.usuario.id = :id")
    public List<Mascota> buscarMascotasPorUsuario(@Param("id") String id);
}
