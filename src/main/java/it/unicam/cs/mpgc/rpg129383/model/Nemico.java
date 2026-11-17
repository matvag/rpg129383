package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.enums.TipoNemico;

public class Nemico extends Personaggio{

    private final TipoNemico tipo;

    public Nemico(TipoNemico tipo){
        super(tipo.getNome(), tipo.getPuntiVita(), tipo.getAttacco(),tipo.getDifesa());
        this.tipo = tipo;
    }

    @Override
    protected int calcolaDanno(){
        return getAttacco();
    }

    public int getEsperienzaRicompensa(){
        return tipo.getEsperienza();
    }

    public int getOroRicompensa() {
        return tipo.getOro();
    }

    public TipoNemico getTipo() {
        return tipo;
    }


}
