/**
 * 
 */
package com.pjl.filter.processor;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.pjl.filter.commandreader.CommandReader;
import com.pjl.filter.model.Command;

/**
 * @author Sangita
 *
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FileProcessorImplTest {

	@Mock
	CommandReader reader;

	@InjectMocks
	private FileProcessorImpl processor;

	@Before
	public void setup() {
		when(this.reader.readCommands(anyString())).thenReturn(this.createCommands());
	}

	@Test
	public void processTest() {

		try {
			URL url = FileProcessorImplTest.class.getProtectionDomain().getCodeSource().getLocation();// .getPath()+"//SampleFiles";
			try {
				URL actualURL = new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile() + "/SampleFiles",
						null);
				URL actualURLforCommand = new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile() + "/SampleFiles",
						null);
				Path resPath = java.nio.file.Paths.get(actualURL.toURI());
				Path resPathCommand = java.nio.file.Paths.get(actualURLforCommand.toURI());
				processor.process(resPath.toString(),resPathCommand.toString());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	private ArrayList<Command> createCommands() {

		ArrayList<Command> commands = new ArrayList<Command>();
		Command command = new Command("USERID", "TestUSer");
		commands.add(command);
		return commands;
	}

}
