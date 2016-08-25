package master.pam.world.servlet.impl.marker;

import com.drew.imaging.ImageProcessingException;
import com.tpo.world.api.geo.coding.api.IGeoCodingAPI;
import com.tpo.world.api.geo.dto.impl.IAddressDto;
import com.tpo.world.api.geo.geo.GeoPoint;
import com.tpo.world.api.geo.tagging.PictureTaggingUtil;
import com.tpo.world.api.geo.tagging.PictureTags;
import com.tpo.world.domain.entity.MarkerEntity;
import com.tpo.world.web.api.ServerActionsEnum;
import com.tpo.world.web.api.request.IServerRequest;
import com.tpo.world.web.api.request.RequestConstants;
import com.tpo.world.web.api.server.IServer;
import com.tpo.world.web.impl.response.base.envelope.IResponseEnvelope;
import master.pam.world.servlet.base.AbstractServerRequestServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@MultipartConfig
public class UploadPictureServlet extends AbstractServerRequestServlet {

    private static final Logger logger = LoggerFactory.getLogger(UploadPictureServlet.class);

    private boolean isGPSMissing;

    private IGeoCodingAPI geoCodingAPI;

    public UploadPictureServlet(IServer serverIf, IGeoCodingAPI geoCodingAPI) {
        super(serverIf);
        this.geoCodingAPI = geoCodingAPI;
    }

    @RequestMapping(value = "/UploadPictureServlet", method = RequestMethod.GET)
    public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doGet(aRequest, aResponse);
    }

    @RequestMapping(value = "/UploadPictureServlet", method = RequestMethod.POST)
    public void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse) throws ServletException, IOException {
        super.doPost(aRequest, aResponse);
    }

    @Override
    protected ServerActionsEnum getServerAction() {
        return ServerActionsEnum.ADD_MARKERS;
    }

    @Override
    protected void buildServerRequest(IServerRequest aServerRequest) {
        Collection<Part> parts = getHttpRequestParts();
        isGPSMissing = false;
        List<MarkerEntity> markers = new ArrayList<>();

        for (Part part : parts) {
            if (part.getContentType() != null) {
                try {
                    InputStream inputStream = part.getInputStream();

                    BufferedInputStream pictureStream = new BufferedInputStream(inputStream);
                    PictureTags pictureTags = PictureTaggingUtil.getGeoLocation(pictureStream);
                    if (pictureTags == null) {
                        isGPSMissing = true;
                        return;
                    }
                    GeoPoint location = pictureTags.getLocation();
                    MarkerEntity markerEntity = new MarkerEntity();
                    markerEntity.setLatitude(location.getLatitude());
                    markerEntity.setLongitude(location.getLongitude());
                    markerEntity.setDate(pictureTags.getDateTaken());

                    markerEntity.setUserId(Long.parseLong(getHttpParam(RequestConstants.USER_ID)));
                    markerEntity.setNote("Imported from a picture ");

                    // geo coding
                    IAddressDto address = geoCodingAPI.getAddress(markerEntity.getLatitude(), markerEntity.getLongitude());
                    markerEntity.setName(address.getShortAddress());
                    markers.add(markerEntity);

                } catch (IOException | ImageProcessingException e) {
                    logger.error("FileUploadServlet: cannot get location from picture!", e);
                    isGPSMissing = true;
                }
            }
        }
        aServerRequest.addField(RequestConstants.DTO_LIST, markers);

    }

    @Override
    protected String buildResult(IResponseEnvelope aResponseEnvelope) {
        if (isGPSMissing) {
            aResponseEnvelope.addDataMessage("Picture(s) doesn't contain GPS information.");
        }

        return super.buildResult(aResponseEnvelope);
    }

}