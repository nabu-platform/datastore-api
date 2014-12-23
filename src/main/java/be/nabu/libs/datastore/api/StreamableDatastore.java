package be.nabu.libs.datastore.api;

import java.io.IOException;

import be.nabu.libs.datastore.DatastoreOutputStream;

public interface StreamableDatastore extends WritableDatastore {
	public DatastoreOutputStream stream(String name, String contentType) throws IOException;
}
