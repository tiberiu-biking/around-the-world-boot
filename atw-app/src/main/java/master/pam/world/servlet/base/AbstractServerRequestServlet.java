package master.pam.world.servlet.base;

import com.tpo.world.services.util.GsonHelper;
import com.tpo.world.web.api.ServerActionsEnum;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.server.IServer;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
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
    private IServer serverIf;

    public AbstractServerRequestServlet(IServer serverIf) {
        this.serverIf = serverIf;
    }

    abstract protected ServerActionsEnum getServerAction();

    abstract protected void buildServerRequest(IServerRequest aServerRequest) throws WrongRequestException;

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
        logger.info("Server action: " + getServerAction());

        IServerRequest serverRequest = serverIf.createRequest();

        serverRequest.setAction(getServerAction());

        IResponseEnvelope resultObj;
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

    protected String buildResult(IResponseEnvelope aResponseEnvelope) {
        logger.debug("Build result. Server result: " + aResponseEnvelope);
        return buildGson(aResponseEnvelope);
    }

    protected String buildGson(IResponseEnvelope aResponseEnvelope) {
        return GsonHelper.toGson(aResponseEnvelope);
    }

    public IServer getServer() {
        return serverIf;
    }
}
