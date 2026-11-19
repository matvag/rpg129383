package it.unicam.cs.mpgc.rpg129383.model.stanze;


import it.unicam.cs.mpgc.rpg129383.enums.TipoStanza;
import it.unicam.cs.mpgc.rpg129383.interfaces.Stanza;
import it.unicam.cs.mpgc.rpg129383.interfaces.Oggetto;
import it.unicam.cs.mpgc.rpg129383.interfaces.Stanza;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;

public class StanzaTesoro implements Stanza {
    public final Oggetto oggetto;
    public final int oro;

    public StanzaTesoro(Oggetto oggetto, int oro) {
        this.oggetto = oggetto;
        this.oro = oro;
    }

    @Override
    public TipoStanza getTipo() {
        return TipoStanza.RIPOSO;
    }

    @Override
    public String entra(Eroe eroe) {
        eroe.aggiungiOro(oro);
        if (eroe.getInventario().aggiungiOggetto(oggetto)) {
            return "Apri un forziere: trovi " + oggetto.getNome() + " e " + oro + " oro";
        }
        return "Apri un forziere: trovi " + oro + " oro, ma il tuo inventario è pieno e lasci "
                + oggetto.getNome();
    }
}