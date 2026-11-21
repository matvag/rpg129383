package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;
import it.unicam.cs.mpgc.rpg129383.interfaces.Oggetto;

public abstract class Eroe extends Personaggio{
    private static final int ESPERIENZA_PER_LIVELLO = 100;

    private int livello;
    private int esperienza;
    private int oro;
    private final Inventario inventario;
    private Arma armaEquipaggiata;

    public Eroe(String nome, int puntiVitaMax, int attacco, int difesa) {
        super(nome, puntiVitaMax, attacco, difesa);
        this.livello = 1;
        this.esperienza = 0;
        this.oro = 0;
        this.inventario = new Inventario();
        this.armaEquipaggiata = null;
    }

    public abstract TipoEroe getTipo();

    /**
     * Aumenta le statistiche quando l'eroe sale di livello
     */
    protected abstract void potenziaPerLivello();

    public int guadagnaEsperienza(int punti){
        if(punti < 0){
            throw new IllegalArgumentException("L'esperienza non può essere negativa");
        }
        esperienza += punti;
        int livelliGuadagnati = 0;
        while(esperienza >= getEsperienzaPerProssimoLivello()){
            esperienza -= getEsperienzaPerProssimoLivello();
            saliDiLivello();
            livelliGuadagnati++;
        }
        return livelliGuadagnati;
    }

    public int getEsperienzaPerProssimoLivello(){
        return livello * ESPERIENZA_PER_LIVELLO;
    }

    public void saliDiLivello(){
        livello++;
        potenziaPerLivello();
        cura(getPuntiVitaMax());
    }

    public void aggiungiOro(int quantita){
        if(quantita < 0){
            throw new IllegalArgumentException("La quantita non può essere negativa");
        }
        oro += quantita;
    }

    public void spendiOro(int quantita){
        if(quantita < 0 || quantita > oro){
            throw new IllegalArgumentException("Quantità di oro non valida");
        }
        oro =- quantita;
    }

    public boolean usaOggetto(Oggetto oggetto) {
        if (!inventario.rimuoviOggetto(oggetto)) {
            return false;
        }
        oggetto.usa(this);
        return true;
    }

    public void equipaggia(Arma arma){
        if(armaEquipaggiata == null){
            inventario.aggiungiOggetto(armaEquipaggiata);
        }
        armaEquipaggiata = arma;
    }

    @Override
    public int getAttacco() {
        if (armaEquipaggiata == null) {
            return super.getAttacco();
        }
        return super.getAttacco() + armaEquipaggiata.getBonusAttacco();
    }
    /**
     * Riporta un eroe appena creato ai progressi salvati.
     * Metodo per persistenza progressi eroe.
     */
    public void ripristinaProgressi(int livello, int esperienza, int oro, int puntiVita) {
        if (this.livello != 1 || this.esperienza != 0) {
            throw new IllegalStateException("Si possono ripristinare i progressi solo di un eroe nuovo");
        }
        if (livello < 1 || esperienza < 0 || oro < 0) {
            throw new IllegalArgumentException("Progressi non validi");
        }
        while (this.livello < livello) {
            this.livello++;
            potenziaPerLivello();
        }
        this.esperienza = esperienza;
        this.oro = oro;
        impostaPuntiVita(puntiVita);
    }

    public Inventario getInventario() {
        return inventario;
    }

    public Arma getArmaEquipaggiata() {
        return armaEquipaggiata;
    }

    public int getLivello() {
        return livello;
    }

    public int getEsperienza() {
        return esperienza;
    }

    public int getOro() {
        return oro;
    }

    @Override
    public String toString() {
        return getNome() + " il " + getTipo().getNome() + " (Lv " + livello + ", "
                + getPuntiVita() + "/" + getPuntiVitaMax() + " PV)";
    }
}
