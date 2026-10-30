package tn.esprit.autoloc.service;

import jakarta.transaction.Transactional;
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
    public String simuler(Long id, int jours, VehiculeOffre offre) {
        Agence a = findById(id);
        double total = 0;
        if (a.getVehicules() != null) {
            for (Vehicule v : a.getVehicules())
                total += vehiculeService.calculTarif(v, jours, offre);
        }
        return "Agence #" + a.getIdAgence() + " - " + a.getNom() + " - Tel : " + a.getTelephone()
                + "\nOffre : " + offre + " / " + jours + " jour(s)"
                + "\nTotal des tarifs : " + String.format("%.2f", total);
    }


}
