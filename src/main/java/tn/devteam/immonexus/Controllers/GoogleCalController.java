package tn.devteam.immonexus.Controllers;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class GoogleCalController {

 /*   private static final String APPLICATION_NAME = "IMMONEXUS Calendar";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @RequestMapping(value = "/events")
    public ResponseEntity<String> getEvents(@AuthenticationPrincipal OAuth2User oAuth2User,
                                            @RequestParam(value = "sdate") String sdate,
                                            @RequestParam(value = "edate") String edate,
                                            @RequestParam(value = "q") String q) {
        Events eventList;
        String message;
        try {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User)oAuth2User;
            String token = customOAuth2User.getToken();
            GoogleCredential credential = new GoogleCredential().setAccessToken(token);

            final DateTime date1 = new DateTime(sdate + "T00:00:00");
            final DateTime date2 = new DateTime(edate + "T23:59:59");

       //     HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
         //   Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, credential)
            //   .setApplicationName(APPLICATION_NAME)
              //  .setClientSecrets(clientId, clientSecret) // Utilisation des informations d'identification OAuth
  //                .build();
//

            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
            GoogleCredential crd= new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(jsonFactory)
                    .setClientSecrets(clientId, clientSecret)
                    .build();
            Calendar service = new Calendar.Builder(httpTransport, jsonFactory, crd)
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            Calendar.Events events = service.events();
            eventList = events.list("primary").setTimeZone("Tunisia").setTimeMin(date1).setTimeMax(date2).setQ(q).execute();
            message = eventList.getItems().toString();
            System.out.println("My:" + eventList.getItems());
        } catch (Exception e) {

            message = "Exception while handling OAuth2 callback (" + e.getMessage() + ")."
                    + " Redirecting to google connection status page.";
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }*/
}
