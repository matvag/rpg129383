package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.interfaces.Combattente;

public abstract class Personaggio implements Combattente {
    private static final int DANNO_MINIMO = 1;

    private final String nome;
    private int puntiVitaMax;
    private int puntiVita;
    private int attacco;
    private int difesa;

    public Personaggio(String nome, int puntiVitaMax, int attacco, int difesa) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }
        if (puntiVitaMax <= 0 || attacco < 0 || difesa < 0) {
            throw new IllegalArgumentException("Statistiche non valide");
        }
        this.nome = nome;
        this.puntiVitaMax = puntiVitaMax;
        this.puntiVita = puntiVitaMax;
        this.attacco = attacco;
        this.difesa = difesa;
    }
    /**
     * Ogni tipo di personaggio calcola il danno a modo suo
     */
    protected abstract int calcolaDanno();

    @Override
    public int attacca(Combattente bersaglio) {
        return bersaglio.subisciDanno(calcolaDanno());
    }

    @Override
    public int subisciDanno(int danno) {
        int dannoSubito = Math.max(DANNO_MINIMO, danno - difesa);
        puntiVita = Math.max(0, puntiVita - dannoSubito);
        return dannoSubito;
    }

    public void cura(int quantita) {
        if (quantita < 0) {
            throw new IllegalArgumentException("La cura non può essere negativa");
        }
        puntiVita = Math.min(puntiVitaMax, puntiVita + quantita);
    }

    @Override
    public boolean isVivo() {
        return puntiVita > 0;
    }

    /**
     * Aumenta le statistiche del personaggio per es. quando sale di livello
     */
    protected void potenzia(int vitaExtra, int attaccoExtra, int difesaExtra) {
        puntiVitaMax += vitaExtra;
        attacco += attaccoExtra;
        difesa += difesaExtra;
    }
    /**
     * Imposta i punti vita tra 0 e il massimo, ad esempio quando si carica un salvataggio
     */
    protected void impostaPuntiVita(int puntiVita) {
        this.puntiVita = Math.max(0, Math.min(puntiVita, puntiVitaMax));
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getPuntiVita() {
        return puntiVita;
    }

    public int getPuntiVitaMax() {
        return puntiVitaMax;
    }

    public int getAttacco() {
        return attacco;
    }

    public int getDifesa() {
        return difesa;
    }

    @Override
    public String toString() {
        return nome + " (" + puntiVita + "/" + puntiVitaMax + " PV)";
    }
}


