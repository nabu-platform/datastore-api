package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public interface WritableDatastore extends Datastore {
	public URI store(InputStream input, String name, String contentType) throws IOException;
}
