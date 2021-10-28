
package edu.egg.tinder.servicio;

import edu.egg.tinder.entidad.Mascota;
import edu.egg.tinder.entidad.Voto;
import edu.egg.tinder.errores.ErrorServicio;
import edu.egg.tinder.repositorio.MascotaRepositorio;
import edu.egg.tinder.repositorio.VotoRepositorio;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Semper Evincere
 */
@Service
public class VotoServicio {
    @Autowired
    private MascotaRepositorio mascotaRepo;
    
    @Autowired
    private NotificacionServicio notificacionServicio;
    
    @Autowired
    private VotoRepositorio votoRepo;
    
    public void votar(String idUser, String idMascota1, String idMascota2) throws ErrorServicio{
        Voto voto = new Voto();
        voto.setFecha(new Date());
        if(idMascota1.equals(idMascota2)){
            throw new ErrorServicio("No puede votarse a si mismo");
        }
        Optional<Mascota> respuesta = mascotaRepo.findById(idMascota1);
        if(respuesta.isPresent()){
            Mascota mascota1 = respuesta.get();
            if(mascota1.getUsuario().getId().equals(idUser)){
                voto.setMascota1(mascota1);
            }else{
                throw new ErrorServicio("No tiene permiso para realizar la operacion");
                
            }
        }else{
            throw new ErrorServicio("No existe una mascota para ese Id");
        }
        
        Optional<Mascota> respuesta2 = mascotaRepo.findById(idMascota2);
        if(respuesta2.isPresent()){
            Mascota mascota2 = respuesta2.get();
            voto.setMascota2(mascota2);
            
            notificacionServicio.enviar("Tu mascota ha sido votada", "Tinder de mascotas", mascota2.getUsuario().getMail());
        }else{
            throw new ErrorServicio("No existe una mascota para ese Id");
        }
        
        votoRepo.save(voto);
    }
    
    public void responder(String idUser, String idVoto) throws ErrorServicio{
        Optional<Voto> respuesta = votoRepo.findById(idVoto);
        if(respuesta.isPresent()){
            Voto voto = respuesta.get();
            voto.setRespuesta(new Date());
            if(voto.getMascota2().getUsuario().getId().equals(idUser)){
                notificacionServicio.enviar("Tu voto fue correspondido", "Tinder de mascotas", voto.getMascota1().getUsuario().getMail());
                votoRepo.save(voto);
            }else{
                throw new ErrorServicio("No tiene permisos para realizar esa operacion");
            }
            votoRepo.save(voto);
        }else{
            throw new ErrorServicio("No existe el voto seleccionado");
        }
    }

}
