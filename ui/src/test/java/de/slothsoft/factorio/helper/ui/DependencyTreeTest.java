package de.slothsoft.factorio.helper.ui;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import de.slothsoft.factorio.helper.Ingredient;
import de.slothsoft.factorio.helper.Recipe;
import de.slothsoft.factorio.helper.Result;
import de.slothsoft.factorio.helper.io.IOUtils;
import de.slothsoft.factorio.helper.ui.impl.RecipeCellFactory;
import javafx.scene.control.TreeItem;

public class DependencyTreeTest {

	@BeforeClass
	public static void setUpClass() throws IOException {
		RecipeCellFactory.setRecipeI18n(IOUtils.createEmptyResourceBundle());
	}

	@Rule
	public final TestName testName = new TestName();
	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	private DependencyTree dependencyTree;

	@Before
	public void setUp() {
		this.dependencyTree = new DependencyTree();
	}

	@Test
	public void testAddDependencyDefault() throws Exception {
		Assert.assertEquals(2, this.dependencyTree.tree.getColumns().size());
		Assert.assertEquals(0, this.dependencyTree.tree.getRoot().getChildren().size());
	}

	@Test
	public void testAddDependencyNoAvailable() throws Exception {
		this.dependencyTree.addDependency(new Dependency(this.testName.getMethodName()));

		Assert.assertEquals(2, this.dependencyTree.tree.getColumns().size());
		Assert.assertEquals(1, this.dependencyTree.tree.getRoot().getChildren().size());
	}

	@Test
	public void testAddDependencyDependencies() throws Exception {
		final String recipe1 = this.testName.getMethodName() + 1;
		final String recipe2 = this.testName.getMethodName() + 2;
		final String recipe3 = this.testName.getMethodName() + 3;

		this.dependencyTree.setAvailableRecipes(Arrays.asList( //
				new Recipe(recipe1).result(new Result(recipe1)).ingredient(new Ingredient(recipe2).amount(7)), //
				new Recipe(recipe2).result(new Result(recipe2)).ingredient(new Ingredient(recipe3).amount(3)), //
				new Recipe(recipe3).result(new Result(recipe3)) //
		));

		this.dependencyTree.addDependency(new Dependency(recipe1));

		Assert.assertEquals(2, this.dependencyTree.tree.getColumns().size());

		final TreeItem<Dependency> rootNode = this.dependencyTree.tree.getRoot();
		Assert.assertEquals(1, rootNode.getChildren().size());

		final TreeItem<Dependency> recipe1Node = rootNode.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe1), recipe1Node.getValue());
		Assert.assertEquals(1, recipe1Node.getChildren().size());

		final TreeItem<Dependency> recipe2Node = recipe1Node.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe2).factor(7.0), recipe2Node.getValue());
		Assert.assertEquals(1, recipe2Node.getChildren().size());

		final TreeItem<Dependency> recipe3Node = recipe2Node.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe3).factor(21.0), recipe3Node.getValue());
		Assert.assertEquals(0, recipe3Node.getChildren().size());
	}

	@Test
	public void testAddDependencyDependenciesWithOtherName() throws Exception {
		final String recipe1 = this.testName.getMethodName() + 1;
		final String recipe2 = this.testName.getMethodName() + 2;
		final String recipe3 = this.testName.getMethodName() + 3;

		this.dependencyTree.setAvailableRecipes(Arrays.asList( //
				new Recipe("A").result(new Result(recipe1)).ingredient(new Ingredient(recipe2).amount(7)), //
				new Recipe("B").result(new Result(recipe2)).ingredient(new Ingredient(recipe3).amount(3)), //
				new Recipe("C").result(new Result(recipe3)) //
		));

		this.dependencyTree.addDependency(new Dependency(recipe1));

		Assert.assertEquals(2, this.dependencyTree.tree.getColumns().size());

		final TreeItem<Dependency> rootNode = this.dependencyTree.tree.getRoot();
		Assert.assertEquals(1, rootNode.getChildren().size());

		final TreeItem<Dependency> recipe1Node = rootNode.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe1), recipe1Node.getValue());
		Assert.assertEquals(1, recipe1Node.getChildren().size());

		final TreeItem<Dependency> recipe2Node = recipe1Node.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe2).factor(7.0), recipe2Node.getValue());
		Assert.assertEquals(1, recipe2Node.getChildren().size());

		final TreeItem<Dependency> recipe3Node = recipe2Node.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe3).factor(21.0), recipe3Node.getValue());
		Assert.assertEquals(0, recipe3Node.getChildren().size());
	}

	@Test
	public void testAddDependencyDependenciesWithDoubleResult() throws Exception {
		final String recipe1 = this.testName.getMethodName() + 1;
		final String recipe2 = this.testName.getMethodName() + 2;

		this.dependencyTree.setAvailableRecipes(Arrays.asList( //
				new Recipe("A").result(new Result(recipe1)).ingredient(new Ingredient(recipe2).amount(7)), //
				new Recipe("B").result(new Result(recipe2).amount(7)) //
		));

		this.dependencyTree.addDependency(new Dependency(recipe1));

		Assert.assertEquals(2, this.dependencyTree.tree.getColumns().size());

		final TreeItem<Dependency> rootNode = this.dependencyTree.tree.getRoot();
		Assert.assertEquals(1, rootNode.getChildren().size());

		final TreeItem<Dependency> recipe1Node = rootNode.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe1), recipe1Node.getValue());
		Assert.assertEquals(1, recipe1Node.getChildren().size());

		final TreeItem<Dependency> recipe2Node = recipe1Node.getChildren().get(0);
		Assert.assertEquals(new Dependency(recipe2).factor(1.0), recipe2Node.getValue());
		Assert.assertEquals(0, recipe2Node.getChildren().size());
	}

}
