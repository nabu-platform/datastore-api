package be.nabu.libs.datastore.api;

import java.io.IOException;

import be.nabu.libs.datastore.DatastoreOutputStream;

public interface ContextualStreamableDatastore<T> extends ContextualWritableDatastore<T> {
	public DatastoreOutputStream stream(T context, String name, String contentType) throws IOException;
}
