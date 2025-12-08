package com.Michele.GestioneMulte.Model;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Comune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String comune;
    @OneToMany(mappedBy = "comune")
    private List<User> users;
    @OneToMany(mappedBy = "comune")
    private List<SanzioneCompleta> sanzioni;
}
