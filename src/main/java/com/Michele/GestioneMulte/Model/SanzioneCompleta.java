package com.Michele.GestioneMulte.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SanzioneCompleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String via;
    private String conducente;
    private String velocita;
    private double sanzione;
    @Lob
    private String sanzioneCompilata;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;


}
