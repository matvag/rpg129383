package it.unicam.cs.mpgc.rpg129383.enums;

/**
 *  Tipi di stanza che si possono incontrare nel dungeon
 */
public enum TipoStanza {

        COMBATTIMENTO("Combattimento"),
        TESORO("Tesoro"),
        RIPOSO("Riposo"),
        BOSS("Boss");

        private final String nome;

        TipoStanza(String nome) {
            this.nome = nome;
        }

        public String getNome() {
            return nome;
        }
    }

