package ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import models.Compte;
import service.CompteService;
import utils.BusinessException;
import utils.ValidationException;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CompteService bank = new CompteService();

    public static void main(String[] args) {
        boolean quitter = false;
        while (!quitter) {
            afficherMenu();
            int choix = lireEntier("Votre choix: ");
            try {
                switch (choix) {
                    case 1:
                        creerCompteCourant();
                        break;
                    case 2:
                        creerCompteEpargne();
                        break;
                    case 3:
                        effectuerVersement();
                        break;
                    case 4:
                        effectuerRetrait();
                        break;
                    case 5:
                        effectuerVirement();
                        break;
                    case 6:
                        consulterSolde();
                        break;
                    case 7:
                        listerOperations();
                        break;
                    case 8:
                        listerComptes();
                        break;
                    case 0:
                        quitter = true;
                        System.out.println("Au revoir!");
                        break;
                    default:
                        System.out.println("Choix invalide");
                }
            } catch (ValidationException ve) {
                System.out.println("Erreur de validation: " + ve.getMessage());
            } catch (BusinessException be) {
                System.out.println("Erreur metier: " + be.getMessage());
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\n=== Menu Banque ===");
        System.out.println("1. Creer Compte Courant");
        System.out.println("2. Creer Compte Epargne");
        System.out.println("3. Versement");
        System.out.println("4. Retrait");
        System.out.println("5. Virement");
        System.out.println("6. Consulter solde");
        System.out.println("7. Consulter operations");
        System.out.println("8. Lister comptes");
        System.out.println("0. Quitter");
    }

    private static void creerCompteCourant() {
        System.out.println("--- Nouveau Compte Courant ---");
        double solde = lireDouble("Solde initial: ");
        double decouvert = lireDouble("Decouvert autorise: ");
        Compte c = bank.creerCompteCourant(solde, decouvert);
        System.out.println("Compte courant cree. Code: " + c.getCode());
    }

    private static void creerCompteEpargne() {
        System.out.println("--- Nouveau Compte Epargne ---");
        double solde = lireDouble("Solde initial: ");
        double taux = lireDouble("Taux (ex 0.03): ");
        Compte c = bank.creerCompteEpargne(solde, taux);
        System.out.println("Compte epargne cree. Code: " + c.getCode());
    }

    private static void effectuerVersement() {
        System.out.println("--- Versement ---");
        Compte compte = choisirCompte("Choisir le compte a crediter");
        if (compte == null) return;
        double montant = lireDouble("Montant: ");
        String source = lireTexte("Source: ");
        bank.verser(compte.getCode(), montant, source);
        System.out.println("Versement effectue.");
    }

    private static void effectuerRetrait() throws BusinessException {
        System.out.println("--- Retrait ---");
        String code = lireTexte("Code compte: ");
        double montant = lireDouble("Montant: ");
        String dest = lireTexte("Destination: ");
        bank.retirer(code, montant, dest);
        System.out.println("Retrait effectue.");
    }

    private static void effectuerVirement() throws BusinessException {
        System.out.println("--- Virement ---");
        Compte source = choisirCompte("Choisir le compte source");
        if (source == null) return;
        Compte destination;
        while (true) {
            destination = choisirCompte("Choisir le compte destination");
            if (destination == null) return;
            if (!destination.getCode().equals(source.getCode())) break;
            System.out.println("La destination doit etre differente de la source.");
        }
        double montant = lireDouble("Montant: ");
        bank.virement(source.getCode(), destination.getCode(), montant);
        System.out.println("Virement effectue.");
    }

    private static void consulterSolde() {
        Compte c = choisirCompte("Choisir un compte");
        if (c == null) {
            System.out.println("Compte introuvable");
        } else {
            c.afficherDetails();
            System.out.println("Solde: " + c.getSolde());
            System.out.println("Interet potentiel: " + c.calculerInteret());
        }
    }

    private static void listerOperations() {
        Compte c = choisirCompte("Choisir un compte");
        if (c == null) {
            System.out.println("Compte introuvable");
            return;
        }
        System.out.println("Operations pour " + c.getCode() + ":");
        c.getListeOperations().forEach(op -> {
            System.out.println(op.getDate() + " - " + op.getClass().getSimpleName() + " - " + op.getMontant());
        });
    }

    private static void listerComptes() {
        List<Compte> comptes = bank.listerComptes();
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte");
            return;
        }
        for (Compte c : comptes) {
            c.afficherDetails();
        }
    }

    private static String lireTexte(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }

    private static int lireEntier(String label) {
        while (true) {
            try {
                System.out.print(label);
                String s = scanner.nextLine();
                return Integer.parseInt(s.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Veuillez saisir un entier valide.");
            }
        }
    }

    private static double lireDouble(String label) {
        while (true) {
            try {
                System.out.print(label);
                String s = scanner.nextLine();
                return Double.parseDouble(s.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Veuillez saisir un nombre valide.");
            }
        }
    }

    private static Compte choisirCompte(String titre) {
        List<Compte> comptes = bank.listerComptes();
        if (comptes.isEmpty()) {
            System.out.println("Aucun compte disponible.");
            return null;
        }
        System.out.println(titre + " :");
        for (int i = 0; i < comptes.size(); i++) {
            Compte c = comptes.get(i);
            System.out.println((i + 1) + ". " + c.getCode());
        }
        int choix;
        while (true) {
            choix = lireEntier("Choix (1-" + comptes.size() + "): ");
            if (choix >= 1 && choix <= comptes.size()) break;
            System.out.println("Choix invalide.");
        }
        return comptes.get(choix - 1);
    }
}


