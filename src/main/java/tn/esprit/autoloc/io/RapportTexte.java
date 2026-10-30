package tn.esprit.autoloc.io;

import tn.esprit.autoloc.domain.Agence;

public class RapportTexte implements Rapport {
    private final StringBuilder sb = new StringBuilder();
    public void entete(Agence a) {
        sb.append("Agence #").append(a.getIdAgence()).append(" - ").append(a.getNom())
                .append(" - Tel : ").append(a.getTelephone()).append("\n");
    }
    public void total(double t) { sb.append("Total des tarifs : ").append(String.format("%.2f", t)); }
    public String contenu() { return sb.toString(); }
}
