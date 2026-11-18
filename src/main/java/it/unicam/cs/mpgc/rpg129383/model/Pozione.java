package it.unicam.cs.mpgc.rpg129383.model;

public class Pozione extends OggettoBase{

    private final int cura;

    public Pozione(String nome, int cura, int valore){
        super(nome, valore);
        if(cura <= 0){
            throw new IllegalArgumentException("La cura deve essere positiva");
        }
        this.cura = cura;
    }
    @Override
    public void usa(Eroe eroe){
        eroe.cura(cura);
    }
    @Override
    public String getDescrizione(){
        return "Ripristina " + cura + " punti vita";
    }

    public int getCura() {
        return cura;
    }
}
