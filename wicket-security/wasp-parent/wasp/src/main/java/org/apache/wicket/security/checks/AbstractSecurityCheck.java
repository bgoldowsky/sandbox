/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.security.checks;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.security.WaspApplication;
import org.apache.wicket.security.WaspSession;
import org.apache.wicket.security.actions.ActionFactory;
import org.apache.wicket.security.actions.WaspAction;
import org.apache.wicket.security.strategies.WaspAuthorizationStrategy;


/**
 * Basic check providing some utility methods.
 * 
 * @author marrink
 * @see ISecurityCheck
 */
public abstract class AbstractSecurityCheck implements ISecurityCheck
{
	/**
	 * Noop constructor for Serialization.
	 * 
	 */
	protected AbstractSecurityCheck()
	{
	}

	/**
	 * Utility method to get the factory to create {@link WaspAction}s.
	 * 
	 * @return the factory
	 * @see WaspApplication#getActionFactory()
	 */
	protected final ActionFactory getActionFactory()
	{
		return ((WaspApplication)Application.get()).getActionFactory();
	}

	/**
	 * Utility method to get to the {@link WaspAuthorizationStrategy}.
	 * 
	 * @return the strategy
	 * @see WaspSession#getAuthorizationStrategy()
	 */
	protected final WaspAuthorizationStrategy getStrategy()
	{
		return (WaspAuthorizationStrategy)((WaspSession)Session.get()).getAuthorizationStrategy();
	}

	/**
	 * Utility method to get the class of the loginpage.
	 * 
	 * @return the login page
	 * @see WaspApplication#getLoginPage()
	 */
	protected final Class getLoginPage()
	{
		return ((WaspApplication)Application.get()).getLoginPage();
	}

	/**
	 * Shortcut to {@link ISecurityCheck#isActionAuthorized(WaspAction)} if you
	 * quickly want to check an action. Note that this is the same as doing
	 * something like </br> <code>
	 * check.isActionAuthorized(((WaspApplication)Application.get()).getActionFactory().getAction(Render.class));
	 * </code>
	 * 
	 * @param waspAction
	 * @return true if the action is authorized, false otherwise
	 * @throws WicketRuntimeException
	 *             if the class is not an instance of {@link WaspAction}
	 */
	public boolean isActionAuthorized(Class waspAction)
	{
		if (!WaspAction.class.isAssignableFrom(waspAction))
			throw new WicketRuntimeException("The class: " + waspAction + " is not a valid "
					+ WaspAction.class);
		return isActionAuthorized(getActionFactory().getAction(waspAction));
	}
}
