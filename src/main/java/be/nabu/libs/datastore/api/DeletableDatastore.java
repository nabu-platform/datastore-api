package be.nabu.libs.datastore.api;

import java.io.IOException;
import java.net.URI;

import javax.jws.WebParam;

public interface DeletableDatastore extends Datastore {
	public void delete(@WebParam(name = "uri") URI uri) throws IOException;
}
