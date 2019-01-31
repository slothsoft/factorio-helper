package de.slothsoft.factorio.helper.ui.impl;

import java.util.List;
import java.util.function.Function;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class ElementValueFactory<P, T> implements Callback<CellDataFeatures<P, T>, ObservableValue<T>> {

	private final int index;
	private final Function<P, List<T>> listGetter;

	public ElementValueFactory(int index, Function<P, List<T>> listGetter) {
		this.index = index;
		this.listGetter = listGetter;
	}

	@Override
	public ObservableValue<T> call(CellDataFeatures<P, T> param) {
		final List<T> list = this.listGetter.apply(param.getValue());
		if (this.index < list.size()) {
			return new ReadOnlyObjectWrapper<>(list.get(this.index));
		}
		return null;
	}

}