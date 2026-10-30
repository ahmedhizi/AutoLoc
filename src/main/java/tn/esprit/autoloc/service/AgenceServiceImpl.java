package tn.esprit.autoloc.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;

@Service
@Primary
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService{
    private final IVehiculeService vehiculeService;

    public AgenceServiceImpl(IAgenceRepository repository, IVehiculeService vehiculeService) {
        super(repository);
        this.vehiculeService = vehiculeService;
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence agence) {
        return agence.getIdAgence();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setAdresse(source.getAdresse());
        target.setVille(source.getVille());
        target.setTelephone(source.getTelephone());
        // les véhicules ne sont pas copiés : ils se gèrent via leur propre service
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public boolean hasVehicules(Long id) {
        Agence agence = findById(id);
        return agence.getVehicules() != null && !agence.getVehicules().isEmpty();
    }

    @Override
    public String simuler(Agence agence, int nbJours, VehiculeOffre offre) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id: " + agence.getIdAgence()
                + ", Nom: " + agence.getNom()
                + ", Tel: " + agence.getTelephone() + "\n");

        double total = 0;
        if (agence.getVehicules() != null) {
            for (Vehicule vehicule : agence.getVehicules()) {
                total += vehiculeService.calculTarif(vehicule, nbJours, offre);
            }
        }

        stringBuilder.append("Total : " + total + "\n");
        return stringBuilder.toString();
    }



}
