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
package org.wicketstuff.jquery;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebComponent;

@SuppressWarnings("serial")
public abstract class DnDSortableHandler extends WebComponent {
    private DnDSortableBehavior dnd_;

    public DnDSortableHandler(String id) throws Exception {
        this(id, null, null);
    }

    /**
     * the Sortable's options (see http://interface.eyecon.ro/docs/sort for the
     * list of options).
     *
     * @param options
     * @see http://interface.eyecon.ro/docs/sort
     */
    public DnDSortableHandler(String id, Options options) throws Exception {
        this(id, options, null);
    }

    /**
     * the Sortable's options (see http://interface.eyecon.ro/docs/sort for the
     * list of options).
     *
     * @param options
     * @see http://interface.eyecon.ro/docs/sort
     */
    public DnDSortableHandler(String id, Options options, String containerCSSClass) throws Exception {
        super(id);
        dnd_ = new DnDSortableBehavior(options, containerCSSClass){
            @Override
            public boolean onDnD(AjaxRequestTarget target, MarkupContainer srcContainer, int srcPos, MarkupContainer destContainer, int destPos) throws Exception {
                return ((DnDSortableHandler)getComponent()).onDnD(target, srcContainer, srcPos, destContainer, destPos);
            }
        };
        add(dnd_);
    }

    /**
     * Call when a component has been moved on client side.
     * @param target a target, provide if a response,
     * @param item the moved component(null if not a child (direct or indirect) of srcContainer or destContainer.
     * @param srcContainer the source container from where item come, (null if not previously registered by via registerContainer(...)).
     * @param srcPos the position/index of item into srcContainer before moving.
     * @param destContainer the destination container where item is, (null if not previously registered by via registerContainer(...)).
     * @param destPos the position/index of item into srcContainer after moving.
     * @return false if you don't need to keep in sync component, markupId on serverside and client side,
     *  else return true to send to client side the srcContainer and destContainer and to update the handler (consume more resource, server, network, client).
     * @throws Exception
     */
    public abstract boolean onDnD(AjaxRequestTarget target, MarkupContainer srcContainer, int srcPos, MarkupContainer destContainer, int destPos) throws Exception;


    /**
     * Register a container as a container for draggable/droppable items.
     * (add the css class and markupId to be find on clientside).
     *
     * @param v the container to register.
     * @return this
     * @throws Exception
     */
    public DnDSortableHandler registerContainer(MarkupContainer v) throws Exception {
        dnd_.registerContainer(v);
        return this;
    }

    /**
     * Register a component as draggable/moveable item.
     * (add the css class and markupId to be find on clientside).
     *
     * @param v the component to register.
     * @return this
     * @throws Exception
     */
    public DnDSortableHandler registerItem(Component v) throws Exception {
        dnd_.registerItem(v);
        return this;
    }
}
