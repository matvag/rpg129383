package it.unicam.cs.mpgc.rpg129383.gui;

import it.unicam.cs.mpgc.rpg129383.interfaces.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129383.interfaces.Navigatore;
import it.unicam.cs.mpgc.rpg129383.interfaces.Oggetto;
import it.unicam.cs.mpgc.rpg129383.logica.Combattimento;
import it.unicam.cs.mpgc.rpg129383.logica.Partita;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Nemico;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Schermata principale del gioco: mostra stato dell'eroe e nemico se c'è,
 * il racconto di ciò che accade e i comandi disponibili
 */
public class SchermataGioco extends BorderPane {

    private final Navigatore navigatore;
    private final Partita partita;
    private final GestoreSalvataggi salvataggi;

    private final Label etichettaEroe = new Label();
    private final ProgressBar vitaEroe = new ProgressBar();
    private final Label statisticheEroe = new Label();
    private final Label etichettaNemico = new Label();
    private final ProgressBar vitaNemico = new ProgressBar();
    private final VBox pannelloNemico = new VBox(6, etichettaNemico, vitaNemico);
    private final Label etichettaStanza = new Label();
    private final TextArea racconto = new TextArea();
    private final ListView<Oggetto> inventario = new ListView<>();

    private final Button avanza = new Button("Avanza");
    private final Button attacca = new Button("Attacca");
    private final Button fuggi = new Button("Fuggi");
    private final Button usaOggetto = new Button("Usa oggetto");
    private final Button salva = new Button("Salva");
    private final Button menu = new Button("Menu");

    private int messaggiMostrati;
    private boolean fineAnnunciata;

    public SchermataGioco(Navigatore navigatore, Partita partita, GestoreSalvataggi salvataggi) {
        this.navigatore = navigatore;
        this.partita = partita;
        this.salvataggi = salvataggi;
        getStyleClass().add("schermata");
        setPadding(new Insets(16));

        setTop(creaPannelloStato());
        setCenter(creaRacconto());
        setRight(creaPannelloInventario());
        setBottom(creaBottoni());

        scrivi("Benvenuto, " + partita.getEroe().getNome() + "! Davanti a te si apre il dungeon...");
        aggiorna();
    }


    private VBox creaPannelloStato() {
        vitaEroe.setPrefWidth(300);
        vitaNemico.setPrefWidth(300);
        vitaNemico.getStyleClass().add("vita-nemico");
        etichettaEroe.getStyleClass().add("nome");
        etichettaNemico.getStyleClass().add("nome");
        etichettaStanza.getStyleClass().add("stanza");

        VBox pannelloEroe = new VBox(6, etichettaEroe, vitaEroe, statisticheEroe);
        HBox personaggi = new HBox(40, pannelloEroe, pannelloNemico);
        VBox stato = new VBox(10, etichettaStanza, personaggi);
        stato.setPadding(new Insets(0, 0, 12, 0));
        return stato;
    }

    private TextArea creaRacconto() {
        racconto.setEditable(false);
        racconto.setWrapText(true);
        return racconto;
    }

    private VBox creaPannelloInventario() {
        inventario.setPrefWidth(260);
        usaOggetto.setMaxWidth(Double.MAX_VALUE);
        usaOggetto.setOnAction(evento -> usaOggettoSelezionato());

        VBox pannello = new VBox(8, new Label("Inventario"), inventario, usaOggetto);
        VBox.setVgrow(inventario, Priority.ALWAYS);
        pannello.setPadding(new Insets(0, 0, 0, 12));
        return pannello;
    }

    private HBox creaBottoni() {
        avanza.setOnAction(evento -> avanza());
        attacca.setOnAction(evento -> attacca());
        fuggi.setOnAction(evento -> fuggi());
        salva.setOnAction(evento -> salva());
        menu.setOnAction(evento -> navigatore.mostraMenu());

        HBox bottoni = new HBox(12, avanza, attacca, fuggi, salva, menu);
        bottoni.setAlignment(Pos.CENTER);
        bottoni.setPadding(new Insets(12, 0, 0, 0));
        return bottoni;
    }



    private void avanza() {
        scrivi("");
        scrivi(partita.avanza());
        messaggiMostrati = 0;
        partita.getCombattimento().ifPresent(combattimento -> mostraNuoviMessaggi());
        aggiorna();
    }

    private void attacca() {
        combattimentoCorrente().attacca();
        mostraNuoviMessaggi();
        aggiorna();
    }

    private void fuggi() {
        combattimentoCorrente().fuggi();
        mostraNuoviMessaggi();
        aggiorna();
    }

    private void usaOggettoSelezionato() {
        Oggetto oggetto = inventario.getSelectionModel().getSelectedItem();
        if (oggetto == null) {
            Avvisi.info("Inventario", "Seleziona prima un oggetto dall'inventario");
            return;
        }
        if (partita.isInCombattimento()) {
            combattimentoCorrente().usaOggetto(oggetto);
            mostraNuoviMessaggi();
        } else {
            partita.getEroe().usaOggetto(oggetto);
            scrivi("Usi " + oggetto.getNome());
        }
        aggiorna();
    }

    private void salva() {
        try {
            salvataggi.salva(partita.getEroe());
            scrivi("Eroe salvato.");
        } catch (RuntimeException e) {
            Avvisi.errore("Impossibile salvare: " + e.getMessage());
        }
    }

    private void aggiorna() {
        aggiornaEroe();
        aggiornaNemico();
        aggiornaStanza();
        inventario.getItems().setAll(partita.getEroe().getInventario().getOggetti());
        aggiornaBottoni();
        controllaFinePartita();
    }

    private void aggiornaEroe() {
        Eroe eroe = partita.getEroe();
        String arma = eroe.getArmaEquipaggiata() == null ? "nessuna" : eroe.getArmaEquipaggiata().getNome();
        etichettaEroe.setText(eroe.toString());
        vitaEroe.setProgress((double) eroe.getPuntiVita() / eroe.getPuntiVitaMax());
        statisticheEroe.setText("Attacco " + eroe.getAttacco() + "   Difesa " + eroe.getDifesa()
                + "   Oro " + eroe.getOro()
                + "\nEsperienza " + eroe.getEsperienza() + "/" + eroe.getEsperienzaPerProssimoLivello()
                + "   Arma: " + arma);
    }

    private void aggiornaNemico() {
        pannelloNemico.setVisible(partita.isInCombattimento());
        if (partita.isInCombattimento()) {
            Nemico nemico = combattimentoCorrente().getNemico();
            etichettaNemico.setText(nemico.toString());
            vitaNemico.setProgress((double) nemico.getPuntiVita() / nemico.getPuntiVitaMax());
        }
    }

    private void aggiornaStanza() {
        String tipoStanza = partita.getStanzaCorrente()
                .map(stanza -> stanza.getTipo().getNome())
                .orElse("Ingresso");
        etichettaStanza.setText("Stanza " + partita.getNumeroStanzaCorrente() + " di "
                + partita.getNumeroStanze() + " – " + tipoStanza);
    }

    private void aggiornaBottoni() {
        boolean inCombattimento = partita.isInCombattimento();
        avanza.setDisable(!partita.puoAvanzare());
        attacca.setDisable(!inCombattimento);
        fuggi.setDisable(!inCombattimento);
        usaOggetto.setDisable(partita.isFinita());
        salva.setDisable(inCombattimento || partita.isPersa());
    }

    private void controllaFinePartita() {
        if (!partita.isFinita() || fineAnnunciata) {
            return;
        }
        fineAnnunciata = true;
        if (partita.isVinta()) {
            Avvisi.info("Vittoria!", "Hai sconfitto il drago e conquistato il dungeon!");
        } else if (partita.isPersa()) {
            Avvisi.info("Sconfitta", "Il tuo eroe è caduto nel dungeon...");
        } else {
            Avvisi.info("Fine", "Sei fuggito dal drago: il dungeon resta invitto.");
        }
    }


    private Combattimento combattimentoCorrente() {
        return partita.getCombattimento().orElseThrow();
    }

    /**
     * Scrive nel racconto i messaggi del combattimento non ancora mostrati
     */
    private void mostraNuoviMessaggi() {
        combattimentoCorrente().getRegistro().stream()
                .skip(messaggiMostrati)
                .forEach(this::scrivi);
        messaggiMostrati = combattimentoCorrente().getRegistro().size();
    }

    private void scrivi(String messaggio) {
        racconto.appendText(messaggio + "\n");
    }
}