package de.slothsoft.factorio.helper.io;

import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;

public final class ServiceBuddy {

	public static <S> S getService(Class<S> serviceClass) {
		return Objects.requireNonNull(findService(serviceClass),
				"Could not find service for " + serviceClass.getSimpleName() + ".class");
	}

	public static <S> S findService(Class<S> serviceClass) {
		final ServiceLoader<S> serviceLoader = ServiceLoader.load(serviceClass);
		final Iterator<S> it = serviceLoader.iterator();
		if (it.hasNext()) return it.next();
		return null;
	}

	private ServiceBuddy() {
		// hide my sorry butt
	}
}
