/**
 * 
 */
package com.pjl.filter.commandreader;

/**
 * @author Sangita
 *
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.pjl.filter.PjlFilterApplication;
import com.pjl.filter.constants.Constants;
import com.pjl.filter.model.Command;

@Component
public class CommandReaderImpl implements CommandReader {

	@Override
	public ArrayList<Command> readCommands(String csvPath) {
		ArrayList<Command> commands = new ArrayList<Command>();
		try {
			Files.list(Paths.get(csvPath)).forEach(x -> {
				String fileExtension = x.toString().substring(x.toString().indexOf("."));
				if (fileExtension.contains(Constants.CSV_FILE_EXTENSION))
					try (BufferedReader br = new BufferedReader(new FileReader(x.toString()))) {
					String line;
					while ((line = br.readLine()) != null) {
						String[] entries = line.split(",");
						Command command = new Command(entries[0], entries[1]);
						commands.add(command);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}

			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return commands;
	}

}
