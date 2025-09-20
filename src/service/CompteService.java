package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Compte;
import model.CompteCourant;
import model.CompteEpargne;
import model.Retrait;
import model.Versement;
import utils.BusinessException;
import utils.ValidationException;
import utils.Validators;

public class CompteService {
    private final Map<String, Compte> comptes = new HashMap<String, Compte>();
    private int sequence = 0;

    public Compte creerCompteCourant(double soldeInitial, double decouvert) {
        String code = genererCodeUnique();
        Compte c = new CompteCourant(code, soldeInitial, decouvert);
        comptes.put(code, c);
        return c;
    }

    public Compte creerCompteEpargne(double soldeInitial, double tauxInteret) {
        String code = genererCodeUnique();
        Compte c = new CompteEpargne(code, soldeInitial, tauxInteret);
        comptes.put(code, c);
        return c;
    }

    public Compte chercherCompte(String code) {
        return comptes.get(code);
    }

    public List<Compte> listerComptes() {
        return new ArrayList<Compte>(comptes.values());
    }

    public void verser(String code, double montant, String source) {
        Compte c = requireCompte(code);
        Validators.requirePositiveAmount(montant, "Montant du versement");
        c.verser(new Versement(montant, source));
    }

    public void retirer(String code, double montant, String destination) throws BusinessException {
        Compte c = requireCompte(code);
        Validators.requirePositiveAmount(montant, "Montant du retrait");
        c.retirer(new Retrait(montant, destination));
    }

    public void virement(String codeSource, String codeDestination, double montant) throws BusinessException {
        if (codeSource == null || codeDestination == null || codeSource.equals(codeDestination)) {
            throw new ValidationException("Codes source et destination invalides");
        }
        Validators.requirePositiveAmount(montant, "Montant du virement");
        Compte src = requireCompte(codeSource);
        Compte dst = requireCompte(codeDestination);
        src.retirer(new Retrait(montant, "Virement vers " + codeDestination));
        dst.verser(new Versement(montant, "Virement de " + codeSource));
    }

    private Compte requireCompte(String code) {
        Validators.requireValidCode(code);
        Compte c = comptes.get(code);
        if (c == null) {
            throw new ValidationException("Compte introuvable: " + code);
        }
        return c;
    }

    private String genererCodeUnique() {
        String code;
        do {
            sequence++;
            code = String.format("CPT-%05d", sequence);
        } while (comptes.containsKey(code));
        return code;
    }
}


