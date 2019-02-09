package aQute.lib.git;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GitIgnoresTest {

	Path workingDir;
	@Before
	public void setUp() throws Exception {
		workingDir = GitRepository.workingDir(Paths.get(""));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGitIgnores() {
		GitIgnores ignores = new GitIgnores(workingDir);

		assertThat(ignores.ignore(new File("test/aQute/lib/git/GitIgnoresTest.java").toPath())).isFalse();
		assertThat(ignores.ignore(workingDir)).isFalse();
	}

}
