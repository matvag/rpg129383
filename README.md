# 🐉 Dungeon RPG

Gioco di ruolo a turni (dungeon crawler) sviluppato in **Java 25** con interfaccia grafica **JavaFX**,
per il corso di Metodologie di Programmazione – Università di Camerino, A.A. 2025/26.
Il giocatore crea un eroe (Guerriero, Mago o Ladro), attraversa un dungeon generato casualmente
fatto di combattimenti, tesori e stanze di riposo, e affronta il drago nell'ultima stanza.
I progressi dell'eroe possono essere salvati e ricaricati tramite un file **JSON** (libreria Gson).

---

## 🚀 Come eseguire il progetto

### Prerequisiti
- Java 25 (LTS)
- Gradle: **non è necessario installarlo**, il progetto include il Gradle Wrapper (`gradlew`)

### Istruzioni

```bash
git clone https://github.com/matvag/rpg129383.git
cd rpg129383
```

### Build del progetto
```bash
./gradlew build
```

### Esecuzione
```bash
./gradlew run
```

Su Windows (PowerShell o Prompt dei comandi) usare `.\gradlew build` e `.\gradlew run`.

### Come si gioca
1. **Nuova partita** → scegli il nome e la classe dell'eroe.
2. **Avanza** per entrare nella stanza successiva.
3. Nei combattimenti usa **Attacca**, **Fuggi** oppure seleziona un oggetto e premi **Usa oggetto**.
4. Fuori dai combattimenti puoi **Salvare** l'eroe e ricaricarlo dal menu con **Carica eroe**.
5. Sconfiggi il drago nell'ultima stanza per vincere.

I salvataggi vengono scritti nella cartella `saves/` (esclusa dal repository).

---

## 🤖 Uso di strumenti di AI
Il codice del progetto è stato scritto interamente da me; mi sono avvalso del confronto con Gemini solo come supporto per:
* Utilizzato **Gemini** per:
    * idee per la realizzazione del codice (es. `Optional`, generazione casuale con il dado)
    * approfondimento teorico
    * idee per la storyline del gioco (idea di base, personaggi, stanze, oggetti)
    * confronto sulla logica dell'interfaccia grafica 
    * generazione del css (stile.css)
    * stesura e revisione lessicale di questo README
  

📌 Per una descrizione più dettagliata dell'uso dell'AI e delle scelte progettuali,
consultare la **Wiki del repository**.