package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;

public class Guerriero extends Eroe{

    public Guerriero(String nome){
        super(nome, 120, 14, 8);
    }
    @Override
    protected int calcolaDanno(){
        return getAttacco();
    }

    @Override
    protected void potenziaPerLivello(){
        potenzia(15,3,2);
    }

    @Override
    public TipoEroe getTipo(){
        return TipoEroe.GUERRIERO;
    }
}
