package fr.afcepf.atod.img.api;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import fr.afcepf.atod.exception.WineServerUtilException;

/**
 * Web service pour acceder aux images des vins
 * de l'application OnWine
 * @author ronan
 *
 */
@WebService(targetNamespace = "http://images.wine.al30.afcepf.fr")
public interface IServiceImage {
	/**
	 * Download des images
	 * @param idMark {@link Integer}
	 * @param idProduct {@link Integer}
	 * @return les images sous forme de string + leurs noms(cle)
	 */
	@WebMethod(operationName = "downloadImages")
    @WebResult(name = "resultImages64")
    Map<String,String> dlImage(@WebParam(name = "imageURL") String imageURL)
    	throws WineServerUtilException;
	/**
	 * Upload des images
	 * @param img l'image sous forme de string
	 * @param nameImage nom de l'image
	 * @return
	 */
	@WebMethod(operationName = "uploadImages")
	@WebResult(name="uploadImages")
	String uplImage(@WebParam(name = "encodeImage")String img,
			@WebParam(name="name") String nameImage) 
		throws WineServerUtilException;
}

