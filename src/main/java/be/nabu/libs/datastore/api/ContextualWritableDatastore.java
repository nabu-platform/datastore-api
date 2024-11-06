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

package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Allows the datastore to store data based on a given context
 * All implementations must also implement a "default" scenario where no context is given which is why it extends the plain writable datastore
 */
public interface ContextualWritableDatastore<T> extends WritableDatastore {
	public URI store(T context, InputStream input, String name, String contentType) throws IOException;
	public Class<T> getContextClass();
}
