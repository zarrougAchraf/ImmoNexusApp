package tn.devteam.immonexus.Services;

/*
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);

        // Retrieve the access token
        String accessToken = userRequest.getAccessToken().getTokenValue();

        // Send a request to the Google userinfo endpoint to retrieve user details
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> response = restTemplate.exchange("https://www.googleapis.com/oauth2/v3/userinfo", HttpMethod.GET, entity, String.class);

        // Parse the response to get user details
        String email = JsonPath.read(response.getBody(), "$.email");
        String name = JsonPath.read(response.getBody(), "$.name");
        String pictureUrl = JsonPath.read(response.getBody(), "$.picture");

        // Create an OAuth2User object with the retrieved user details
        User userInfo = new User(email, name, pictureUrl);
        return (OAuth2User) userInfo;
    }
}*/

