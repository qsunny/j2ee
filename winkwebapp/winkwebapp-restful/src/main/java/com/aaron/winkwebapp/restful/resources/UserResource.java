package com.aaron.winkwebapp.restful.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;
import org.apache.wink.common.annotations.Workspace;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.aaron.winkwebapp.api.bean.User;
import com.aaron.winkwebapp.api.constant.WinkConstant;
import com.aaron.winkwebapp.api.exception.UserException;
import com.aaron.winkwebapp.core.service.IUserService;

@Workspace(workspaceTitle="user resource",collectionTitle="user resource")
@Path("users")
public class UserResource {
	
	private static final Logger log = Logger.getLogger(UserResource.class);
	private static final String SUB_RUI = "{id}";
	
	@Autowired
	private IUserService userService;
	
	  @GET
	  @Path("hello")
	  @Produces("text/plain")
      public String get() {
		  
         return "Hello World";
      }
	
	@GET
	@Path(SUB_RUI)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(
            @Context UriInfo uriInfo,
			@PathParam("id") int id
			) {
		User u = null;
		try {
			u = userService.getUserById(id);
		} catch (UserException e) {
			e.printStackTrace();
			log.error("getUser error id="+id,e);
		}
		log.info("user===============>" + u);
		u = new User();
		u.setUsername("ssssssssss");
		u.setPassword("dsfdsfdsfsdf");
		return Response.ok(u).build();
	}
	
	@GET
	@Path("getUserList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserList(@Context UriInfo uriInfo) throws UserException {
		
		return Response.ok(userService.getUserList()).build();
	}
	
	@POST
	@Path("addUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(@Context UriInfo uriInfo,User u) throws UserException {
		int result = userService.addUser(u);
		JSONObject json = new JSONObject();
		try {
			if(result>0) {
				json.put("code", WinkConstant.SUCCESS_CODE);
				json.put("msg", "success");
			} else {
				json.put("msg", "failture");
				json.put("code", WinkConstant.FAILTURE_CODE);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.ok(json).build();
	}
	
	@PUT
	@Path("updateUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(@Context UriInfo uriInfo,User u) throws UserException {
		JSONObject json = new JSONObject();
		try {
			int result = userService.updateUser(u);
			if(result>0) {
				json.put("code", WinkConstant.SUCCESS_CODE);
				json.put("msg", "success");
			} else {
				json.put("msg", "failture");
				json.put("code", WinkConstant.FAILTURE_CODE);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.ok(json).build();
	}
	
	@DELETE
	@Path(SUB_RUI)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteUser(@Context UriInfo uriInfo,@PathParam("id") String id) throws UserException {
		User user = new User();
		user.setId(Integer.valueOf(id));
		int result = userService.removeUser(user);
		JSONObject json = new JSONObject();
		try {
			if(result>0) {
				json.put("code", WinkConstant.SUCCESS_CODE);
				json.put("msg", "success");
			} else {
				json.put("msg", "failture");
				json.put("code", WinkConstant.FAILTURE_CODE);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.ok(json).build();
	}
	
}
