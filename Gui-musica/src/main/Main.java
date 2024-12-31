import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestoreMusica gestore = new GestoreMusica();

        // Carica i dati da un file esistente, se presente
        try {
            gestore.caricaDatiDaFile("dati.txt");
        } catch (IOException e) {
            System.out.println("Errore nel caricamento dei dati.");
        }

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Gestione Canzoni");
            System.out.println("2. Gestione Playlist");
            System.out.println("3. Ricerca Canzone");
            System.out.println("4. Salvataggio Dati");
            System.out.println("5. Esci");

            System.out.print("Scegli un'opzione: ");
            int scelta = scanner.nextInt();
            scanner.nextLine();  // Pulisce il buffer

            switch (scelta) {
                case 1:
                    gestisciCanzoni(scanner, gestore);
                    break;
                case 2:
                    gestisciPlaylist(scanner, gestore);
                    break;
                case 3:
                    ricercaCanzone(scanner, gestore);
                    break;
                case 4:
                    try {
                        gestore.salvaDatiSuFile("dati.txt");
                        System.out.println("Dati salvati.");
                    } catch (IOException e) {
                        System.out.println("Errore nel salvataggio dei dati.");
                    }
                    break;
                case 5:
                    System.out.println("Uscita...");
                    return;
                default:
                    System.out.println("Opzione non valida.");
            }
        }
    }

    private static void gestisciCanzoni(Scanner scanner, GestoreMusica gestore) {
        System.out.println("\nGestione Canzoni:");
        System.out.println("1. Aggiungi una nuova canzone");
        System.out.println("2. Elimina una canzone");
        System.out.println("3. Mostra tutte le canzoni");
        System.out.println("4. Ordina le canzoni");
        System.out.print("Scegli un'opzione: ");
        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                aggiungiCanzone(scanner, gestore);
                break;
            case 2:
                eliminaCanzone(scanner, gestore);
                break;
            case 3:
                gestore.mostraTutteLeCanzoni();
                break;
            case 4:
                ordinaCanzoni(scanner, gestore);
                break;
            default:
                System.out.println("Opzione non valida.");
        }
    }

    private static void aggiungiCanzone(Scanner scanner, GestoreMusica gestore) {
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Artista: ");
        String artista = scanner.nextLine();
        System.out.print("Durata (secondi): ");
        int durata = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Genere: ");
        String genere = scanner.nextLine();

        Canzone canzone = new Canzone(titolo, artista, durata, genere);
        gestore.aggiungiCanzone(canzone);
        System.out.println("Canzone aggiunta.");
    }

    private static void eliminaCanzone(Scanner scanner, GestoreMusica gestore) {
        System.out.print("Titolo della canzone da eliminare: ");
        String titolo = scanner.nextLine();
        Canzone canzone = gestore.cercaCanzonePerTitolo(titolo);
        if (canzone != null) {
            gestore.rimuoviCanzone(canzone);
            System.out.println("Canzone eliminata.");
        } else {
            System.out.println("Canzone non trovata.");
        }
    }

    private static void ordinaCanzoni(Scanner scanner, GestoreMusica gestore) {
        System.out.println("1. Ordina per titolo");
        System.out.println("2. Ordina per artista");
        System.out.println("3. Ordina per durata");
        System.out.print("Scegli un'opzione: ");
        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                gestore.ordinaCanzoniPerTitolo();
                break;
            case 2:
                gestore.ordinaCanzoniPerArtista();
                break;
            case 3:
                gestore.ordinaCanzoniPerDurata();
                break;
            default:
                System.out.println("Opzione non valida.");
        }
    }

    private static void gestisciPlaylist(Scanner scanner, GestoreMusica gestore) {
        System.out.println("\nGestione Playlist:");
        System.out.println("1. Crea una nuova playlist");
        System.out.println("2. Aggiungi una canzone a una playlist");
        System.out.println("3. Rimuovi una canzone da una playlist");
        System.out.println("4. Mostra tutte le playlist");
        System.out.print("Scegli un'opzione: ");
        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                creaPlaylist(scanner, gestore);
                break;
            case 2:
                aggiungiCanzoneAPlaylist(scanner, gestore);
                break;
            case 3:
                rimuoviCanzoneDaPlaylist(scanner, gestore);
                break;
            case 4:
                gestore.mostraTutteLePlaylist();
                break;
            default:
                System.out.println("Opzione non valida.");
        }
    }

    private static void creaPlaylist(Scanner scanner, GestoreMusica gestore) {
        System.out.print("Nome della playlist: ");
        String nome = scanner.nextLine();
        gestore.creaPlaylist(nome);
        System.out.println("Playlist creata.");
    }

    private static void aggiungiCanzoneAPlaylist(Scanner scanner, GestoreMusica gestore) {
        System.out.print("Nome della playlist: ");
        String nomePlaylist = scanner.nextLine();
        System.out.print("Titolo della canzone: ");
        String titoloCanzone = scanner.nextLine();
        Canzone canzone = gestore.cercaCanzonePerTitolo(titoloCanzone);
        if (canzone != null) {
            gestore.aggiungiCanzoneAPlaylist(nomePlaylist, canzone);
            System.out.println("Canzone aggiunta alla playlist.");
        } else {
            System.out.println("Canzone non trovata.");
        }
    }

    private static void rimuoviCanzoneDaPlaylist(Scanner scanner, GestoreMusica gestore) {
        System.out.print("Nome della playlist: ");
        String nomePlaylist = scanner.nextLine();
        System.out.print("Titolo della canzone: ");
        String titoloCanzone = scanner.nextLine();
        Canzone canzone = gestore.cercaCanzonePerTitolo(titoloCanzone);
        if (canzone != null) {
            gestore.rimuoviCanzoneDaPlaylist(nomePlaylist, canzone);
            System.out.println("Canzone rimossa dalla playlist.");
        } else {
            System.out.println("Canzone non trovata.");
        }
    }

    private static void ricercaCanzone(Scanner scanner, GestoreMusica gestore) {
        System.out.println("1. Cerca per titolo");
        System.out.println("2. Cerca per artista");
        System.out.print("Scegli un'opzione: ");
        int scelta = scanner.nextInt();
        scanner.nextLine();

        switch (scelta) {
            case 1:
                System.out.print("Inserisci il titolo della canzone: ");
                String titolo = scanner.nextLine();
                Canzone canzone = gestore.cercaCanzonePerTitolo(titolo);
                if (canzone != null) {
                    System.out.println(canzone);
                } else {
                    System.out.println("Canzone non trovata.");
                }
                break;
            case 2:
                System.out.print("Inserisci l'artista: ");
                String artista = scanner.nextLine();
                Canzone canzoneArtista = gestore.cercaCanzonePerArtista(artista);
                if (canzoneArtista != null) {
                    System.out.println(canzoneArtista);
                } else {
                    System.out.println("Canzone non trovata.");
                }
                break;
            default:
                System.out.println("Opzione non valida.");
        }
    }
}
