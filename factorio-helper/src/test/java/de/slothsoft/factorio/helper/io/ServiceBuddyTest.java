package de.slothsoft.factorio.helper.io;

import org.junit.Assert;
import org.junit.Test;

public class ServiceBuddyTest {

	@Test
	public void testFindService() throws Exception {
		final TestService testService = ServiceBuddy.findService(TestService.class);
		Assert.assertNotNull(testService);
		Assert.assertTrue(testService instanceof TestServiceImpl);
	}

	@Test
	public void testFindServiceNull() throws Exception {
		final TestServiceWithoutImpl testService = ServiceBuddy.findService(TestServiceWithoutImpl.class);
		Assert.assertNull(testService);
	}

	@Test
	public void testGetService() throws Exception {
		final TestService testService = ServiceBuddy.getService(TestService.class);
		Assert.assertNotNull(testService);
		Assert.assertTrue(testService instanceof TestServiceImpl);
	}

	@Test
	public void testGetServiceNull() throws Exception {
		try {
			ServiceBuddy.getService(TestServiceWithoutImpl.class);
			Assert.fail("There should have been an exception!");
		} catch (final NullPointerException e) {
			Assert.assertEquals("Could not find service for TestServiceWithoutImpl.class", e.getMessage());
		}
	}
}
