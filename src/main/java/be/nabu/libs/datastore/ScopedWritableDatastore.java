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
import java.io.InputStream;
import java.net.URI;

import be.nabu.libs.datastore.api.ContextualWritableDatastore;
import be.nabu.libs.datastore.api.DataProperties;
import be.nabu.libs.datastore.api.WritableDatastore;

public class ScopedWritableDatastore implements WritableDatastore {

	@SuppressWarnings("rawtypes")
	private ContextualWritableDatastore datastore;
	private Object context;
	
	public <T> ScopedWritableDatastore(ContextualWritableDatastore<T> datastore, T context) {
		this.datastore = datastore;
		this.context = context;
	}
	
	@Override
	public InputStream retrieve(URI uri) throws IOException {
		return datastore.retrieve(uri);
	}

	@Override
	public DataProperties getProperties(URI uri) throws IOException {
		return datastore.getProperties(uri);
	}

	@SuppressWarnings("unchecked")
	@Override
	public URI store(InputStream input, String name, String contentType) throws IOException {
		return datastore.store(context, input, name, contentType);
	}
	
	Object getContext() {
		return context;
	}
	
	ContextualWritableDatastore<?> getDatastore() {
		return datastore;
	}
}
