package no.uib.inf101.sample.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.*;

/**
 * Play the Tetris music. Sample usage:
 * <code>
 * TetrisSong music = new TetrisSong();
 * music.run(); 
 * </code>
 */
public class ChadSong implements Runnable {
    private Sequencer sequencer;
    InputStream song;

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