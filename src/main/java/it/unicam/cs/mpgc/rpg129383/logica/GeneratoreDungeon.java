package it.unicam.cs.mpgc.rpg129383.logica;

import it.unicam.cs.mpgc.rpg129383.enums.TipoNemico;
import it.unicam.cs.mpgc.rpg129383.interfaces.Oggetto;
import it.unicam.cs.mpgc.rpg129383.interfaces.Stanza;
import it.unicam.cs.mpgc.rpg129383.model.Arma;
import it.unicam.cs.mpgc.rpg129383.model.Nemico;
import it.unicam.cs.mpgc.rpg129383.model.Pozione;
import it.unicam.cs.mpgc.rpg129383.model.stanze.StanzaBoss;
import it.unicam.cs.mpgc.rpg129383.model.stanze.StanzaCombattimento;
import it.unicam.cs.mpgc.rpg129383.model.stanze.StanzaRiposo;
import it.unicam.cs.mpgc.rpg129383.model.stanze.StanzaTesoro;
import it.unicam.cs.mpgc.rpg129383.utils.Dado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Crea in modo casuale le stanze del dungeon. L'ultima stanza è sempre quella del boss
 */
public class GeneratoreDungeon {
    private static final TipoNemico BOSS = TipoNemico.DRAGO;
    private static final int PROBABILITA_COMBATTIMENTO = 60;
    private static final int PROBABILITA_TESORO = 20;
    private static final int MINIMO_ORO = 10;
    private static final int MASSIMO_ORO = 40;

    private static final List<Oggetto> TESORI = List.of(
            new Pozione("Pozione piccola", 20,10),
            new Pozione ("Pozione grande", 50, 25),
            new Arma("Spada Corta", 3, 30),
            new Arma("Spada Media", 6, 60),
            new Arma("Spada Lunga", 9, 90)
    );
    private final Dado dado;
    private final List<TipoNemico> nemiciComuni;

    public GeneratoreDungeon(Dado dado) {
        this.dado = dado;
        this.nemiciComuni = Arrays.stream(TipoNemico.values())
                .filter(tipo -> tipo != BOSS)
                .toList();
    }

    public List<Stanza> genera(int numeroStanze){
        if(numeroStanze < 1){
            throw new IllegalArgumentException("Numero stanze non valido almeno una stanza");
        }
        List<Stanza> stanze = new ArrayList<>();
        for (int i = 0; i < numeroStanze - 1; i++) {
            if (i == 0) {
                stanze.add(creaStanzaCombattimento());
            } else {
                stanze.add(creaStanzaCasuale());
            }
        }
        stanze.add(new StanzaBoss(new Nemico(BOSS)));
        return stanze;
    }

    private Stanza creaStanzaCasuale() {
        int tiro = dado.tira(1,100);
        if (tiro <= PROBABILITA_COMBATTIMENTO) {
            return creaStanzaCombattimento();
        }
        if (tiro <= PROBABILITA_COMBATTIMENTO + PROBABILITA_TESORO) {
            int oro = dado.tira(MINIMO_ORO, MASSIMO_ORO);
            return new StanzaTesoro(scegliCasuale(TESORI), oro);
        }
        return new StanzaRiposo();
    }

    private Stanza creaStanzaCombattimento(){
        return new StanzaCombattimento(new Nemico(scegliCasuale(nemiciComuni)));
    }

    private <T> T scegliCasuale(List<T> elementi) {
        return elementi.get(dado.tira(0, elementi.size() - 1));
    }
}
