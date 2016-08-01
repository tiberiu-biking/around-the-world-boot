package master.pam.server.impl.server;

import master.pam.server.api.request.IServerRequest;
import master.pam.server.api.server.IServer;
import master.pam.server.impl.request.RequestFactory;
import master.pam.server.impl.request.ServerRequest;
import master.pam.server.impl.response.base.envelope.IResponseEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server implements IServer {

    private final Logger log = LoggerFactory.getLogger(Server.class);

    private RequestFactory requestFactory;

    public Server(RequestFactory requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public IServerRequest createRequest() {
        return new ServerRequest();
    }

    @Override
    public IResponseEnvelope sendRequest(IServerRequest aServerRequest) {
        log.debug("Send request: " + aServerRequest);
        return requestFactory.createResult(aServerRequest);
    }

}
