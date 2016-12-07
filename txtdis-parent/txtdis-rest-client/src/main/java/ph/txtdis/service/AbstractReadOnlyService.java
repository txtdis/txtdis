package ph.txtdis.service;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.List;

import org.atteo.evo.inflector.English;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import ph.txtdis.exception.FailedAuthenticationException;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.exception.NoServerConnectionException;
import ph.txtdis.exception.RestException;
import ph.txtdis.exception.StoppedServerException;
import ph.txtdis.util.HttpHeader;
import ph.txtdis.util.TypeMap;

public abstract class AbstractReadOnlyService<T, H extends HttpHeader, RS extends RestService, RSS extends RestServerService>
		implements ReadOnlyService<T> {

	@Autowired
	private H http;

	@Autowired
	private RS restService;

	@Autowired
	private RSS serverService;

	@Autowired
	private TypeMap response;

	private String module;

	@Override
	public List<T> getList() throws NoServerConnectionException, StoppedServerException, FailedAuthenticationException,
			RestException, InvalidException {
		return getList("");
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getOne(String endpoint) throws NoServerConnectionException, StoppedServerException,
			FailedAuthenticationException, RestException, InvalidException {
		return (T) responseEntity(endpoint, single()).getBody();
	}

	@Override
	public AbstractReadOnlyService<T, H, RS, RSS> module(String module) {
		this.module = module;
		return this;
	}

	private HttpEntity<T> httpEntity(T entity) {
		return new HttpEntity<>(entity, http.headers());
	}

	private String plural() {
		return English.plural(single());
	}

	private ResponseEntity<?> responseEntity(String endpoint, String path) throws NoServerConnectionException,
			StoppedServerException, FailedAuthenticationException, RestException, InvalidException {
		try {
			return restService.init().exchange(url() + endpoint, GET, httpEntity(null), response.type(path));
		} catch (ResourceAccessException e) {
			e.printStackTrace();
			throw new NoServerConnectionException(serverService.getLocation());
		} catch (HttpClientErrorException | HttpServerErrorException e) {
			e.printStackTrace();
			if (e.getStatusCode() == UNAUTHORIZED) {
				if (e.getResponseBodyAsString().contains("This connection has been closed"))
					throw new StoppedServerException();
				else
					throw new FailedAuthenticationException();
			} else
				throw new RestException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidException(e.getMessage());
		}
	}

	private String url() {
		return "https://" + serverService.address() + ":" + serverService.getPort() + "/" + plural();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getList(String endpoint) throws NoServerConnectionException, StoppedServerException,
			FailedAuthenticationException, RestException, InvalidException {
		return (List<T>) responseEntity(endpoint, plural()).getBody();
	}

	protected String single() {
		return module;
	}
}