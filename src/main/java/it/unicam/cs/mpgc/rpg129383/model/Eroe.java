package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;

public abstract class Eroe extends Personaggio{
    private static final int ESPERIENZA_PER_LIVELLO = 100;

    private int livello;
    private int esperienza;
    private int oro;

    public Eroe(String nome, int puntiVitaMax, int attacco, int difesa) {
        super(nome, puntiVitaMax, attacco, difesa);
        this.livello = 1;
        this.esperienza = 0;
        this.oro = 0;
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

    public int getLivello() {
        return livello;
    }

    public int getEsperienza() {
        return esperienza;
    }

    public int getOro() {
        return oro;
    }
}
