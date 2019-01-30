package de.slothsoft.factorio.helper.ui.common;

import javax.swing.JLabel;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class AccordionTest {

	@Rule
	public TestName testName = new TestName();
	private final Accordion accordion = new Accordion();

	@Test
	public void testSetDefaultExpandedTrue() throws Exception {
		this.accordion.setDefaultExpanded(true);
		Assert.assertTrue(this.accordion.isDefaultExpanded());

		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertTrue(page.isExpanded());
	}

	@Test
	public void testSetDefaultExpandedFalse() throws Exception {
		this.accordion.setDefaultExpanded(false);
		Assert.assertFalse(this.accordion.isDefaultExpanded());

		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertFalse(page.isExpanded());
	}

	@Test
	public void testDefaultExpandedTrue() throws Exception {
		this.accordion.defaultExpanded(true);
		Assert.assertTrue(this.accordion.isDefaultExpanded());

		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertTrue(page.isExpanded());
	}

	@Test
	public void testDefaultExpandedFalse() throws Exception {
		this.accordion.defaultExpanded(false);
		Assert.assertFalse(this.accordion.isDefaultExpanded());

		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertFalse(page.isExpanded());
	}

	@Test
	public void testGetPages() throws Exception {
		Assert.assertArrayEquals(new Accordion.Page[0], this.accordion.getPages());

		final Accordion.Page page1 = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertArrayEquals(new Accordion.Page[]{page1}, this.accordion.getPages());

		final Accordion.Page page2 = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertArrayEquals(new Accordion.Page[]{page1, page2}, this.accordion.getPages());

	}

	@Test
	public void testSetHeaderHeight() throws Exception {
		this.accordion.setHeaderHeight(5);
		Assert.assertEquals(5, this.accordion.getHeaderHeight());

		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertEquals(5, page.getPreferredSize().height);
	}

	@Test
	public void testSetHeaderHeightLater() throws Exception {
		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());

		this.accordion.setHeaderHeight(6);
		Assert.assertEquals(6, this.accordion.getHeaderHeight());
		Assert.assertEquals(6, page.getPreferredSize().height);
	}

	@Test
	public void testHeaderHeight() throws Exception {
		this.accordion.headerHeight(7);
		Assert.assertEquals(7, this.accordion.getHeaderHeight());

		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());
		Assert.assertEquals(7, page.getPreferredSize().height);
	}

	@Test
	public void testHeaderHeightLater() throws Exception {
		final Accordion.Page page = this.accordion.addPage(this.testName.getMethodName(), new JLabel());

		this.accordion.headerHeight(8);
		Assert.assertEquals(8, this.accordion.getHeaderHeight());
		Assert.assertEquals(8, page.getPreferredSize().height);
	}

}
