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
