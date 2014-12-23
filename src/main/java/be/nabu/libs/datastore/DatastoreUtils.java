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
