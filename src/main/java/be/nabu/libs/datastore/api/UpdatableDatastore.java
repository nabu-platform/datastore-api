package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public interface UpdatableDatastore extends Datastore {
	public void update(URI uri, InputStream input) throws IOException;
}
