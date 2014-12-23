package be.nabu.libs.datastore.api;

import java.net.URI;

public interface URNManager {
	/**
	 * Map a URL to a URN
	 */
	public URI map(URI url);
	
	/**
	 * Return the URL associated with this URN
	 */
	public URI resolve(URI urn);
}
