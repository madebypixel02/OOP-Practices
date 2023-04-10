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

/**
 * This class represents a simple RPG character
 * @author aperez-b
 * @version 1.0
 */
public class RPGCharacter {
    /**
     * The unique identifier for a character, starting from 0
     */
	private int id = RPGCharacter.getNextId();
    /**
     * The value for the next character that is created
     */
    private static int nextId = 0;
    /**
     * The name of a member of the RPCCharacter class
     */
    private String name = "Name";
    /**
     * The characters that cannot appear on the name
     */
	private static String forbiddenChars = "[~!@#$%^&*()]";
    /**
     * The level of a member of the RPCCharacter class
     */
	private int level = 1;
    /**
     * The number of hitpoints a member of the RPCCharacter class starts with
     */
	private int life = 100;
    /**
     * Whether a member of the RPCCharacter class is alive or not
     */
	private boolean isAlive = true;
    /**
     * The last known date when a member of the RPCCharacter class died
     */
	private LocalDate lastDeath = null;
    /**
     * The alignment of a member of the RPCCharacter class, could be Alliance (A), Horde (H) or Neutral (N)
     */
	private char alignment = 'N';

    /**
     * Basic Constructor for the RPGCharacter class
     */
	public RPGCharacter() {
        RPGCharacter.nextId++;
	}
    /**
     * A Constructor for the RPGCharacter class
     * @param name The name of the RPGCharacter
     * @param alignment The name of the alignment of an RPGCharacter
     * @throws Exception When alignment is not 'H' 'A' or 'N', or when name contains an invalid character
     */
	public RPGCharacter(String name, char alignment) throws Exception {
        this.setName(name);
        this.setAlignment(alignment);
        RPGCharacter.nextId++;
	}
    /**
     * Basic Constructor for the RPGCharacter class
     * @param name The name of the RPGCharacter
     * @param alignment The name of the alignment of an RPGCharacter
     * @param life The starting number of hitpoints of an RPGCharacter
     * @throws Exception When alignment is not "A", "H" or "N", when name contains an invalid character, or when life is negative
     */
	public RPGCharacter(String name, char alignment, int life) throws Exception {
        this.setName(name);
        this.setAlignment(alignment);
        this.setLife(life);
        RPGCharacter.nextId++;
	}

    /**
     * Getter of id
     * @return id
     */
	public int getId() {
		return (this.id);
	}
    /**
     * Setter of level based on nextId
     */
	private void setId() {
		this.id = RPGCharacter.getNextId();
	}
    /**
     * Getter of nextId
     * @return nextId
     */
	public static int getNextId() {
		return (RPGCharacter.nextId);
	}
    /**
     * Increase nextID by one
     */
	private void setNextId() {
		RPGCharacter.nextId++;
	}
    /**
     * Getter of name
     * @return name
     */
	public String getName() {
		return (this.name);
	}
    /**
     * Setter of name
     * @param name New name to be set
     * @throws Exception When name contains invalid characters
     */
	public void setName(String name) throws Exception {
        this.setName(name, RPGCharacter.forbiddenChars);
    }

    /**
     * Setter of name
     * @param name New name to be set
     * @param forbiddenChars Custom string of invalid characters, replaces forbiddenChars
     * @throws Exception When name contains invalid characters
     */
    public void setName(String name, String forbiddenChars) throws Exception {
        int index;
        String []words;

        for (int i = 0; i < name.length(); i++) {
            if (forbiddenChars.indexOf(name.charAt(i)) != -1)
                throw new Exception("[ERROR] Name cannot contain any of the forbidden chars");
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
    /**
     * Getter of level
     * @return level
     */
    public int getLevel() {
        return (this.level);
    }
    /**
     * Setter of level
     * @param level New level to set
     */
    private void setLevel(int level){
        this.level = level;
    }
    /**
     * Increase level by one
     */
    public void incLevel() {
        this.level++;
    }
    /**
     * Getter of life
     * @return life
     */
    public int getLife() {
        return (this.life);
    }
    /**
     * Setter of life
     * @param life New life to be set
     * @throws Exception When life is negative
     */
    private void setLife(int life) throws Exception {
        if (life <= 0)
            throw new Exception("[ERROR] Life must be a positive number");
        else {
            this.life = life;
            this.setAlive(true);
        }
    }
    /**
     * Update life to increase or decrease hitpoints
     * @param life Amount healed or damaged
     * @throws Exception When player is dead, or when life > (this.life / 2)
     */
    public void updateLife(int life) throws Exception {
        if (this.life == 0)
            throw new Exception("[ERROR] The character is dead");
        else if (life > 0 && life > this.life / 2)
            throw new Exception("[ERROR] A character cannot increase its life more than 50% in a single healing");
        else {
            this.life = Math.max(this.life + life, 0);
            if (this.life == 0)
                this.setAlive(false);
        }
    }
    /**
     * Getter of isAlive
     * @return Whether RPGCharacter is alive
     */
    public boolean isAlive() {
        return (this.isAlive);
    }
    /**
     * Setter of isAlive. If isAlive == false, updates lastDeath to current time
     * @param alive New value of isAlive
     */
    private void setAlive(boolean alive) {
        this.isAlive = alive;
        if (!alive)
            this.setLastDeath(java.time.LocalDate.now());
    }
    /**
     * Restores life to 1 if a member of RPCCharacter has died
     * @throws Exception When isAlive == true
     */
    public void resurrect() throws Exception {
        if (this.isAlive())
            throw new Exception("[ERROR] The character is already alive");
        this.setLife(1);
    }
    /**
     * Getter of lastDeath
     * @return date of the last time an RPGCharacter died
     */
    public LocalDate getLastDeath() {
        return (this.lastDeath);
    }
    /**
     * Setter of lastDeath
     * @param lastDeath New lastDeath to set, provided that it is later than last known lastDeath
     */
    private void setLastDeath(LocalDate lastDeath) {
        if (this.lastDeath == null || lastDeath.isAfter(this.lastDeath))
            this.lastDeath = lastDeath;
    }
    /**
     * Getter of Alignment
     * @return alignment of a given RPGCharacter
     */
    public char getAlignment() {
        return (this.alignment);
    }
    /**
     * Setter of Alignment
     * @param alignment New alignment to set
     * @throws Exception When alignment is not 'H' 'A' or 'N'
     */
    public void setAlignment(char alignment) throws Exception {
        String alignments;

        alignments = "HAN";
        if (alignments.indexOf(alignment) == -1)
            throw new Exception("[ERROR] Alignment must be a valid value ('H', 'A' or 'N')");
        else
            this.alignment = alignment;
    }
}
