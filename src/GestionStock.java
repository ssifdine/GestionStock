import java.util.Scanner;

public class GestionStock {

    // Limite de produits
    private static final int MAX_PRODUITS = 100;

    // Tableaux parallèles
    private static int[] codesProduits = new int[MAX_PRODUITS];
    private static String[] nomsProduits = new String[MAX_PRODUITS];
    private static int[] quantites = new int[MAX_PRODUITS];
    private static double[] prix = new double[MAX_PRODUITS];

    // Nombre de produits dans le stock
    private static int nombreProduits = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    ajouterProduit(scanner);
                    break;
                case 2:
                    modifierProduit(scanner);
                    break;
                case 3:
                    supprimerProduit(scanner);
                    break;
                case 4:
                    afficherProduits();
                    break;
                case 5:
                    rechercherProduit(scanner);
                    break;
                case 6:
                    calculerValeurStock();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Réessayez.");
            }
        } while (choix != 0);

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("\n--- Gestion de Stock ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Afficher la liste des produits");
        System.out.println("5. Rechercher un produit");
        System.out.println("6. Calculer la valeur totale du stock");
        System.out.println("0. Quitter");
        System.out.print("Choisissez une option: ");
    }

    public static void ajouterProduit(Scanner scanner) {
        if (nombreProduits >= MAX_PRODUITS) {
            System.out.println("Stock plein. Impossible d'ajouter plus de produits.");
            return;
        }

        System.out.print("Code du produit: ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        System.out.print("Nom du produit: ");
        String nom = scanner.nextLine();

        System.out.print("Quantité: ");
        int quantite = scanner.nextInt();

        System.out.print("Prix unitaire: ");
        double prixUnitaire = scanner.nextDouble();

        codesProduits[nombreProduits] = code;
        nomsProduits[nombreProduits] = nom;
        quantites[nombreProduits] = quantite;
        prix[nombreProduits] = prixUnitaire;
        nombreProduits++;

        System.out.println("Produit ajouté avec succès !");
    }

    public static void modifierProduit(Scanner scanner) {
        System.out.print("Code du produit à modifier: ");
        int code = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        int index = trouverIndexParCode(code);
        if (index == -1) {
            System.out.println("Produit introuvable.");
            return;
        }

        System.out.print("Nouveau nom: ");
        String nouveauNom = scanner.nextLine();

        System.out.print("Nouvelle quantité: ");
        int nouvelleQuantite = scanner.nextInt();

        System.out.print("Nouveau prix unitaire: ");
        double nouveauPrix = scanner.nextDouble();

        nomsProduits[index] = nouveauNom;
        quantites[index] = nouvelleQuantite;
        prix[index] = nouveauPrix;

        System.out.println("Produit modifié avec succès !");
    }

    public static void supprimerProduit(Scanner scanner) {
        System.out.print("Code du produit à supprimer: ");
        int code = scanner.nextInt();

        int index = trouverIndexParCode(code);
        if (index == -1) {
            System.out.println("Produit introuvable.");
            return;
        }

        for (int i = index; i < nombreProduits - 1; i++) {
            codesProduits[i] = codesProduits[i + 1];
            nomsProduits[i] = nomsProduits[i + 1];
            quantites[i] = quantites[i + 1];
            prix[i] = prix[i + 1];
        }

        nombreProduits--;
        System.out.println("Produit supprimé avec succès !");
    }

    public static void afficherProduits() {
        if (nombreProduits == 0) {
            System.out.println("Aucun produit en stock.");
            return;
        }

        System.out.println("\nListe des produits:");
        for (int i = 0; i < nombreProduits; i++) {
            System.out.printf("Code: %d | Nom: %s | Quantité: %d | Prix: %.2f\n",
                    codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
        }
    }

    public static void rechercherProduit(Scanner scanner) {
        System.out.print("Nom du produit à rechercher: ");
        String nom = scanner.nextLine();

        boolean trouve = false;
        for (int i = 0; i < nombreProduits; i++) {
            if (nomsProduits[i].equalsIgnoreCase(nom)) {
                System.out.printf("Produit trouvé - Code: %d | Nom: %s | Quantité: %d | Prix: %.2f\n",
                        codesProduits[i], nomsProduits[i], quantites[i], prix[i]);
                trouve = true;
                break;
            }
        }

        if (!trouve) {
            System.out.println("Produit introuvable.");
        }
    }

    public static void calculerValeurStock() {
        double valeurTotale = 0;

        for (int i = 0; i < nombreProduits; i++) {
            valeurTotale += quantites[i] * prix[i];
        }

        System.out.printf("Valeur totale du stock: %.2f\n", valeurTotale);
    }

    private static int trouverIndexParCode(int code) {
        for (int i = 0; i < nombreProduits; i++) {
            if (codesProduits[i] == code) {
                return i;
            }
        }
        return -1;
    }
}
