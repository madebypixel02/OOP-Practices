/* ************************************************************************** */
/*                                                                            */
/*                                           =@@*   +@@+                      */
/*                                           =@@*   +@@+ :*%@@@%*:            */
/*                                           =@@*   =@@+.@@@=--%@@-           */
/*                                           :@@%. .#@@--@@*   +@@* .+%@@@    */
/*   RPGCharacter.java                        =%@@@@@@+ =@@*   =@@+.@@@+-=    */
/*                                              .---:   -@@#.  *@@--@@*       */
/*   By: aperez-b <aperez-b@uoc.edu>                     +@@@@@@@* +@@+       */
/*                                                         :-==:.  -@@#       */
/*   Created: 2023/04/10 20:18:31 by aperez-b                       +@@@%@    */
/*   Updated: 2023/04/10 20:26:57 by aperez-b                                 */
/*                                                                            */
/* ************************************************************************** */

package edu.uoc.pac2;

import java.time.LocalDate;

public class RPGCharacter {
	private int id = RPGCharacter.getNextId();
	private static int nextId = 0;
	private String name = "Name";
	private static String forbiddenChars = "[~!@#$%^&*()]";
	private int level = 1;
	private int life = 100;
	private boolean isAlive = true;
	private LocalDate lastDeath = null;
	private char alignment = 'N';

	public RPGCharacter() {
        RPGCharacter.nextId++;
	}
	public RPGCharacter(String name, char alignment) {
        this.setName(name);
        this.setAlignment(alignment);
        RPGCharacter.nextId++;
	}
	public RPGCharacter(String name, char alignment, int life) {
        this.setName(name);
        this.setAlignment(alignment);
        this.setLife(life);
        RPGCharacter.nextId++;
	}
	public int getId() {
		return (this.id);
	}
	private void setId() {
		this.id = RPGCharacter.getNextId();
	}
	public static int getNextId() {
		return (RPGCharacter.nextId);
	}
	private void setNextId() {
		RPGCharacter.nextId++;
	}
	public String getName() {
		return (this.name);
	}
	public void setName(String name) {
        this.setName(name, RPGCharacter.forbiddenChars);
    }

    public void setName(String name, String forbiddenChars) {
        int index;
        String []words;

        for (int i = 0; i < name.length(); i++) {
            if (forbiddenChars.indexOf(name.charAt(i)) != -1) {
                System.out.println("[ERROR] Name cannot contain any of the forbidden chars");
                return;
            }
        }
        words = name.toLowerCase().split(" ");
        this.name = "";
        for (String word : words) {
            if (word.length() > 0)
                this.name += word.substring(0, 1).toUpperCase() + word.substring(1);
        }
        if (words.length == 0)
            this.name = "Name";
    }
    public int getLevel() {
        return (this.level);
    }
    private void setLevel(int level){
        this.level = level;
    }
    public void incLevel() {
        this.level++;
    }
    public int getLife() {
        return (this.life);
    }
    private void setLife(int life) {
        if (life <= 0)
            System.out.println("[ERROR] Life must be a positive number");
        else {
            this.life = life;
            this.setAlive(true);
        }
    }
    public void updateLife(int life) {
        if (this.life == 0)
            System.out.println("[ERROR] The character is dead");
        else if (life > 0 && life > this.life / 2)
            System.out.println("[ERROR] A character cannot increase its life more than 50% in a single healing");
        else {
            this.life = Math.max(this.life + life, 0);
            if (this.life == 0)
                this.setAlive(false);
        }
    }
    public boolean isAlive() {
        return (this.isAlive);
    }
    private void setAlive(boolean alive) {
        this.isAlive = alive;
        if (!alive)
            this.setLastDeath(java.time.LocalDate.now());
    }
    public boolean resurrect() {
        if (this.isAlive())
            return (false);
        this.setLife(1);
        return (true);
    }
    public LocalDate getLastDeath() {
        return (this.lastDeath);
    }
    private void setLastDeath(LocalDate lastDeath) {
        if (this.lastDeath == null || lastDeath.isAfter(this.lastDeath))
            this.lastDeath = lastDeath;
    }
    public char getAlignment() {
        return (this.alignment);
    }
    public void setAlignment(char alignment) {
        String alignments;

        alignments = "HAN";
        if (alignments.indexOf(alignment) == -1)
            System.out.println("[ERROR] Alignment must be a valid value ('H', 'A' or 'N')");
        else
            this.alignment = alignment;
    }
}
