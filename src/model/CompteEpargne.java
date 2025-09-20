package models;

import utils.BusinessException;
import utils.ValidationException;

public class CompteEpargne extends Compte {
    private final double tauxInteret; // ex: 0.03 = 3%

    public CompteEpargne(String code, double soldeInitial, double tauxInteret) {
        super(code, soldeInitial);
        if (tauxInteret < 0) {
            throw new ValidationException("Le taux d'interet doit etre positif ou nul");
        }
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    @Override
    public void retirer(Retrait retrait) throws BusinessException {
        if (retrait == null) {
            throw new ValidationException("Retrait invalide");
        }
        if (retrait.getMontant() <= 0) {
            throw new ValidationException("Le montant de retrait doit etre positif");
        }
        if (retrait.getMontant() > this.solde) {
            throw new BusinessException("Retrait refuse: solde insuffisant");
        }
        this.solde -= retrait.getMontant();
        enregistrerOperation(retrait);
    }

    @Override
    public double calculerInteret() {
        return this.solde * this.tauxInteret;
    }

    @Override
    public void afficherDetails() {
        System.out.println("Compte Epargne - Code: " + getCode() + ", Solde: " + getSolde() + ", Taux: " + (tauxInteret * 100) + "%");
    }
}


