package br.com.kontrola.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import br.com.kontrola.project.Issue.Status;

public class IssueTest {

	@Test
	public void testConstructor() {
		Issue issue = new Issue("PROJECT A", "NAME WITH SPACES");
		assertEquals("name-with-spaces", issue.getName());
		assertEquals(Status.RED, issue.getStatus());
		assertNotNull(issue.getLastUpdate());
	}

	@Test
	public void testEquals() {
		Issue issueA = new Issue("PROJECT A", "A");
		Issue issueA2 = new Issue("PROJECT A", "A");
		Issue issueB = new Issue("PROJECT B", "A");

		assertTrue(issueA.equals(issueA2));
		assertFalse(issueA.equals(issueB));
	}

	@Test
	public void updateStatus() throws InterruptedException {
		Issue issueA = new Issue("PROJECT A", "A");
		Date lastUpdate = issueA.getLastUpdate();

		Thread.sleep(100);
		issueA.updateStatus(Status.GREEN);

		assertEquals(Status.GREEN, issueA.getStatus());
		assertTrue(issueA.getLastUpdate().after(lastUpdate));
	}

}
