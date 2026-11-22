package it.unicam.cs.mpgc.rpg129383.gui;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;
import it.unicam.cs.mpgc.rpg129383.interfaces.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129383.interfaces.Navigatore;
import it.unicam.cs.mpgc.rpg129383.logica.FabbricaEroi;
import it.unicam.cs.mpgc.rpg129383.logica.GeneratoreDungeon;
import it.unicam.cs.mpgc.rpg129383.logica.Partita;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Pozione;
import it.unicam.cs.mpgc.rpg129383.persistenza.SalvataggioJson;
import it.unicam.cs.mpgc.rpg129383.utils.Dado;
import it.unicam.cs.mpgc.rpg129383.interfaces.Stanza;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 *  apre la finestra e gestisce il passaggio tra le schermate
 */
public class AppDungeon extends Application implements Navigatore {

    private static final int LARGHEZZA = 960;
    private static final int ALTEZZA = 640;
    private static final int NUMERO_STANZE = 8;

    private final GestoreSalvataggi salvataggi = new SalvataggioJson();
    private final Dado dado = new Dado();
    private Scene scena;

    @Override
    public void start(Stage finestra) {
        scena = new Scene(new SchermataMenu(this, salvataggi.esisteSalvataggio()), LARGHEZZA, ALTEZZA);
        scena.getStylesheets().add(getClass().getResource("/stile.css").toExternalForm());
        finestra.setTitle("Dungeon RPG");
        finestra.setScene(scena);
        finestra.show();
    }

    @Override
    public void mostraMenu() {
        mostra(new SchermataMenu(this, salvataggi.esisteSalvataggio()));
    }

    @Override
    public void mostraCreazioneEroe() {
        mostra(new SchermataCreazioneEroe(this));
    }

    @Override
    public void nuovaPartita(String nome, TipoEroe tipo) {
        Eroe eroe = FabbricaEroi.crea(tipo, nome);
        eroe.getInventario().aggiungiOggetto(new Pozione("Pozione piccola", 20, 10));
        iniziaPartita(eroe);
    }

    @Override
    public void caricaPartita() {
        try {
            salvataggi.carica().ifPresent(this::iniziaPartita);
        } catch (RuntimeException e) {
            Avvisi.errore("Impossibile caricare il salvataggio: " + e.getMessage());
        }
    }

    private void iniziaPartita(Eroe eroe) {
        Partita partita = new Partita(eroe, new GeneratoreDungeon(dado).genera(NUMERO_STANZE), dado);
        mostra(new SchermataGioco(this, partita, salvataggi));
    }

    private void mostra(Parent schermata) {
        scena.setRoot(schermata);
    }
}