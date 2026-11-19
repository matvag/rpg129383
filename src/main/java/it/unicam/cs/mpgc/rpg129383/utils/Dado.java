package it.unicam.cs.mpgc.rpg129383.utils;
import java.util.Random;

/**
 * Genera numeri casuali per il gioco (colpi critici, generazione dei nemici...)
 */
public class Dado {

    private final Random random;

    public Dado() {
        random = new Random();
    }

    public boolean probabilita(int percentuale){
        return tira(1,100) <= percentuale;
    }
    public int tira(int min, int max){
        return random.nextInt(min, max + 1);
    }




}
