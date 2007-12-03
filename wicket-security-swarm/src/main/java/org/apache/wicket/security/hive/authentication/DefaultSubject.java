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
package org.apache.wicket.security.hive.authentication;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.wicket.Component;
import org.apache.wicket.model.IModel;
import org.apache.wicket.security.hive.authorization.Principal;


/**
 * Default implementation of a Subject. By default it authenticates all classes,
 * components and models. This makes it an ideal candidate for single login
 * applications.
 * 
 * @author marrink
 * @see Subject
 */
public class DefaultSubject implements Subject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean readOnly;

	private Set principals = new HashSet(100);// guess
	private Set readOnlyPrincipals = Collections.unmodifiableSet(principals);

	/**
	 * @see org.apache.wicket.security.hive.authentication.Subject#getPrincipals()
	 */
	public final Set getPrincipals()
	{
		return readOnlyPrincipals;
	}

	/**
	 * @see org.apache.wicket.security.hive.authentication.Subject#isReadOnly()
	 */
	public final boolean isReadOnly()
	{
		return readOnly;
	}

	/**
	 * @see org.apache.wicket.security.hive.authentication.Subject#setReadOnly()
	 */
	public final void setReadOnly()
	{
		this.readOnly = true;
	}

	/**
	 * Adds a new principal to this subject.
	 * 
	 * @param principal
	 * @return true if the principal was added, false if it wasn't for instance
	 *         because the subject is readonly.
	 */
	public final boolean addPrincipal(Principal principal)
	{
		if (readOnly)
			return false;
		if (principal == null)
			throw new IllegalArgumentException("principal can not be null.");
		return principals.add(principal);
	}

	/**
	 * 
	 * @see org.apache.wicket.security.hive.authentication.Subject#isClassAuthenticated(java.lang.Class)
	 */
	public boolean isClassAuthenticated(Class class1)
	{
		return true;
	}

	/**
	 * 
	 * @see org.apache.wicket.security.hive.authentication.Subject#isComponentAuthenticated(org.apache.wicket.Component)
	 */
	public boolean isComponentAuthenticated(Component component)
	{
		return true;
	}

	/**
	 * 
	 * @see org.apache.wicket.security.hive.authentication.Subject#isModelAuthenticated(org.apache.wicket.model.IModel,
	 *      org.apache.wicket.Component)
	 */
	public boolean isModelAuthenticated(IModel model, Component component)
	{
		return true;
	}
}
