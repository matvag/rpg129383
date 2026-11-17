package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;

public class Mago extends Eroe{

    private static final int POTERE_MAGICO = 8;

    public Mago(String nome){
        super(nome,80,12,3);
    }

    @Override
    protected int calcolaDanno(){
        return getAttacco() + POTERE_MAGICO;
    }

    @Override
    protected void potenziaPerLivello(){
        potenzia(8,4,1);
    }

    @Override
    public TipoEroe getTipo(){
        return TipoEroe.MAGO;
    }

}
