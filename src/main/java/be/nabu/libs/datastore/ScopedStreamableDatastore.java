/*
* Copyright (C) 2014 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

package be.nabu.libs.datastore;

import java.io.IOException;

import be.nabu.libs.datastore.api.ContextualStreamableDatastore;
import be.nabu.libs.datastore.api.StreamableDatastore;

public class ScopedStreamableDatastore extends ScopedWritableDatastore implements StreamableDatastore {

	public <T> ScopedStreamableDatastore(ContextualStreamableDatastore<T> datastore, T context) {
		super(datastore, context);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public DatastoreOutputStream stream(String name, String contentType) throws IOException {
		return ((ContextualStreamableDatastore) getDatastore()).stream(getContext(), name, contentType);
	}

}
