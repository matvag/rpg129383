package it.unicam.cs.mpgc.rpg129383.model.stanze;

import it.unicam.cs.mpgc.rpg129383.enums.TipoStanza;
import it.unicam.cs.mpgc.rpg129383.interfaces.Stanza;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;

/**
 *  Recuperi la metà della vita totale in più a quella rimasta
 */

public class StanzaRiposo implements Stanza{

    @Override
    public TipoStanza getTipo() {
        return TipoStanza.RIPOSO;
    }

    @Override
    public String entra(Eroe eroe){
        int vitaPrima = eroe.getPuntiVita();
        eroe.cura(eroe.getPuntiVitaMax()/2);
        int vitaRecuperata = eroe.getPuntiVita() - vitaPrima;
        return "Trovi un falò e recuperi " + vitaRecuperata + " punti vita";
    }
}
