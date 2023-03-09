package tn.devteam.immonexus.Services;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import tn.devteam.immonexus.Interfaces.IImageVerificationService;

import java.io.IOException;
import java.util.Map;

import static org.opencv.imgcodecs.Imgcodecs.imread;

@Service
@Slf4j
public class ImageVerificationService implements IImageVerificationService {

@Override
    public boolean isRealEstateImage(MultipartFile file) throws Exception {
        return true;

}

    /*
    @Override
    public boolean isRealEstateImage2(MultipartFile file) throws IOException {
        File imageFile = new File(String.valueOf(file));
        BufferedImage image = ImageIO.read(imageFile);

        if (image == null) {
            return false;
        }

        int width = image.getWidth();
        int height = image.getHeight();

        if (width < 400 || height < 300) {
            return false;
        }

        double aspectRatio = (double) width / (double) height;

        if (aspectRatio < 1.2 || aspectRatio > 2.5) {
            return false;
        }

        return true;
    }

     */
    @Override
    public ResponseEntity<String> verifyImage(MultipartFile file) throws IOException {

        // Créer un objet RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Construire les headers de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Construire les données de la requête (ici, le fichier image)
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", new org.springframework.core.io.InputStreamResource(file.getInputStream()));

        // Construire l'objet HttpEntity à partir des headers et des données
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Envoyer la requête POST à l'API RESTful
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity("http://127.0.0.1:5000/", requestEntity, Map.class);

        // Extraire la réponse JSON de l'objet ResponseEntity
        Map<String, Object> responseMap = responseEntity.getBody();

        // Récupérer la valeur 'is_immo' de la réponse JSON
        Boolean isImmo = (Boolean) responseMap.get("is_immo");

        // Retourner une réponse HTTP 200 OK avec la valeur 'is_immo' dans le corps de la réponse
        return new ResponseEntity<String>(isImmo.toString(), HttpStatus.OK);
    }
@Override
    public  boolean isRealEstateImage2(String imagePath) throws IOException, InterruptedException {

        // Chargez l'image à partir du chemin du fichier
    String openCvLibraryPath = "C:/opencv/build/java/x64";
    System.load(openCvLibraryPath + "/opencv_java470.dll");
log.info("chemmin : "+imagePath);
    // Votre code OpenCV ici
     //  Mat image = imread(imagePath);
    String imagePath2 = "C:/PiDevFiles/"+imagePath;
        Mat image = imread(imagePath2);
    log.info("chemmin : "+imagePath2);

     //  CascadeClassifier buildingDetector = new CascadeClassifier("C:\\opencv\\sources\\data\\haarcascades\\haarcascade_eye.xml");
        CascadeClassifier classifier = new CascadeClassifier();
        classifier.load("C:\\data1\\classifier\\cascade.xml");
        // Détectez les bâtiments dans l'image
        MatOfRect buildings = new MatOfRect();
    classifier.detectMultiScale(image, buildings);
  log.info("Building ....."+buildings);
  boolean v=true;
  if(!buildings.empty()){
      return true;
  }else {
      return false;
  }
    }


}
