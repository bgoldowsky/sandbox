package org.wicketstuff.iolite.persistence;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.jpa.AbstractJpaTests;
import org.wicketstuff.iolite.persistence.provider.GeneralRepository;

/**
 * Base class for simplifying tests.
 * 
 * Inherits from AbstractJpaTests, which means each test is run in a transaction
 * that is rolled back after each test. No need to clean up after yourself.
 * 
 */
@ContextConfiguration(locations = "AllInOneRepositoryContext.xml")
public abstract class TestSupport extends AbstractJpaTests {

	
	

	protected GeneralRepository generalRepository;

	public TestSupport() {
		super();
	}
	@Override
	protected void onSetUpBeforeTransaction() throws Exception {
		super.onSetUpBeforeTransaction();

	}
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:AllInOneRepositoryContext.xml" };
	}

	public void setGeneralRepository(GeneralRepository generalRepository) {
		this.generalRepository = generalRepository;
	}

}
