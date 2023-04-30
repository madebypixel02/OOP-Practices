/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   MusicGenre.java                          =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/04/30 18:39:40 by aperez-b                       +@@@%@    */
/*   Updated: 2023/04/30 18:39:41 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac3;

import java.net.MulticastSocket;

public enum MusicGenre {
    ALTERNATIVE("Alternative Rock", 'A', 125),
    BLUES("Blues",'B', 80),
    CLASSICAL("Classical Music", 'C', 60),
    COUNTRY("Country Music", 'Y', 120),
    DISCO("Disco", 'D', 130),
    JAZZ("Jazz", 'J', 100),
    METAL("Metal", 'M', 140),
    POP("Pop Music", 'P', 110),
    ROCK_N_ROLL("Rock & Roll", 'R', 150),
    R_N_B("R & B", 'N', 90),
    OTHER("Other", 'O', 105);

    final private String description;
    final private char code;
    final private int tempo;
    private MusicGenre(String description, char code, int avgTempo) {
        this.description = description;
        this.code = code;
        this.tempo = avgTempo;
    }

    public String getDescription() {
        return this.description;
    }

    public char getCode() {
        return this.code;
    }

    public int getTempo() {
        return this.tempo;
    }
    public static MusicGenre getMusicGenre(char code) {
        for (MusicGenre genre : MusicGenre.values()) {
            if (genre.code == code)
                return (genre);
        }
        return (null);
    }
    public static MusicGenre[] getGenresSortedByCode() {
        MusicGenre []list;
        MusicGenre aux;
        int i;

        list = MusicGenre.values();
        i = 0;
        while (i < list.length) {
            if (i + 1 < list.length && list[i].code > list[i + 1].code) {
                aux = list[i + 1];
                list[i + 1] = list[i];
                list[i] = aux;
                i = 0;
            }
            else
                i++;
        }
        return (list);
    }
    public MusicGenre[] getGenresSortedByTempo() {
        MusicGenre []list;
        MusicGenre aux;
        int i;

        list = MusicGenre.values();
        i = 0;
        while (i < list.length) {
            if (i + 1 < list.length && list[i].tempo > list[i + 1].tempo) {
                aux = list[i + 1];
                list[i + 1] = list[i];
                list[i] = aux;
                i = 0;
            }
            else
                i++;
        }
        return (list);
    }
    public MusicGenre getGenreWithNextHigherTempo() {
        MusicGenre []genresSortedByTempo;

        genresSortedByTempo = this.getGenresSortedByTempo();
        for (int i = 0; i < genresSortedByTempo.length; i++) {
            if (this == genresSortedByTempo[i] && i + 1 >= genresSortedByTempo.length)
                return (this);
            else if (this == genresSortedByTempo[i]) {
                if (genresSortedByTempo[i].tempo > genresSortedByTempo[i + 1].tempo)
                    return (this);
                return (genresSortedByTempo[i + 1]);
            }
        }
        return (this);
    }
}
