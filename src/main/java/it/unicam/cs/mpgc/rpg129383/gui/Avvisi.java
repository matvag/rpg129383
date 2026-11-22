package it.unicam.cs.mpgc.rpg129383.gui;

import javafx.scene.control.Alert;

/**
 * Mostra le finestre di avviso al giocatore
 */
public final class Avvisi {

    private Avvisi() {
    }

    public static void info(String titolo, String messaggio) {
        mostra(Alert.AlertType.INFORMATION, titolo, messaggio);
    }

    public static void errore(String messaggio) {
        mostra(Alert.AlertType.ERROR, "Errore", messaggio);
    }

    private static void mostra(Alert.AlertType tipo, String titolo, String messaggio) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(messaggio);
        alert.showAndWait();
    }
}
