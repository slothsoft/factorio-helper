package de.slothsoft.factorio.helper.ui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class Messages {

	private static final String BUNDLE_NAME = "de.slothsoft.factorio.helper.ui.messages"; //$NON-NLS-1$
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (final MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	private Messages() {
		// hide me
	}
}
