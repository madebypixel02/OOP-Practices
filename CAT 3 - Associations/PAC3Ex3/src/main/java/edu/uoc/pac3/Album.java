/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   Album.java                               =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/04/30 18:38:13 by aperez-b                       +@@@%@    */
/*   Updated: 2023/04/30 18:38:14 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac3;

public class Album {

    private String title;
    private MusicGenre musicGenre;

    public Album(String title, MusicGenre musicGenre) {
        setTitle(title);
        setMusicGenre(musicGenre);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MusicGenre getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(MusicGenre musicGenre) {
        this.musicGenre = musicGenre;
    }
}
