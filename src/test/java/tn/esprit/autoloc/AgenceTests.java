package tn.esprit.autoloc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Fail.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AgenceTests {
    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    @Test
    public void addAgence(){
        Agence a = new Agence();
        a.setNom("Agence ariana");
        a.setAdresse("1 Rue Hedi");
        a.setVille("Tunis");
        a.setTelephone("71585874");

        Vehicule v1 = new Vehicule();
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setImmatriculation("785414TU96");
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setStatut(StatutVehicule.EN_MAINTENANCE);
        v1.setTarifJournalier(new BigDecimal(100));

        Vehicule v2 = new Vehicule();
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setImmatriculation("785414TU95");
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setTarifJournalier(new BigDecimal(80));

        a.setVehicules(Set.of(v1,v2));
        basicAgenceRepository.save(a);
    }

    @Test
    public void loadAgence(){
        Iterable<Agence> agences = basicAgenceRepository.findAll();

        StringBuilder sb = new StringBuilder();
        for (Agence agence : agences) {
            sb.append(agence.getIdAgence())
                    .append(" | ")
                    .append(agence.getNom())
                    .append("\n");
            Set<Vehicule> vehicules = agence.getVehicules();
            int nb = (vehicules == null) ? 0 : vehicules.size();
            sb.append("Nombre de véhicules : ").append(nb).append("\n");

            if (vehicules != null) {
                for (Vehicule v : vehicules) {
                    sb.append("   ")
                            .append(v.getIdVehicule())
                            .append(" | ")
                            .append(v.getImmatriculation())
                            .append("\n");
                }
            }

        }
        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long>{

}