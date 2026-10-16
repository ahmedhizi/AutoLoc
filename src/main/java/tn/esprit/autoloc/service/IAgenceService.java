package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    boolean check(Agence agence, boolean isNew);
    Agence create(Agence agence) throws IllegalAccessException;
    Agence findById(Long id);
    List<Agence> findAll();
    Agence update(Long id, Agence agence);
    void deleteById(Long id);
    boolean hasVehicules(Long id);
}
