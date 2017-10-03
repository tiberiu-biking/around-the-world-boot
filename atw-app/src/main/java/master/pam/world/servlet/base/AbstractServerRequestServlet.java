package master.pam.world.servlet.base;

import com.tpo.world.services.util.GsonHelper;
import com.tpo.world.web.api.Server;
import com.tpo.world.web.api.ServerRequest;
import com.tpo.world.web.domain.ServerAction;
import com.tpo.world.web.impl.response.base.envelope.ResponseEnvelope;
import master.pam.world.servlet.exception.WrongRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractServerRequestServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AbstractServerRequestServlet.class);
    private HttpServletRequest httpRequest;
    private Server serverIf;

    public AbstractServerRequestServlet(Server serverIf) {
        this.serverIf = serverIf;
    }

    abstract protected ServerAction getServerAction();

    abstract protected void buildServerRequest(ServerRequest aServerRequest) throws WrongRequestException;

    @Override
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        doPost(aRequest, aResponse);
    }

    @Override
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {

        logger.debug("doPost " + getClass().getCanonicalName());

        httpRequest = aRequest;

        doRequest(aResponse);
    }

    protected void doRequest(HttpServletResponse aResponse) throws ServletException, java.io.IOException {
        logger.info("InternalServer action: " + getServerAction());

        ServerRequest serverRequest = serverIf.createRequest();

        serverRequest.setAction(getServerAction());

        ResponseEnvelope resultObj;
        try {
            buildServerRequest(serverRequest);
            resultObj = serverIf.sendRequest(serverRequest);
        } catch (WrongRequestException e) {
            resultObj = e.getErrorEnvelope();
        }

        String resultJson = buildResult(resultObj);

        logger.info("Sending json to server: " + resultJson);
        aResponse.setContentType("application/json");
        aResponse.setCharacterEncoding("utf-8");
        aResponse.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = aResponse.getWriter();
        out.println(resultJson);
    }

    protected String getHttpParam(String aParamName) {
        return httpRequest.getParameter(aParamName);
    }

    protected Collection<Part> getHttpRequestParts() {
        try {
            return httpRequest.getParts();
        } catch (IllegalStateException | IOException | ServletException e) {
            // TODO
            e.printStackTrace();
            logger.error("The http request has no parts!", e);
            return new ArrayList<>();
        }
    }

    protected String buildResult(ResponseEnvelope aResponseEnvelope) {
        logger.debug("Build result. InternalServer result: " + aResponseEnvelope);
        return buildGson(aResponseEnvelope);
    }

    protected String buildGson(ResponseEnvelope aResponseEnvelope) {
        return GsonHelper.toGson(aResponseEnvelope);
    }

    public Server getServer() {
        return serverIf;
    }
}
