package com.Michele.GestioneMulte.Model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SanzioneTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    private String Corpo;
}
