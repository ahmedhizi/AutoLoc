package tn.esprit.autoloc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


    private void addAgence(CrudRepository<Agence , Long> repository){

        int valeur = (int) System.currentTimeMillis();

        Agence a = new Agence();
        a.setNom("Agence ariana");
        a.setAdresse("1 Rue Hedi");
        a.setVille("Tunis");
        a.setTelephone("71585874");

        Vehicule v1 = new Vehicule();
        v1.setCategorie(CategorieVehicule.SUV);
        v1.setImmatriculation("785414TU96" + valeur);
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setStatut(StatutVehicule.EN_MAINTENANCE);
        v1.setTarifJournalier(new BigDecimal(100));

        Vehicule v2 = new Vehicule();
        v2.setCategorie(CategorieVehicule.UTILITAIRE);
        v2.setImmatriculation("785414TU95" + valeur);
        v2.setMarque("Toyota");
        v2.setModele("Yaris");
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setTarifJournalier(new BigDecimal(80));

        a.setVehicules(Set.of(v1,v2));
        repository.save(a);
    }

    private void loadAgence(CrudRepository repository, String typeDepot) {
        Iterable<Agence> agences = repository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("Type de dépôt utilisé : ")
                .append(typeDepot)
                .append("\n");

        for (Object obj : agences) {
            Agence agence = (Agence) obj;

            sb.append(agence.getIdAgence())
                    .append(" | ")
                    .append(agence.getNom())
                    .append("\n");

            Set<Vehicule> vehicules = agence.getVehicules();
            int nb = (vehicules == null) ? 0 : vehicules.size();

            sb.append("Nombre de véhicules : ")
                    .append(nb)
                    .append("\n");

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

        System.out.println(sb);
    }

    @Test
    public void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    public void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }


    @Test
    public void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "Basic Repository Mock");
    }

    @Test
    public void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "Full Repository");
    }


    @Test
    public void loadSortedAgences() {
        Iterable<Agence> agences =
                fullAgenceRepository.findAll(
                        Sort.by(Sort.Direction.DESC, "idAgence")
                );

        for (Agence agence : agences) {
            System.out.println("ID : " + agence.getIdAgence());
            System.out.println("Nom : " + agence.getNom());
            System.out.println("Adresse : " + agence.getAdresse());
            System.out.println("Ville : " + agence.getVille());
            System.out.println("Téléphone : " + agence.getTelephone());
            System.out.println("-------------------------");
        }
    }

    @Test
    public void loadPagedAgences() {
        Pageable pageable = PageRequest.of(
                0,
                2,
                Sort.by(Sort.Direction.DESC, "idAgence")
        );

        Page<Agence> page = fullAgenceRepository.findAll(pageable);



        System.out.println("Nombre total de page : " + page.getTotalPages());
        System.out.println("Page en cours : " + (page.getNumber() + 1));

        for (Agence agence : page.getContent()) {
            System.out.println("ID : " + agence.getIdAgence());
            System.out.println("Nom : " + agence.getNom());
            System.out.println("Adresse : " + agence.getAdresse());
            System.out.println("Ville : " + agence.getVille());
            System.out.println("Téléphone : " + agence.getTelephone());
            System.out.println("-------------------------");
        }
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long>{

}