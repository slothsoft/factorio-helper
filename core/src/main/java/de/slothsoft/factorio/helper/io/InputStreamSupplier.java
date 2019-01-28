package de.slothsoft.factorio.helper.io;

import java.io.IOException;
import java.io.InputStream;

public interface InputStreamSupplier {

	/**
	 * Gets a result.
	 *
	 * @return a result
	 */

	InputStream create() throws IOException;
}
