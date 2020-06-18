package com.god.beetl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * prePostEnabled :决定Spring Security的前注解是否可用 [@PreAuthorize,@PostAuthorize,..]
 * secureEnabled : 决定是否Spring Security的保障注解 [@Secured] 是否可用
 * jsr250Enabled ：决定 JSR-250 annotations 注解[@RolesAllowed..] 是否可用.
 */
@Configurable
@EnableWebSecurity
// 开启spring security方法级安全注解
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.csrf().disable(); // 注释就是使用csrf功能
        http.headers().frameOptions().disable(); // 解决form标签引入问题
        http.authorizeRequests()
                .antMatchers("/login/**", "/initUserData") // 不拦截登录相关方法
                .permitAll()
                .antMatchers("/user").hasRole("ADMIN") //user接口只有admin角色可以访问
                .anyRequest()
                .authenticated() // 任何尚未匹配的url只需要验证用户即可访问
                .anyRequest()
                .access("@rbacPermission.hasPermission(request, authentication)")
                .and()
                .formLogin()
                .loginPage("/")
                .loginPage("/login") // 登录请求页
                .loginProcessingUrl("/login") // 登录post请求路径
                .usernameParameter("username") // 登录用户名
                .passwordParameter("password") // 登录密码
                .defaultSuccessUrl("/main")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler) // 无权限处理器
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout"); // 退出登录成功URL
    }

    /**
     * 自定义获取用户信息的接口
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 静态资源配置
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不拦截静态方法，所有用户都可以访问的资源路径配置
        web.ignoring().antMatchers(
                "/",
                "/css/**",
                "/js/**",
                "/images/**",
                "/layui/**"
        );
    }

    /**
     * 密码加密算法
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
