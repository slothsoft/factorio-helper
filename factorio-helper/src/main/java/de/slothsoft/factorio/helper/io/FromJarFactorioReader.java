package de.slothsoft.factorio.helper.io;

import java.io.InputStream;

public class FromJarFactorioReader implements FactorioReader {

	@Override
	public InputStream createDataLoaderStream() {
		return getClass().getResourceAsStream("dataloader.lua");
	}

}
