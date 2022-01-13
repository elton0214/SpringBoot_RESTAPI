package idv.Healthcare.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import idv.Healthcare.repository.ApplicationUserRepository;
import idv.Healthcare.service.ApplicationUserService;
import idv.Healthcare.service.IApplicationUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * 授權中介
 * 解析JWT Token, 將token轉換回使用者，以利後續controller可直接抓取使用者資訊
 * 除了Resign/Signin外，其他api都要通過
 */

@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

//    @Autowired
//    private JwtUtil jwtUtil;
//
//    private final ApplicationUserRepository applicationUserRepository;
//
//    public ApplicationUserService(ApplicationUserRepository applicationUserRepository) {
//        this.applicationUserRepository=applicationUserRepository;
//    }

//    @Autowired
//    private ApplicationUserService appuserService;

    public JwtAuthorizationFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //if is "/api/login" or "/api/register" then no need to authorize
        if(request.getServletPath().equals("/login") || request.getServletPath().equals("/register")) {
            filterChain.doFilter(request,response);
        } else {
            String authorizationHeader = request.getHeader("Authorization");
            if (null != authorizationHeader && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String token = authorizationHeader.substring("Bearer ".length());

                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(token);
                    String username = decodedJWT.getSubject();
                    log.info("decodedJWT.getSubject(): {}", username);
                    String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                    stream(roles).forEach(role -> {
                        authorities.add(new SimpleGrantedAuthority(role));
                    });


                    //test
//                    UserDetails userDetails = appuserService.loadUserByUsername(username);
//                    log.info("appUsrService.loadUserByUsername(username): {}", userDetails);

                    UsernamePasswordAuthenticationToken authenticationToken =
//                            new UsernamePasswordAuthenticationToken(username, null);
                        new UsernamePasswordAuthenticationToken(username, null,authorities);

                    log.info("authenticationToken:"+ authenticationToken);

                    if (authenticationToken.isAuthenticated()) {
                        log.info("authenticationToken.isAuthenticated() true");
                    } else {
                        log.info("authenticationToken.isAuthenticated() failed");
                    }

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    log.info("response1:"+ response);

                    filterChain.doFilter(request, response);
                    log.info("response2:"+ response);
                } catch (Exception exception) {
                    log.info("Error logging in: {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
//                    response.sendError(FORBIDDEN.value());
                    Map<String,String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                filterChain.doFilter(request, response);


//            userName = jwtUtil.getUsernameFromToken(token);
            }
        }

//        if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails
//                    = appuserService.loadUserByUsername(userName);
//
//            if(jwtUtil.validateToken(token,userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                        = new UsernamePasswordAuthenticationToken(userDetails,
//                        null, userDetails.getAuthorities());
//
//                usernamePasswordAuthenticationToken.setDetails(
//                        new WebAuthenticationDetailsSource().buildDetails(httpServletRequest)
//                );
//
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
