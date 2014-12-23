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
