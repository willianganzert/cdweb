package br.com.cdweb.server.filters;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.com.cdweb.server.startup.ControlAccessBean;
import br.com.cdweb.server.util.TokenUtil;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter{
	
	@EJB
	private ControlAccessBean bean;
	
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	
        // Get the HTTP Authorization header from the request
        String authorizationHeader = 
            requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        Response.Status status = new br.com.cdweb.server.util.TokenUtil().validateTokenAccess(authorizationHeader);
        if(status.equals(Response.Status.OK)){
        	
        }
        else{
        	requestContext.abortWith(
                    Response.status(status).build());
        }
    }	
}
