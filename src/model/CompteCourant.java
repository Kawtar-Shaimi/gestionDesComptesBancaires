package model;

import utils.BusinessException;
import utils.ValidationException;

public class CompteCourant extends Compte {
    private final double decouvert; // autorisation de decouvert

    public CompteCourant(String code, double soldeInitial, double decouvert) {
        super(code, soldeInitial);
        if (decouvert < 0) {
            throw new ValidationException("Le decouvert doit etre positif ou nul");
        }
        this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    @Override
    public void retirer(Retrait retrait) throws BusinessException {
        if (retrait == null) {
            throw new ValidationException("Retrait invalide");
        }
        if (retrait.getMontant() <= 0) {
            throw new ValidationException("Le montant de retrait doit etre positif");
        }
        double soldeApres = this.solde - retrait.getMontant();
        if (soldeApres < -decouvert) {
            throw new BusinessException("Retrait refuse: depassement du decouvert autorise");
        }
        this.solde = soldeApres;
        enregistrerOperation(retrait);
    }

    @Override
    public double calculerInteret() {
        return 0.0; // pas d'interets sur compte courant
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Courant - Code: " + getCode() + ", Solde: " + getSolde() + ", Decouvert: " + decouvert);
    }
}


