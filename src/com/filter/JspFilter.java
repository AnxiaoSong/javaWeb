package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspFilter implements Filter{

	public JspFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;  
        String url = httpServletRequest.getRequestURI();  
        if(url != null && url.endsWith(".jsp")) { 
            System.out.println(httpServletRequest.getContextPath());
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath());  
            return;  
        }  
        chain.doFilter(request, response);  
    }  
  

}
