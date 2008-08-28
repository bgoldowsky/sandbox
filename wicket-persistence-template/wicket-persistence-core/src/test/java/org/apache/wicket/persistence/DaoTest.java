package org.apache.wicket.persistence;

import org.apache.log4j.Logger;
import org.apache.wicket.persistence.domain.Message;

public class DaoTest extends TestSupport {

	private Logger log = Logger.getLogger(DaoTest.class);

	public void testIsSomethingWorking() {
		Message message = new Message("Hello world");
		messageRepository.add(message);

		assertEquals(1, messageRepository.getAll().size());
	}
	public void testIsSomethingWorkingGetAllAsList() {
		Message message = new Message("Hello world");
		messageRepository.add(message);

		assertEquals(1, messageRepository.getAllAsList().size());
	}

}
