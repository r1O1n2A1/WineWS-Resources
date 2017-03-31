package fr.afcepf.atod.img.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import fr.afcepf.atod.exception.WineServerCodeError;
import fr.afcepf.atod.exception.WineServerUtilException;
import fr.afcepf.atod.img.api.IServiceImage;
import fr.afcepf.atod.utile.WineUtile;



@WebService(targetNamespace = "http://images.wine.al30.afcepf.fr",
endpointInterface = "fr.afcepf.atod.img.api.IServiceImage")
public class ServiceImage implements IServiceImage {
	private static final Logger logger = Logger.getLogger(ServiceImage.class);

	/* (non-Javadoc)
	 * @see fr.afcepf.atod.images.api.IServiceImage#uplImage(java.lang.String, java.lang.String)
	 */
	public String uplImage(String img, String nameImage) throws WineServerUtilException {
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.afcepf.atod.images.api.IServiceImage#dlImage(java.lang.String, java.lang.String)
	 */
	public Map<String,String> dlImage(String imageURL) throws WineServerUtilException {
		WineServerUtilException serverUtilException = null;
		Map<String,String> returnImages = new HashMap<>();
		if (imageURL != null && !imageURL.equalsIgnoreCase(WineUtile.EMPTY_STR)) {
			String pathImages = getClass().getResource("./").getPath();
			pathImages = pathImages.split("/WEB-INF")[0];
			pathImages += WineUtile.PATH_IMG + WineUtile.PICTURES + imageURL;
			File file = new File(pathImages);			
			if (file.exists() && file.isDirectory()) {
				try {
					returnImages = writeOutputMapImgs(returnImages, file);
				}
				catch (IOException e) {
					logger.error(e);
				}
			} else {
				serverUtilException = new WineServerUtilException(WineServerCodeError.FICHIER_INTROUVABLE,
						"Files not found");
			}
		} else {
			serverUtilException = new WineServerUtilException(WineServerCodeError.URL_JOINTE_FAUSSE,
					"the incoming url for the image is wrong.");
		}

		if (serverUtilException != null) {
			throw serverUtilException;
		}
		return returnImages;
	}

	/**
	 * Filling key/value from the map
	 * @param returnImages images key-value name-encoded
	 * @param file {@link File}
	 * @return
	 * @throws IOException @{@link IOException}
	 */
	private Map<String,String> writeOutputMapImgs(Map<String,String> returnImages, File file) 
			throws IOException {
		File[] imgs = file.listFiles();
		for (File f : imgs) {				
			byte[] bytes = new byte[(int)f.length()];
			FileInputStream fis = new FileInputStream(f);
			fis.read(bytes);
			returnImages.put(f.getName(), Base64.encodeBase64String(bytes));
			fis.close();
		}
		return returnImages;

	}
}