package it.unicam.cs.mpgc.rpg129383.model.stanze;

import it.unicam.cs.mpgc.rpg129383.enums.TipoStanza;
import it.unicam.cs.mpgc.rpg129383.interfaces.Stanza;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Nemico;

import java.util.Optional;

public class StanzaCombattimento implements Stanza{

    private final Nemico nemico;

    public StanzaCombattimento(Nemico nemico) {
        this.nemico = nemico;
    }

    @Override
    public TipoStanza getTipo() {
        return TipoStanza.COMBATTIMENTO;
    }

    @Override
    public String entra(Eroe eroe){
        return "Senti dei passi...un nemico si sta avvicinando";
    }

    @Override
    public Optional<Nemico> getNemico() {
        return Optional.of(nemico);
    }
}
