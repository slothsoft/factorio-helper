package de.slothsoft.factorio.helper.ui.common;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.WindowConstants;

import de.slothsoft.factorio.helper.ui.common.Accordion.Page;

public class AccordionDemo {

	public static void main(String[] args) {
		final Accordion accordion = new Accordion();
		addPanels(accordion);

		final JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(new JScrollPane(accordion));
		frame.setSize(360, 500);
		frame.setLocation(200, 100);
		frame.setVisible(true);
	}

	private static void addPanels(Accordion accordion) {

		// Panel #1 - General Settings

		final JPanel generalPanel = new JPanel(new GridBagLayout());

		generalPanel.add(new JLabel("Header height:"), GridBagData.forLabel(0, 0));
		final JSlider headerHeight = new JSlider();
		headerHeight.setValue(accordion.getHeaderHeight());
		generalPanel.add(headerHeight, GridBagData.forControl(1, 0));
		headerHeight.addChangeListener(e -> accordion.setHeaderHeight(headerHeight.getValue()));

		accordion.addPage("General Settings", generalPanel);

		// Panel #2 - Page Specific Settings

		final JPanel pagePanel = new JPanel(new GridBagLayout());

		pagePanel.add(new JLabel("Page:"), GridBagData.forLabel(0, 0));
		final JComboBox<Accordion.Page> pageToChange = new JComboBox<>();
		pageToChange.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = -5334189626421605085L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				final JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
				label.setText(((Page) value).getTitle());
				return label;
			}
		});
		pagePanel.add(pageToChange, GridBagData.forControl(1, 0));

		pagePanel.add(new JLabel("Expanded:"), GridBagData.forLabel(0, 1));
		final JCheckBox pageExpanded = new JCheckBox();
		pageExpanded.setSelected(true);
		pagePanel.add(pageExpanded, GridBagData.forControl(1, 1));
		pageExpanded.addActionListener(
				e -> ((Accordion.Page) pageToChange.getSelectedItem()).setExpanded(pageExpanded.isSelected()));
		pageToChange.addActionListener(
				e -> pageExpanded.setSelected(((Accordion.Page) pageToChange.getSelectedItem()).isExpanded()));

		accordion.addPage("Page Settings", pagePanel);

		// init stuff that needs all pages

		pageToChange.setModel(new DefaultComboBoxModel<>(accordion.getPages()));
	}

	private static void addComponents(Component c1, Component c2, Container c, GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		c.add(c1, gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		c.add(c2, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
	}

}