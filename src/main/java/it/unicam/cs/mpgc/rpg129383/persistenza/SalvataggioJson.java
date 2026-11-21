package it.unicam.cs.mpgc.rpg129383.persistenza;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg129383.interfaces.GestoreSalvataggi;
import it.unicam.cs.mpgc.rpg129383.model.Eroe;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Salva l'eroe in un file JSON usando la libreria Gson
 */
public class SalvataggioJson implements GestoreSalvataggi {

    private static final Path FILE_PREDEFINITO = Path.of("saves", "eroe.json");

    private final Path file;
    private final Gson gson;

    public SalvataggioJson() {
        this(FILE_PREDEFINITO);
    }

    public SalvataggioJson(Path file) {
        this.file = file;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void salva(Eroe eroe) {
        DatiEroe dati = new DatiEroe(eroe);
        String json = gson.toJson(dati);
        try {
            Files.createDirectories(file.toAbsolutePath().getParent());
            Files.writeString(file, json);
        } catch (IOException e) {
            throw new UncheckedIOException("Impossibile salvare la partita", e);
        }
    }

    @Override
    public Optional<Eroe> carica() {
        if (!esisteSalvataggio()) {
            return Optional.empty();
        }
        try {
            String json = Files.readString(file);
            DatiEroe dati = gson.fromJson(json, DatiEroe.class);
            return Optional.of(dati.creaEroe());
        } catch (IOException e) {
            throw new UncheckedIOException("Impossibile caricare la partita", e);
        }
    }

    @Override
    public boolean esisteSalvataggio() {
        return Files.exists(file);
    }
}
