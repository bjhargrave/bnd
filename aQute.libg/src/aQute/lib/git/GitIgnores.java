package aQute.lib.git;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class GitIgnores {
	private final Path									workingDir;
	private final Map<Path, Function<String, Boolean>>	rules;

	public GitIgnores(Path workingDir) {
		this.workingDir = workingDir;
		rules = new HashMap<>();
	}

	private static final Path EMPTY = Paths.get("");

	public boolean ignore(Path path) {
		path = path.normalize()
			.toAbsolutePath();
		if (!path.startsWith(workingDir)) {
			return false;
		}
		Path relative = workingDir.relativize(path);
		int count = relative.getNameCount() - 1;
		if (count == 0) {
			return false;
		}
		for (String subject = null; count >= 0; count--) {
			Path prefix = (count == 0) ? EMPTY : relative.subpath(0, count);
			subject = (subject == null) ? relative.getName(count)
				.toString()
				: relative.getName(count)
					.toString() + "/" + subject;
			Function<String, Boolean> rule = rules.computeIfAbsent(prefix, this::createRule);
			Boolean result = rule.apply(subject);
			if (result != null) {
				return result.booleanValue();
			}
		}
		return false;
	}

	private Function<String, Boolean> createRule(Path prefix) {
		Path gitignore = workingDir.resolve(prefix)
			.resolve(".gitignore");
		if (Files.isRegularFile(gitignore)) {
			//
		}
		return item -> null;
	}

}
