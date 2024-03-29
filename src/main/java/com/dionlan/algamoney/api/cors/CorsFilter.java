package com.dionlan.algamoney.api.cors;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.dionlan.algamoney.api.config.property.AlgamoneyApiProperty;

/**
 * Política de segurança em que que JavaScript presente nos browsers só permite fazer requisições para a mesma origem / domínio / host
 * @author dius_
 * CORS - Cross-Origin HTTP Request, por padrão a política CORS é bloqueado. Por isso é necessário adicionar a exceção a origem desejada.
 * 1 - O browser envia uma requisição OPTIONS (preflighted) ao servidor para saber saber quais requisições estão permitidas. 
 * Se estiver habilitado, é realizado outra requição com o verbo solicitado anteriormente (GET, POST, PUT, DELETE, PATCH, etc)
 * No Spring @CrossOrigin(maxAge = 10, origins={ "http://localhost:8080/" }) anotado na classe controladora ou no método específico
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	
	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;	

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		response.setHeader("Access-Control-Allow-Origin", algamoneyApiProperty.getAllowedOrigin());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        		
		if ("OPTIONS".equals(request.getMethod()) && algamoneyApiProperty.getAllowedOrigin().equals(request.getHeader("Origin"))) {
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS");
        	response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
        	response.setHeader("Access-Control-Max-Age", "3600");
			
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(req, resp);
		}
		
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}