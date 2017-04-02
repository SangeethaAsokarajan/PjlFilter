/**
 * 
 */
package com.pjl.filter.processor;

/**
 * @author Sangita
 *
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pjl.filter.commandreader.CommandReader;
import com.pjl.filter.constants.Constants;
import com.pjl.filter.model.Command;
import com.pjl.filter.utils.FileUtils;

@Component
public class FileProcessorImpl implements FileProcessor {

	@Autowired
	CommandReader reader;

	@Override
	public void process(String path,String csvPath) {

		ArrayList<Command> commands = reader.readCommands(csvPath);
		try {
			Files.list(Paths.get(path)).forEach(x -> {
				String fileExtension = x.toString().substring(x.toString().indexOf("."));
				if (fileExtension.contains(Constants.PJL_FILE_EXTENSION))
					applyPjlCommandsFilter(x, commands);

			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String replaceCorrectValue(String str, ArrayList<Command> commands) {
		if (str.contains(Constants.PJLSET)) {
			String command = str.replace(Constants.PJLSET, "").trim();
			List<String> commandHeaderValue = Arrays.asList(command.split(Constants.PJLSET_SEPERATOR));
			for (Command cmd : commands) {
				if (commandHeaderValue.get(0).trim().equals(cmd.getName()))
					str = str.replace(commandHeaderValue.get(1).trim(), cmd.getValue());
			}
		}
		return str;
	}

	private void applyPjlCommandsFilter(Path path, ArrayList<Command> commands) {
		List<String> list = new ArrayList<>();
		String newFileName = FileUtils.copyFile(path);

		try (BufferedReader br = new BufferedReader(new FileReader(newFileName))) {
			list = br.lines().collect(Collectors.toList());
			list = list.stream().map(x -> replaceCorrectValue(x, commands)).collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(newFileName))) {
			list.forEach(x -> {
				try {
					bw.write(x + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Filter applied successfully!!");
	}
}
