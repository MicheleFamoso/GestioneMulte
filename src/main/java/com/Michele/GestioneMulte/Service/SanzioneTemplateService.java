package com.Michele.GestioneMulte.Service;

import com.Michele.GestioneMulte.Exception.NotFoundException;
import com.Michele.GestioneMulte.Model.SanzioneTemplate;
import com.Michele.GestioneMulte.Repository.SanzioneTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanzioneTemplateService {

    private final SanzioneTemplateRepository sanzioneTemplateRepository;

    public SanzioneTemplateService(SanzioneTemplateRepository sanzioneTemplateRepository) {
        this.sanzioneTemplateRepository = sanzioneTemplateRepository;
    }

    public List<SanzioneTemplate> getAllSanzioniTemplate(){
        return sanzioneTemplateRepository.findAll();
    }

    public SanzioneTemplate GetSanzione(int id){
        return sanzioneTemplateRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Sanzione Non trovata")
        );
    }
}
