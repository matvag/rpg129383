package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;

public class Mago extends Eroe{

    public Mago(String nome){
        super(nome,80,12,3);
    }

    @Override
    protected int calcolaDanno(){
        return getAttacco();
    }

    @Override
    protected void potenziaPerLivello(){
        potenzia(8,5,1);
    }

    @Override
    public TipoEroe getTipo(){
        return TipoEroe.MAGO;
    }

}
