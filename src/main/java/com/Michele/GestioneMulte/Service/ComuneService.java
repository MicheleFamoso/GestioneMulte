package com.Michele.GestioneMulte.Service;


import com.Michele.GestioneMulte.Dto.ComuneDto;
import com.Michele.GestioneMulte.Exception.NotFoundException;
import com.Michele.GestioneMulte.Model.Comune;
import com.Michele.GestioneMulte.Repository.ComuneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuneService {
    private final ComuneRepository comuneRepository;

    public ComuneService(ComuneRepository comuneRepository) {
        this.comuneRepository = comuneRepository;
    }

    public Comune createComune (ComuneDto comuneDto){
        Comune comune = new Comune();
        comune.setComune(comuneDto.getComune());
        return comuneRepository.save(comune);
    }

    public List<Comune> getAllComuni(){
        return comuneRepository.findAll();
    }

    public Comune getComuneById(int id){
        return comuneRepository.findById(id).orElseThrow(()-> new NotFoundException("Comune non trovato"));
    }

}
