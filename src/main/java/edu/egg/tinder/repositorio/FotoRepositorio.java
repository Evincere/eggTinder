/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.egg.tinder.repositorio;

import edu.egg.tinder.entidad.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Semper Evincere
 */
public interface FotoRepositorio extends JpaRepository<Foto, String>{
    
}
