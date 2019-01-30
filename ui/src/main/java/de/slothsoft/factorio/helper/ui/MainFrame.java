package de.slothsoft.factorio.helper.ui;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.io.FactorioReader;
import de.slothsoft.factorio.helper.io.RecipeReader;
import de.slothsoft.factorio.helper.io.ServiceBuddy;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 3560924069865234910L;

	public MainFrame() {
		setTitle(Messages.getString("FactorioHelper"));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		final FactorioReader factorioReader = ServiceBuddy.getService(FactorioReader.class);
		final RecipeReader recipeReader = ServiceBuddy.getService(RecipeReader.class);
		try {
			final List<Recipe> result = recipeReader.readRecipes(factorioReader.getAllRecipeFileStreams());
//			setContentPane(new RecipesTable().recipies(result));
			setContentPane(new DependencyTree());
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pack();
	}

	public void open() {
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
