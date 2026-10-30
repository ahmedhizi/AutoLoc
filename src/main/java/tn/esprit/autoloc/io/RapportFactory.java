package tn.esprit.autoloc.io;

import org.springframework.stereotype.Component;

@Component
public class RapportFactory {
    public Rapport creer(Class<?> cible) {
        Rapport base = new RapportTexte();
        return cible.isAnnotationPresent(RapportConsoleActif.class)
                ? new RapportConsole(base)   // décoration
                : base;
    }
}
