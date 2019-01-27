package de.slothsoft.factorio.helper.io;

import java.io.InputStream;

public interface FactorioReader {

	/**
	 * Reads the file "dataloader.lua" from the Factorio installation.
	 * 
	 * @return a input stream with the above contents
	 */

	InputStream createDataLoaderStream();
}
