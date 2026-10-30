package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public interface Rapport {
    void entete(Agence agence);
    void total(double total);
    String contenu();
}
