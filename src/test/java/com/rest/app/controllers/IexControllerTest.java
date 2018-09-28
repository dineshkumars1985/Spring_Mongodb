package com.rest.app.controllers;

import com.rest.app.business.IexBo;
import com.rest.app.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.*;

import static javax.ws.rs.core.Response.Status.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * IexController JUnit
 *
 * @author Dinesh Kumar
 */
@RunWith(MockitoJUnitRunner.class)
public class IexControllerTest {

  @Mock
  private IexBo iexbo;
  @InjectMocks
  private IexController iexController;

  private IexModel iexModel;
  private IexFetchModel iexFetchModel;
  private IexResultModel resModel;

  @Before
  public void setUp() {
    iexModel = new IexModel(new IexPk("CYH", new Timestamp(System.currentTimeMillis())),
    "Community Health Systems", 3.5, "logo");
    iexFetchModel = new IexFetchModel();
    iexFetchModel.setSymbol(Collections.singletonList("CYN"));
    resModel = new IexResultModel("apple", "logo");
    resModel.addSingleObject(new IexTimeValue("2018-10-10",6.3));
  }

  @After
  public void tearDown() {
    iexFetchModel = null;
    iexModel = null;
    resModel=null;
  }

  @Test
  public void getIexDetails_shouldReturnResponseWithOkStatus() { // 200
    when(iexbo.fetch(iexFetchModel)).thenReturn(Collections.singletonList(resModel));
    Response response = iexController.getIexDetails(iexFetchModel);
    assertEquals(OK.getStatusCode(), response.getStatus());
  }

}
