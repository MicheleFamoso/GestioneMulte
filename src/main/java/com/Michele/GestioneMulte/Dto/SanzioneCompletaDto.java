package com.Michele.GestioneMulte.Dto;

import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class SanzioneCompletaDto {
    private String via;
    private String conducente;
    private String velocita;
    private double sanzione;
    private String sanzioneCompilata;
    private int template_id;
}
