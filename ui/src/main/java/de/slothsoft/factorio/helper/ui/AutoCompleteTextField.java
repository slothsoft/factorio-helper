package de.slothsoft.factorio.helper.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * This class is a TextField which implements an "autocomplete" functionality, based on a
 * supplied list of entries.
 */

public class AutoCompleteTextField extends TextField {

	private int maxProposals = 10;
	private List<String> proposals = new ArrayList<>();
	final ContextMenu popup = new ContextMenu();

	public AutoCompleteTextField() {
		textProperty().addListener((v, o, n) -> showPopup());
		focusedProperty().addListener((v, o, n) -> hidePopup());
	}

	void showPopup() {
		final String text = getText().toLowerCase();

		if (text.length() == 0) {
			hidePopup();
			return;
		}

		final List<String> searchResults = this.proposals.stream().filter(s -> s.toLowerCase().startsWith(text))
				.collect(Collectors.toList());

		if (searchResults.isEmpty()) {
			hidePopup();
			return;
		}

		populatePopup(searchResults);
		if (!this.popup.isShowing()) {
			this.popup.show(this, Side.BOTTOM, 0, 0);
		}
	}

	void hidePopup() {
		this.popup.getItems().clear();
		this.popup.hide();
	}

	void populatePopup(List<String> searchResults) {
		final List<MenuItem> popupItems = new ArrayList<>(this.maxProposals);

		for (final String searchResult : searchResults) {
			final CustomMenuItem popupItem = new CustomMenuItem(new Label(searchResult), true);
			popupItem.setOnAction(d -> {
				setText(searchResult);
				positionCaret(searchResult.length());
				hidePopup();

				final EventHandler<ActionEvent> onAction = getOnAction();
				if (onAction != null) {
					onAction.handle(new ActionEvent(this, null));
				}
			});
			popupItems.add(popupItem);
		}
		this.popup.getItems().setAll(popupItems);
	}

	public List<String> getProposals() {
		return this.proposals;
	}

	public AutoCompleteTextField proposals(List<String> newProposals) {
		setProposals(newProposals);
		return this;
	}

	public void setProposals(List<String> proposals) {
		this.proposals = Objects.requireNonNull(proposals);
	}

	public int getMaxProposals() {
		return this.maxProposals;
	}

	public AutoCompleteTextField maxProposals(int newMaxProposals) {
		setMaxProposals(newMaxProposals);
		return this;
	}

	public void setMaxProposals(int maxProposals) {
		this.maxProposals = maxProposals;
	}

}