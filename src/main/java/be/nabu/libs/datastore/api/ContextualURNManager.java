package be.nabu.libs.datastore.api;

import java.net.URI;

import javax.jws.WebParam;
import javax.jws.WebResult;

public interface ContextualURNManager extends URNManager {
	@WebResult(name = "urn")
	public URI map(@WebParam(name = "context") String context, @WebParam(name = "url") URI url);
}
