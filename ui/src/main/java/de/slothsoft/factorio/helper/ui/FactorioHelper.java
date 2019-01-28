package de.slothsoft.factorio.helper.ui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class FactorioHelper {

	static MainFrame mainFrame = new MainFrame();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (final Exception e) {
					// we do not need a look and feel
				}
				FactorioHelper.mainFrame = new MainFrame();
				FactorioHelper.mainFrame.open();
			}
		});
	}

	public static MainFrame getMainFrame() {
		return FactorioHelper.mainFrame;
	}
}
