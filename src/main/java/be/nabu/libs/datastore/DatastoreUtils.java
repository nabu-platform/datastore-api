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

import be.nabu.libs.datastore.api.ContextualStreamableDatastore;
import be.nabu.libs.datastore.api.ContextualWritableDatastore;
import be.nabu.libs.datastore.api.WritableDatastore;

public class DatastoreUtils {
	
	private static WritableDatastore memoryStore;
	
	public static WritableDatastore getMemoryStore() {
		if (memoryStore == null) {
			memoryStore = new MemoryDatastore();
		}
		return memoryStore;
	}
	
	public static WritableDatastore newMemoryStore() {
		return new MemoryDatastore();
	}
	
	public static <T> WritableDatastore scope(ContextualWritableDatastore<T> datastore, T context) {
		return new ScopedWritableDatastore(datastore, context);
	}
	
	public static <T> WritableDatastore scope(ContextualStreamableDatastore<T> datastore, T context) {
		return new ScopedStreamableDatastore(datastore, context);
	}
}
