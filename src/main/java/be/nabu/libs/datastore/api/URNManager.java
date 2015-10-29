package be.nabu.libs.datastore.api;

import java.net.URI;

import javax.jws.WebParam;
import javax.jws.WebResult;

public interface URNManager {
	/**
	 * Map a URL to a URN
	 */
	@WebResult(name = "urn")
	public URI map(@WebParam(name = "url") URI url);
	
	/**
	 * Return the URL associated with this URN
	 */
	@WebResult(name = "url")
	public URI resolve(@WebParam(name = "urn") URI urn);
}
