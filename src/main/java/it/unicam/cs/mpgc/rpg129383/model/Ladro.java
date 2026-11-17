package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.enums.TipoEroe;
import it.unicam.cs.mpgc.rpg129383.utils.Dado;

/**
 * Molto veloce può infliggere colpi critici
 */
public class Ladro extends Eroe{
    private final static int PROBABILITA_CRITICO = 30;
    private final static int MOLTIPLICATORE_CRITICO = 2;

    private final Dado dado;

    public Ladro(String nome){
        super(nome,95,13, 5);
        this.dado = new Dado();
    }

    @Override
    protected int calcolaDanno(){
        if(dado.probabilita(PROBABILITA_CRITICO)){
            return  MOLTIPLICATORE_CRITICO * getAttacco();
        }
        return getAttacco();
    }

    @Override
    protected void potenziaPerLivello(){
        potenzia(10,3,1);
    }

    @Override
    public TipoEroe getTipo(){
        return TipoEroe.LADRO;
    }

}
