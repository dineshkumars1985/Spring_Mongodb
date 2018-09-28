package com.rest.app.controllers;

import com.rest.app.business.IexBo;
import com.rest.app.domain.IexFetchModel;
import com.rest.app.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author dinesh kumar
 */
@Controller
@Path("/iex")
public class IexController {

  public static final String BOOK_NOT_FOUND_MSG = "Book with ISBN[%s] does not exist";
  public static final String BOOK_EXISTS_MSG = "Book with ISBN[%s] already exists";

  @Qualifier("iexBo")
  private IexBo iexBo;

  @Autowired
  public IexController(IexBo iexBo) {
    this.iexBo = iexBo;
    iexBo.scheduleIexJob();
  }

  /**
   *
   * @param iexFetch
   * @return
   * @throws NotFoundException
   */
  @POST
  @Consumes({MediaType.APPLICATION_JSON})
  @Produces({MediaType.APPLICATION_JSON})
  public Response getIexDetails(IexFetchModel iexFetch) throws NotFoundException {
    return Response.ok(AppUtils.objectToJson(iexBo.fetch(iexFetch))).build();
  }

}
