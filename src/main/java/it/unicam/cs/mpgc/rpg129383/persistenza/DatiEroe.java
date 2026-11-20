package it.unicam.cs.mpgc.rpg129383.persistenza;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;
import it.unicam.cs.mpgc.rpg129383.logica.FabbricaEroi;
import it.unicam.cs.mpgc.rpg129383.model.Arma;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;

import java.util.List;

/**
 * Copia semplice dell'eroe pronta per essere scritta nel file JSON
 * Non viene salvato direttamente l'eroe perché è un classe astratta e non si saprebbe il tipo
 */
public class DatiEroe {

    private String nome;
    private TipoEroe tipo;
    private int livello;
    private int esperienza;
    private int oro;
    private int puntiVita;
    private List<DatiOggetto> inventario;
    private DatiOggetto armaEquipaggiata;


    private DatiEroe() {
    }

    public DatiEroe(Eroe eroe) {
        this.nome = eroe.getNome();
        this.tipo = eroe.getTipo();
        this.livello = eroe.getLivello();
        this.esperienza = eroe.getEsperienza();
        this.oro = eroe.getOro();
        this.puntiVita = eroe.getPuntiVita();
        this.inventario = eroe.getInventario().getOggetti().stream()
                .map(DatiOggetto::new)
                .toList();
        if (eroe.getArmaEquipaggiata() != null) {
            this.armaEquipaggiata = new DatiOggetto(eroe.getArmaEquipaggiata());
        }
    }


    public Eroe creaEroe() {
        Eroe eroe = FabbricaEroi.crea(tipo, nome);
        eroe.ripristinaProgressi(livello, esperienza, oro, puntiVita);
        for (DatiOggetto oggetto : inventario) {
            eroe.getInventario().aggiungiOggetto(oggetto.creaOggetto());
        }
        if (armaEquipaggiata != null) {
            eroe.equipaggia((Arma) armaEquipaggiata.creaOggetto());
        }
        return eroe;
    }
}