package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.ValidationException;
import utils.Validators;

public abstract class Compte {
    private final String code;
    protected double solde;
    private final List<Operation> listeOperations;

    protected Compte(String code, double soldeInitial) {
        Validators.requireValidCode(code);
        if (soldeInitial < 0) {
            throw new ValidationException("Le solde initial doit etre positif ou nul");
        }
        this.code = code;
        this.solde = soldeInitial;
        this.listeOperations = new ArrayList<Operation>();
    }

    public String getCode() {
        return code;
    }

    public double getSolde() {
        return solde;
    }

    public List<Operation> getListeOperations() {
        return Collections.unmodifiableList(listeOperations);
    }

    protected void enregistrerOperation(Operation operation) {
        if (operation == null) {
            throw new ValidationException("Operation ne peut pas etre nulle");
        }
        this.listeOperations.add(operation);
    }

    public void verser(Versement versement) {
        if (versement == null) {
            throw new ValidationException("Versement invalide");
        }
        if (versement.getMontant() <= 0) {
            throw new ValidationException("Le montant de versement doit etre positif");
        }
        this.solde += versement.getMontant();
        enregistrerOperation(versement);
    }

    public abstract void retirer(Retrait retrait) throws utils.BusinessException;

    public abstract double calculerInteret();

    public abstract void afficherDetails();
}