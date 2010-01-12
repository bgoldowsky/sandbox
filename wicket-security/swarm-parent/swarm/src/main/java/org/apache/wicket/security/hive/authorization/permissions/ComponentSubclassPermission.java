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
package org.apache.wicket.security.hive.authorization.permissions;

import org.apache.wicket.Component;
import org.apache.wicket.security.actions.WaspAction;

/**
 * Same as {@link ComponentPermission}, but also gives permission for subclasses
 * of the component class.
 * 
 * @author papegaaij
 */
public class ComponentSubclassPermission extends ComponentPermission
{
	private static final long serialVersionUID = 4906335298749077918L;

	public ComponentSubclassPermission(Component component, WaspAction action)
	{
		super(component, action);
	}

	public ComponentSubclassPermission(String componentAlias, WaspAction actions)
	{
		super(componentAlias, actions);
	}

	protected boolean implies(String[] path1, String[] path2)
	{
		int i = 0;
		int j = 0;
		for (; i < path1.length && j < path2.length; j++)
		{
			if ((i == 0 && j == 0 && isSubclass(path1[i], path2[j])) || path1[i].equals(path2[j]))
			{
				i++;
			}
			else if (i > 0)
				return false;
		}
		return path1.length == i;
		// TODO wildcards would be nice:
		// e.g org.MyPage:*:SomeComponent * fits just one parts
		// org.MyPage:**:SomeComponent ** fits one or more parts so
		// Panel1:Panel2 would be valid and we could grant permissions for
		// components at an unknown depth
		// ? could be used to fit a single char wildcard
	}

	private boolean isSubclass(String classname1, String classname2)
	{
		try
		{
			Class class1 = Class.forName(classname1);
			Class class2 = Class.forName(classname2);
			return class2.isAssignableFrom(class1);
		}
		catch (ClassNotFoundException e)
		{
		}
		return false;
	}
}
