package aQute.lib.git;

import static java.util.Objects.requireNonNull;

import java.nio.file.Files;
import java.nio.file.Path;

public class GitRepository {

	public GitRepository() {
		// TODO Auto-generated constructor stub
	}


	public static Path workingDir(Path folder) {
		requireNonNull(folder);
		folder = folder.normalize()
			.toAbsolutePath();
		while ((folder != null) && Files.isDirectory(folder)) {
			if (Files.exists(folder.resolve(".git"))) {
				return folder;
			}
			folder = folder.getParent();
		}
		return null;
	}
}
