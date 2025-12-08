package com.Michele.GestioneMulte.Service;

import com.Michele.GestioneMulte.Dto.SanzioneCompletaDto;
import com.Michele.GestioneMulte.Model.SanzioneCompleta;
import com.Michele.GestioneMulte.Model.SanzioneTemplate;
import com.Michele.GestioneMulte.Model.User;
import com.Michele.GestioneMulte.Repository.SanzioneCompletaRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SanzioneCompletaService {
    private final SanzioneCompletaRepository sanzioneCompletaRepository;
    private final SanzioneTemplateService sanzioneTemplateService;
    private final UserService userService;

    public SanzioneCompletaService(SanzioneCompletaRepository sanzioneCompletaRepository, SanzioneTemplateService sanzioneTemplateService, UserService userService) {
        this.sanzioneCompletaRepository = sanzioneCompletaRepository;
        this.sanzioneTemplateService = sanzioneTemplateService;
        this.userService = userService;
    }

    public SanzioneCompleta createSanzione(SanzioneCompletaDto sanzioneCompletaDto ,Principal principal,int SanzioneId){
        SanzioneCompleta sanzioneCompleta = new  SanzioneCompleta();
        User user= userService.findByUsername(principal.getName());
        sanzioneCompleta.setUser(user);
        sanzioneCompleta.setComune(user.getComune());
        sanzioneCompleta.setVia(sanzioneCompletaDto.getVia());
        sanzioneCompleta.setSanzione(sanzioneCompletaDto.getSanzione());
        sanzioneCompleta.setConducente(sanzioneCompletaDto.getConducente());
        sanzioneCompleta.setVelocita(sanzioneCompletaDto.getVelocita());
        SanzioneTemplate sanzioneTemplate = sanzioneTemplateService.GetSanzione(SanzioneId);
        String CorpoSanzione = sanzioneTemplate.getCorpo();
       CorpoSanzione = CorpoSanzione.replace("{Comune}",sanzioneCompleta.getComune().getComune());
        CorpoSanzione = CorpoSanzione.replace("{Sanzione}",""+sanzioneCompleta.getSanzione());
        CorpoSanzione = CorpoSanzione.replace("{Conducente}",sanzioneCompleta.getConducente());
        CorpoSanzione = CorpoSanzione.replace("{Velocita}",sanzioneCompleta.getVelocita());
        sanzioneCompleta.setSanzioneCompilata(CorpoSanzione);
        return sanzioneCompletaRepository.save(sanzioneCompleta);
    };


}
