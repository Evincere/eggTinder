
package edu.egg.tinder.servicio;

import edu.egg.tinder.entidad.Foto;
import edu.egg.tinder.entidad.Mascota;
import edu.egg.tinder.entidad.Usuario;
import edu.egg.tinder.errores.ErrorServicio;
import edu.egg.tinder.repositorio.MascotaRepositorio;
import edu.egg.tinder.repositorio.UsuarioRepositorio;
import enumeraciones.Sexo;
import java.util.Date;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Semper Evincere
 */
@Service
public class MascotaServicio {
    @Autowired
    private UsuarioRepositorio userRepo;
    @Autowired
    private MascotaRepositorio mascotaRepo;
    @Autowired
    private FotoServicio fotoServicio;
    
    
    @Transactional
    public void agregarMascota(MultipartFile archivo, String id, String nombre,Sexo sexo) throws ErrorServicio{
        Usuario usuario = userRepo.findById(id).get();
        
        validar(nombre, sexo);
        
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setSexo(sexo);
        mascota.setAlta(new Date());
        
        Foto foto = fotoServicio.guardar(archivo);
        mascota.setFoto(foto);
        
        mascotaRepo.save(mascota);
       
    }
    
    @Transactional
    public void modificar(MultipartFile archivo, String idUser, String idMascota, String nombre, Sexo sexo) throws ErrorServicio{
        validar(nombre, sexo);
        
        Optional<Mascota> respuesta = mascotaRepo.findById(idMascota);
        if(respuesta.isPresent()){
            Mascota mascota = respuesta.get();
            if(mascota.getUsuario().getId().equals(idUser)){
                mascota.setNombre(nombre);
                mascota.setSexo(sexo);
                
                String idFoto = null;
                if(mascota.getFoto()!=null){
                    idFoto=mascota.getFoto().getId();
                }
                
                Foto foto = fotoServicio.actualizar(idFoto, archivo);
                mascota.setFoto(foto);
                
                mascotaRepo.save(mascota);
            }else{
                throw new ErrorServicio("No tiene permisos suficientes");
            }
            
        }else{
            throw new ErrorServicio("No existe una mascota con ese Id");
        }
        
    }
    
    @Transactional
    public void eliminar(String idUser, String idMascota) throws ErrorServicio{
        Optional<Mascota> respuesta = mascotaRepo.findById(idMascota);
        if(respuesta.isPresent()){
            Mascota mascota = respuesta.get();
            if(mascota.getUsuario().getId().equals(idUser)){
                mascota.setBaja(new Date());
                mascotaRepo.save(mascota);
            }
        }else{
            throw new ErrorServicio("No existe esa mascota");
        }
    }
    
    public void validar(String nombre, Sexo sexo) throws ErrorServicio {
        if(nombre==null || nombre.isEmpty()){
            throw new ErrorServicio("Nombre de la mascota no puede ser vacio");
            
        }
        if(sexo==null){
            throw new ErrorServicio("sexo de la mascota no puede ser nulo");
            
        }
    }

}
