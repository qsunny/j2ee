package com.aaron.winkwebapp.restful.context;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.aaron.winkwebapp.api.bean.User;

/**
 * Context provider for providing the JAXBContext for the User JAXB object
 */
@Provider
public class UserContextResolver implements ContextResolver<JAXBContext>{
	
	 private static JAXBContext context;
	    static {
	        try {
	            context = JAXBContext.newInstance(User.class);
	        } catch (JAXBException e) {
	            throw new RuntimeException(e);
	        }
	    }
	
	@Override
	public JAXBContext getContext(Class<?> rawType) {
		 if (User.class.equals(rawType)) {
	            return context;
	        }
		return null;
	}

}
