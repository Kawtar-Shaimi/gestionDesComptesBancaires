package model;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Operation {
    private final String numero; // UUID string
    private final LocalDateTime date;
    private final double montant;

    protected Operation(double montant) {
        this.numero = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.montant = montant;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }
}


