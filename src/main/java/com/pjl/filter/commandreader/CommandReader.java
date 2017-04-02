/**
 * 
 */
package com.pjl.filter.commandreader;

import java.util.ArrayList;

import com.pjl.filter.model.Command;

/**
 * @author Sangita
 *
 */
public interface CommandReader {
	public ArrayList<Command> readCommands(String csvPath);
}