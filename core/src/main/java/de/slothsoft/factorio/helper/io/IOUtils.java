package de.slothsoft.factorio.helper.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class IOUtils {

	public static ResourceBundle createEmptyResourceBundle() throws IOException {
		try (InputStream input = new ByteArrayInputStream(new byte[0])) {
			return new PropertyResourceBundle(input);
		}
	}

	private IOUtils() {
		// hide me
	}
}
