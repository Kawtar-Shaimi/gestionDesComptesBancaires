# 🏦 Système de Gestion de Comptes Bancaires

## 📌 Description du projet
Ce projet est une application console développée en **Java 8** permettant de gérer des comptes bancaires (comptes courants et comptes épargne).  
L’application respecte une architecture en couches (présentation, métier, utilitaire) et propose un menu interactif pour effectuer les principales opérations bancaires :

- Création de comptes (courant ou épargne)
- Versements
- Retraits
- Virements entre comptes
- Consultation de solde
- Consultation de la liste des opérations

L’objectif est de fournir une solution simple, robuste et extensible tout en respectant les principes **SOLID** et les bonnes pratiques de conception objet.

---

## ⚙️ Technologies utilisées
- **Java 8** (JDK 1.8)
- **Eclipse IDE**
- **Collections Java** : `ArrayList`, `HashMap`
- **Java Time API** (`LocalDateTime`, `DateTimeFormatter`)
- **UUID** pour la génération d’identifiants uniques
- **Gestion des exceptions** avec `try/catch`
- **Git & GitHub** pour le versioning

---

## 🏗️ Structure du projet
L’application suit une architecture en couches :

```

src/
├── ui/              → Couche présentation (menus, interaction utilisateur)
├── metier/          → Couche métier (logique de gestion des comptes et opérations)
├── model/          → Classes principales (Compte, CompteCourant, CompteEpargne, Operation, etc.)
├── utils/           → Classes utilitaires (validations, formatage, etc.)

````

### Classes principales
- **Compte** *(abstraite)* : `code`, `solde`, `listeOperations`  
- **CompteCourant** : + `decouvert`  
- **CompteEpargne** : + `tauxInteret`  
- **Operation** *(abstraite)* : `numero`, `date`, `montant`  
- **Versement** : + `source`  
- **Retrait** : + `destination`

---

## 📋 Prérequis
Avant de lancer l’application, assurez-vous d’avoir :
- Installé **JDK 1.8**
- Installé **Eclipse IDE** (ou tout autre IDE compatible Java 8)
- Git pour cloner le projet et exécuter les commandes
- Un terminal pour compiler et exécuter avec `javac` et `java`

---

## 🚀 Exécution
1. Cloner le dépôt GitHub :
   ```bash
   git clone https://github.com/Kawtar-Shaimi/gestion-comptes-bancaires.git
   cd gestion-comptes-bancaires
````

2. Compiler les fichiers :

   ```bash
   javac -d bin src/**/*.java
   ```

3. Exécuter le programme :

   ```bash
   java -cp bin ui.App
   ```

4. Générer le fichier **.jar** (optionnel) :

   ```bash
   jar cfe gestionDesComptesBancaires.jar App -C bin .
   ```

---

## 📸 Captures d’écran

*(Ajouter vos captures ici)*

---

## ✅ Critères de performance

* Application développée exclusivement en **Java 8**
* Toutes les fonctionnalités de base implémentées
* Architecture en couches respectée
* Code propre, lisible, bien commenté et conforme aux conventions Java
* Utilisation de Git avec des commits réguliers et descriptifs
* README complet et clair
* Diagramme de classes fidèle au code source


