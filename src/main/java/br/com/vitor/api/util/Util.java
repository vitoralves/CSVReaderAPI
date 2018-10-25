package br.com.vitor.api.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


public class Util {

	public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
		CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }
	
	/* Calculate distance between two points in latitude 
	and longitude taking into account height difference. 
	If you are not interested in height difference pass 0.0.
	 Uses Haversine method as its base. lat1, lon1 Start point 
	lat2, lon2 End point el1 Start altitude in meters el2 End altitude 
	in meters */

	public static double distance(double lat1, double lat2, double lon1, double lon2) {

	    final int R = 6371; // Radius of the earth

	    Double latDistance = deg2rad(lat2 - lat1);
	    Double lonDistance = deg2rad(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c;

	    distance = Math.pow(distance, 2);
	    return Math.sqrt(distance);
	}

	public static double deg2rad(double deg) {
	    return (deg * Math.PI / 180.0);
	}

}
