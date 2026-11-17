package it.unicam.cs.mpgc.rpg129383.model;

import it.unicam.cs.mpgc.rpg129383.interfaces.Oggetto;

import java.util.Objects;

public abstract class OggettoBase implements Oggetto {

    private final String nome;
    private final int valore;

    public OggettoBase(String nome, int valore) {
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome dell'oggetto nullo");
        }
        if(valore < 0){
            throw new IllegalArgumentException("Il valore non può essere negativo");
        }
        this.nome = nome;
        this.valore = valore;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getValore() {
        return valore;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OggettoBase altro = (OggettoBase) obj;
        return nome.equals(altro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), nome);
    }

    @Override
    public String toString() {
        return nome;
    }


}
