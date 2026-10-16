package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicAgenceServiceImpl implements IAgenceService {
    private final IAgenceRepository Repository;

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom()) && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public Agence create(Agence agence) throws IllegalAccessException {
        if(agence.getIdAgence() != null){
            throw new IllegalAccessException("identifient de l'agence doit etre null !!!");
        }
        if(!check(agence,true)){
            throw new IllegalAccessException("champs obligatoires !!!");
        }
        return Repository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        return Repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agence",id.toString()));
    }

    public List<Agence> findAll() {
        return null; // à implémenter
    }

    @Override
    public Agence update(Long id, Agence agence) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean hasVehicules(Long id) {
        return false;
    }

}
