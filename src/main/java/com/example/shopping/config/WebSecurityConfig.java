package com.example.shopping.config;

import com.example.shopping.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //无需权限均可以访问
                .antMatchers("/css/**", "/js/**", "/jquery/**", "/templates/**","/users/register","/shopping/main","/users/dologin", "/users/doregister").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin() //基于Form表单验证登录
                        .loginPage("/users/login")
                        .defaultSuccessUrl("/shopping/main").permitAll()
                        .permitAll()
                        .and().csrf().disable()
                        .logout()
                        .permitAll();
    }


//    public final static String SESSION_KEY = "currentUser";
//
//    @Bean
//    public SecurityInterceptor getSecurityInterceptor() {
//        return new SecurityInterceptor();
//    }
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());
//
//        // 排除配置
//        addInterceptor.excludePathPatterns("/**");

        // 拦截配置
//        addInterceptor.addPathPatterns("/**");
//    }
//
//    private class SecurityInterceptor extends HandlerInterceptorAdapter {
//
//        @Override
//        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//                throws Exception {
//            System.out.println("拦截到");
//            Cookie[] cookies = request.getCookies();
//            String path = request.getServletPath();
//            if (path.contains("users") || path.contains("login")) {
//
//                return true;
//            }
//
//            for (Cookie cookie : cookies) {
//                System.out.println(cookie.getName() + "拦截器中的cookie");
//                if (cookie.getName().equals(SESSION_KEY)) {
//
//                    return true;
//                }
//            }
//
//            response.sendRedirect("/login");
//
//            return false;
//
//
//        }
// }

//
//        @Bean
//        public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
//            return hemf.getSessionFactory();
//        }
    }

