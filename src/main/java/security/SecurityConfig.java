package security;


import javax.sql.DataSource;

//@EnableWebSecurity
//@Configuration
public class SecurityConfig {

    private DataSource dataSource;
    private LoginSuccessHandler loginSuccessHandler;
    private LoginFailHandler loginFailHandler;


}
