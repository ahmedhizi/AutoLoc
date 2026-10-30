package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class RapportConsole implements Rapport {
    private final Rapport delegue;
    public RapportConsole(Rapport delegue) { this.delegue = delegue; }
    public void entete(Agence a) {
        System.out.println("Agence #" + a.getIdAgence() + " - " + a.getNom() + " - Tel : " + a.getTelephone());
    }
    public void total(double t) { delegue.total(t); }
    public String contenu() { return delegue.contenu(); }
}
