package models;

import java.time.Duration;
import java.time.Instant;

/**
 * Lihtne mängu ajamõõtja, mis võimaldab:
 * -aja kävitamist
 * -peatamist
 * -nullist uuesti alustamist
 * Aega mõõdetakse süsteemi kella alusel
 */
public class GameTimer {
    private Instant startTime;
    private boolean running;
    private Duration duration = Duration.ZERO;

    //Käivita taimer nullits, kui juba töötas, siis alustab uuesti algusest
    public void start() {
        startTime = Instant.now(); //Alusta aja mõõtmist praegusest hetkest
        running = true;
        duration = Duration.ZERO; //Taaskäivitus nullist
    }

    //Peatb timeri, aeg peatub
    public void stop() {
        if (running && startTime != null) {
            duration = Duration.between(startTime, Instant.now());
        }
        running = false;
    }

    /**
     * Kas taimer töötab
     * @return true jah, false ei
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Tagstab, kui palju aega on mängu algusest möödas
     * @return aeg sekundites või 0
     */
    public int getElapsedSeconds() {
        if(startTime == null) {
            return 0;
        }

        if(running) {
            return (int) Duration.between(startTime, Instant.now()).getSeconds();
        } else {
            return (int) duration.getSeconds();
        }
    }

    /**
     * Vormindab aja sekunditest kujule MM:SS ehk 25 sek = 00:20
     * @return vormindatud aeg
     */
    public String formatGameTime() {
        int totalSeconds = getElapsedSeconds();
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
