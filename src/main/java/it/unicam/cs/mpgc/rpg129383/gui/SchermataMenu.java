package it.unicam.cs.mpgc.rpg129383.gui;

import it.unicam.cs.mpgc.rpg129383.interfaces.Navigatore;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SchermataMenu extends VBox{

    public SchermataMenu(Navigatore navigatore, boolean esisteSalvataggio) {
        super(18);
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("schermata");

        Label titolo = new Label("Dungeon RPG");
        titolo.getStyleClass().add("titolo");
        Label sottotitolo = new Label("Attraversa il dungeon e uccidi il BOSS FINALE");

        Button nuovaPartita = new Button("Nuova Partita");
        nuovaPartita.setOnAction(evento -> navigatore.mostraCreazioneEroe());

        Button caricaPartita = new Button("Carica Partita");
        caricaPartita.setDisable(!esisteSalvataggio);
        caricaPartita.setOnAction(evento -> navigatore.caricaPartita());

        Button esci = new Button("Esci");
        esci.setOnAction(evento -> Platform.exit());

        getChildren().addAll(titolo, sottotitolo, nuovaPartita, caricaPartita, esci);

    }
}
