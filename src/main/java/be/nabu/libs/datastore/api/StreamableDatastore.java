package be.nabu.libs.datastore.api;

import java.io.IOException;

import javax.jws.WebParam;
import javax.jws.WebResult;

import be.nabu.libs.datastore.DatastoreOutputStream;

public interface StreamableDatastore extends WritableDatastore {
	@WebResult(name = "datastoreOutputStream")
	public DatastoreOutputStream stream(@WebParam(name = "name") String name, @WebParam(name = "contentType") String contentType) throws IOException;
}
