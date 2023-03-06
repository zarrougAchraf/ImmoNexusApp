package tn.devteam.immonexus.Configurations;

//import tn.devteam.immonexus.Services.CustomOAuth2UserService;
/*
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint().userService(oauthUserService);
    }
}*/