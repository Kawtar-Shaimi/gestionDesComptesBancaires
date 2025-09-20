package model;

public class Retrait extends Operation {
    private final String destination;

    public Retrait(double montant, String destination) {
        super(montant);
        this.destination = destination == null ? "Inconnu" : destination;
    }

    public String getDestination() {
        return destination;
    }
}