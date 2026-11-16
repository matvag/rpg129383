package it.unicam.cs.mpgc.rpg129383.interfaces;
/*
* Rappresenta chiunque possa partecipare ad un duello
* */
public interface Combattente {

    String getNome();

    int getPuntiVita();

    boolean isVivo();

    int attacca(Combattente bersaglio);

    int subisciDanno(int danno);
}
