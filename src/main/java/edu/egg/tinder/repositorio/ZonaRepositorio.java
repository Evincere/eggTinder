
package edu.egg.tinder.repositorio;

import edu.egg.tinder.entidad.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Semper Evincere
 */
@Repository
public interface ZonaRepositorio extends JpaRepository<Zona, String> {
    
}
