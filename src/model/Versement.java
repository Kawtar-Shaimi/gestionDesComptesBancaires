package model;

public class Versement extends Operation {
    private final String source;

    public Versement(double montant, String source) {
        super(montant);
        this.source = source == null ? "Inconnu" : source;
    }

    public String getSource() {
        return source;
    }
}


