package com.tpo.world.geo.tagging;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;
import com.tpo.world.model.geo.GeoPoint;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Date;

public class PictureTaggingUtil {

    public static PictureTags getGeoLocation(BufferedInputStream aInputStream) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(aInputStream, false);

        GpsDirectory gpsDirectory = metadata.getDirectory(GpsDirectory.class);
        if (gpsDirectory == null) {
            return null;
        }
        GeoLocation location = gpsDirectory.getGeoLocation();
        GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());

        ExifSubIFDDirectory dateDirectory = metadata.getDirectory(ExifSubIFDDirectory.class);
        Date dateTaken = dateDirectory.getDate(ExifSubIFDDirectory.TAG_DATETIME_DIGITIZED);

        return new PictureTags(geoPoint, dateTaken);
    }
}
