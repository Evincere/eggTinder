
package edu.egg.tinder.repositorio;

import edu.egg.tinder.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Semper Evincere
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
    
    @Query("SELECT c FROM Usuario c WHERE c.mail = mail")
    public Usuario buscarPorMail(@Param("mail") String mail);

    
}
