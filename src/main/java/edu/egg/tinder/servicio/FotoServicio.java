package edu.egg.tinder.servicio;

import edu.egg.tinder.entidad.Foto;
import edu.egg.tinder.repositorio.FotoRepositorio;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Semper Evincere
 */
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepo;

    @Transactional
    public Foto guardar(MultipartFile archivo) {
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepo.save(foto);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }
    
    @Transactional
    public Foto actualizar(String idFoto, MultipartFile archivo){
        if (archivo != null) {
            try {
                Foto foto = new Foto();
                if(idFoto != null){
                    Optional<Foto> respuesta = fotoRepo.findById(idFoto);
                    if(respuesta.isPresent()){
                        foto = respuesta.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setContenido(archivo.getBytes());

                return fotoRepo.save(foto);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        return null;
    }
}
