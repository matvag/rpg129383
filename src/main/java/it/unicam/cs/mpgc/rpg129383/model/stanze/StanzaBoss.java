package it.unicam.cs.mpgc.rpg129383.model.stanze;

import it.unicam.cs.mpgc.rpg129383.enums.TipoStanza;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Nemico;

public class StanzaBoss extends StanzaCombattimento{

    public StanzaBoss(Nemico boss) {
        super(boss);
    }
    @Override
    public TipoStanza getTipo() {
        return TipoStanza.BOSS;
    }

   @Override
    public String entra(Eroe eroe){
        return "Sei arrivato al dungeon finale:  "
                + getNemico().orElseThrow().getNome() + " ti attende!";
    }

}
