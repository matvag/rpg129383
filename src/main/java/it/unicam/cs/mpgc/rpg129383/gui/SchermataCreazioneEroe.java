package it.unicam.cs.mpgc.rpg129383.gui;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;
import it.unicam.cs.mpgc.rpg129383.interfaces.Navigatore;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class SchermataCreazioneEroe extends VBox {
    private final static int LARGHEZZA_CAMPO_NOME = 240;

    private final Navigatore navigatore;
    private final TextField campoNome;
    private final ToggleGroup gruppoClassi;

    public SchermataCreazioneEroe(Navigatore navigatore) {
        super(18);
        this.navigatore = navigatore;
        campoNome = new TextField();
        gruppoClassi = new ToggleGroup();
        setAlignment(Pos.CENTER);
        getStyleClass().add("schermata");

        Label titolo = new Label("Creazione Eroe");
        titolo.getStyleClass().add("Titolo");

        campoNome.setPromptText("Nome dell'eroe");
        campoNome.setMaxWidth(LARGHEZZA_CAMPO_NOME);

        getChildren().addAll(titolo,new Label("Nome"), campoNome, new Label("Classi"), creaSceltaEroe(), creaBottoni());
    }

    private VBox creaSceltaEroe() {
        VBox scelte = new VBox(8);
        scelte.setAlignment(Pos.CENTER);
        scelte.maxWidth(LARGHEZZA_CAMPO_NOME);
        for(TipoEroe tipo : TipoEroe.values()) {
            RadioButton scelta = new RadioButton(tipo.getNome() + " - " + tipo.getDescrizione());
            scelta.setUserData(tipo);
            scelta.setToggleGroup(gruppoClassi);
            scelte.getChildren().add(scelta);
        }
        gruppoClassi.getToggles().getFirst().setSelected(true);
        return scelte;
    }

    private HBox creaBottoni() {
        Button indietro = new Button("Indietro");
        indietro.setOnAction(evento -> navigatore.mostraMenu());

        Button inizia = new Button("Inizia");
        inizia.setDefaultButton(true);
        inizia.setOnAction(evento -> iniziaPartita());

        HBox bottoni = new HBox(12, indietro, inizia);
        bottoni.setAlignment(Pos.CENTER);
        return bottoni;
    }

    private void iniziaPartita() {
        String nome = campoNome.getText().strip();
        if(nome.isEmpty()){
            Avvisi.errore("Inserisci un nome");
            return;
        }
        TipoEroe tipo = (TipoEroe) gruppoClassi.getSelectedToggle().getUserData();
        navigatore.nuovaPartita(nome, tipo);
    }


}
