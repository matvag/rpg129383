package it.unicam.cs.mpgc.rpg129383.logica;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;
import it.unicam.cs.mpgc.rpg129383.model.Guerriero;
import it.unicam.cs.mpgc.rpg129383.model.Ladro;
import it.unicam.cs.mpgc.rpg129383.model.Mago;

public final class FabbricaEroi {

    private FabbricaEroi() {
    }

    public static Eroe crea(TipoEroe tipo, String nome){
        return switch(tipo) {
        case GUERRIERO -> new Guerriero(nome);
        case MAGO -> new Mago(nome);
        case LADRO -> new Ladro(nome);
        };
    }
}
