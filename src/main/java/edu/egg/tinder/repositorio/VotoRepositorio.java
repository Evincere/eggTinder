
package edu.egg.tinder.repositorio;

import edu.egg.tinder.entidad.Voto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Semper Evincere
 */
@Repository
public interface VotoRepositorio extends JpaRepository<Voto, String> {
    
    @Query("SELECT c FROM Voto c WHERE c.Mascota1.id = id ORDER BY c.fecha DESC")
    public List<Voto> busrVotosPropios(String id);
    
    @Query("SELECT c FROM Voto c WHERE c.Mascota2.id = id ORDER BY c.fecha DESC")
    public List<Voto> busrVotosRecibidos(String id);
}   

