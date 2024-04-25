package no.uib.inf101.sample.midi;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import java.io.*;

/**
 * The ChadSong class implements the Runnable interface and is responsible for playing a MIDI song.
 * It manages the loading of the song file and controls the playback using a Sequencer.
 */
public class ChadSong implements Runnable {
    private Sequencer sequencer;
    private InputStream song;

    /**
     * Constructor for ChadSong. It initializes the InputStream for the MIDI song file.
     * If the file is not found, it throws a RuntimeException.
     */
    public ChadSong(){
        try {
            song = new FileInputStream("res/Other/chadSong.mid");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            (this.sequencer = MidiSystem.getSequencer()).setSequence(MidiSystem.getSequence(song));
            this.sequencer.open();
            this.sequencer.setLoopCount(-1);
            this.sequencer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}