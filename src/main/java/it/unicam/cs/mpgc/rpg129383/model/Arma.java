package it.unicam.cs.mpgc.rpg129383.model;

public class Arma extends OggettoBase{

    private final int bonusAttacco;

    public Arma(String nome, int bonusAttacco, int valore){
        super(nome, valore);
        if(bonusAttacco < 0){
            throw new IllegalArgumentException("Bonus attacco non può essere negativo");
        }
        this.bonusAttacco = bonusAttacco;
    }

    @Override
    public void usa(Eroe eroe){
    }

    @Override
    public String getDescrizione() {
        return "Aumenta l'attacco di " + bonusAttacco;
    }

    public int getBonusAttacco() {
        return bonusAttacco;
    }


}
