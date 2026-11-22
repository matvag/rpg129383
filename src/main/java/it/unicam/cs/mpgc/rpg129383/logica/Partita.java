package it.unicam.cs.mpgc.rpg129383.logica;

import it.unicam.cs.mpgc.rpg129383.enums.StatoCombattimento;
import it.unicam.cs.mpgc.rpg129383.interfaces.Stanza;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Nemico;
import it.unicam.cs.mpgc.rpg129383.utils.Dado;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * L'eroe attraversa le stanze del dungeon una dopo l'altra
 * Non si può passare alla stanza successiva finché c'è un combattimento in corso
 * La partita è vinta se l'eroe supera l'ultima stanza sconfiggendo il boss
 */
public class Partita {
    private final Eroe eroe;
    private final List<Stanza> stanze;
    private final Dado dado;
    private int indiceStanza;
    private Combattimento combattimento;

    public Partita(Eroe eroe, List<Stanza> stanze, Dado dado) {
        if (stanze.isEmpty()) {
            throw new IllegalStateException("Il dungeon deve avere almeno una stanza");
        }
        this.eroe = eroe;
        this.stanze = new ArrayList<>(stanze);
        this.dado = dado;
        this.indiceStanza = -1;
        this.combattimento = null;
    }

    /**
     *  Fa entrare l'eroe nella stanza successiva. Se nella stanza c'è un nemico
     *  inizia un nuovo combattimento.
     */

    public String avanza(){
        if(!puoAvanzare()){
            throw new IllegalArgumentException("Non puoi avanzare ancora");
        }
        indiceStanza++;
        Stanza stanza = stanze.get(indiceStanza);
        String descrizione = stanza.entra(eroe);
        combattimento = stanza.getNemico()
                .map(this::creaCombattimento)
                .orElse(null);
        return descrizione;
    }


    private Combattimento creaCombattimento(Nemico nemico) {
        return new Combattimento(eroe, nemico, dado);
    }

    public boolean puoAvanzare() {
        return eroe.isVivo() && !isInCombattimento() && !isUltimaStanza();
    }

    public boolean isInCombattimento() {
        return combattimento != null && !combattimento.isTerminato();
    }

    public boolean isFinita(){
        return (!isInCombattimento() && isUltimaStanza()) || isPersa();
    }
    public boolean isPersa() {
        return !eroe.isVivo();
    }
    public boolean isVinta(){
        if(!isFinita() || isPersa()){
            return false;
        }
        return combattimento == null || combattimento.getStato() == StatoCombattimento.VITTORIA;
    }

    private boolean isUltimaStanza() {
        return indiceStanza == stanze.size() - 1;
    }

    public Optional<Combattimento> getCombattimento() {
        return Optional.ofNullable(combattimento);
    }

    public Optional<Stanza> getStanzaCorrente() {
        if (indiceStanza < 0) {
            return Optional.empty();
        }
        return Optional.of(stanze.get(indiceStanza));
    }

    public int getNumeroStanzaCorrente() {
        return indiceStanza + 1;
    }

    public int getNumeroStanze() {
        return stanze.size();
    }

    public Eroe getEroe() {
        return eroe;
    }

}
