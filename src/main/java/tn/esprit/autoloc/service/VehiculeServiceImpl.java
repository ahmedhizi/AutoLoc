package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IVehiculeRepository;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    public VehiculeServiceImpl(IVehiculeRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Vehicule";
    }

    @Override
    protected Long getId(Vehicule entity) {
        return entity.getIdVehicule();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setCategorie(source.getCategorie());
        target.setTarifJournalier(source.getTarifJournalier());
        target.setStatut(source.getStatut());
        target.setAgence(source.getAgence());
    }

    @Override
    public boolean check(Vehicule entity, boolean isNew) {
        return Utils.isNotEmpty(entity.getImmatriculation())
                && Utils.isNotEmpty(entity.getMarque())
                && Utils.isNotEmpty(entity.getModele())
                && entity.getCategorie() != null
                && entity.getStatut() != null
                && entity.getTarifJournalier() != null
                && entity.getTarifJournalier().signum() > 0;
    }


    @Override
    public double calculTarif(Vehicule vehicule, int nbJours, VehiculeOffre offre) {

        double tarifJournalier = vehicule.getTarifJournalier().doubleValue();

        if (offre == VehiculeOffre.WEEKEND) {
            tarifJournalier *= 0.80;

        } else if (offre == VehiculeOffre.LONGUE_DUREE) {
            tarifJournalier *= 0.75;
        }

        double total = tarifJournalier * nbJours;

        if (offre == VehiculeOffre.LONGUE_DUREE && total > 2000) {
            total *= 0.95;
        }

        return total;
    }

}