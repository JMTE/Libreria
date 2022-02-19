package com.ite.jmte.security;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class DatSecurity extends WebSecurityConfigurerAdapter {
	
	
	@Autowired 
	
    private DataSource dataSource; 
	
	
@Override 
protected void configure(AuthenticationManagerBuilder auth) throws 
Exception { 
auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery ("select username,password,'true' as enabled from Usuarios where username=?") 
.authoritiesByUsernameQuery("select u.username, p.descripcion from Usuario_Perfiles up" + 
" inner join Usuarios u on u.username = up.username " + " inner join Perfiles p on p.id_perfil = up.id_Perfil where u.username=?"); 
} 


/*De donde sacas el usuario 
 * select username, password, de usuarios where username 
 * es igual al interrogante
 * 
 * autorizaciones:
 * de usuarios el usernamen, de perfiles el nombre, from usuario perfiles
 * inner join la de usuarios*/


/*
 * Esta clase puede estar dentro del paquete principal, pero también 
podemos poner un paquete independiente para estas clases de 
configuración.  
• Está anotada con @Configuration y @EnableWebSecurity 
• El nombre de la clase es inventado 
• Hereda (extends) de la Clase WebSecurityConfigurerAdapter para la 
configuración de de seguridad en la web. 
 
 
• La clase necesita un DataSource, clase necesaria  para implementar 
una fuente de Datos externa(generalmente una base de datos. 
• Necesita un método redefinido denominado configure, y cuya 
estructura de método es como la que tienes en el código. 
• A través de la variable auth y el siguiente código 
auth.jdbcAuthentication().dataSource(dataSource);  le indicamos que 
busque, de forma automática,  las tablas USERS Y AUTHORITIES, en la 
base de datos que le hemos indicado en el fichero 
applications.properties.

DataSource hay que añadir dos métodos: 
• usersByUsernameQuery, que es una consulta SQL de las 
columnas que hay en la tabla, donde le informo de cual hace de 
username, password y enable. 
• authoritiesByUsernameQuery, que es una consulta de SQL en 
donde le digo qué voy a usar como Username y nombre del perfil 
de la tabla de perfiles.
 * 
 * */

@Override
protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.authorizeRequests()
		//Los recursos estáticos no requieren autenticaciÃ³n
		.antMatchers(
		"/bootstrap/**",  "/images/**", "/css/**", "js/**").permitAll()
		
		// Las vistas públicas no requieren autenticaciÃ³n
		.antMatchers("/",
		"/login",
		"/search").permitAll()
		
		// Asignar permisos a URLs por ROLES
		.antMatchers("/app/producto/**").hasAnyAuthority("ROL_CLIENTE","ROL_ADMON")
		.antMatchers("/app/usuarios/**").hasAnyAuthority("ROL_CLIENTE","ROL_ADMON")
		.antMatchers("/admon/perfiles/**").hasAnyAuthority("ROL_ADMON")
		.antMatchers("/admon/tipos/**").hasAnyAuthority("ROL_ADMON")
		.antMatchers("/cliente/verDetalle/**").hasAnyAuthority("ROL_CLIENTE")
		
		// Todas las demÃ¡s URLs de la AplicaciÃ³n requieren autenticaciÃ³n
		.anyRequest().authenticated()
		
		// El formulario de Login no requiere autenticacion
		.and().formLogin().permitAll();
		
		

}

}

