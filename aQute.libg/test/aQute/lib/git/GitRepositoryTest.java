package aQute.lib.git;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class GitRepositoryTest {

	@Test
	public void testWorkingDir() {
		Path cwd = Paths.get("");
		Path workingDir = GitRepository.workingDir(cwd);
		assertThat(workingDir).isDirectory();
		assertThat(workingDir.resolve(".git")).exists();
		assertThat(cwd).startsWith(workingDir);
	}
}
