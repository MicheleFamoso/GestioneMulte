package com.Michele.GestioneMulte.Repository;

import com.Michele.GestioneMulte.Model.SanzioneCompleta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SanzioneCompletaRepository extends JpaRepository<SanzioneCompleta,Integer> {

    public List<SanzioneCompleta> findByUserId(int id);

}
