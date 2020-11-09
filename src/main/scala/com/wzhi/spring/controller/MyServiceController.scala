package com.wzhi.spring.controller

import com.wzhi.spring.service.MyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, ResponseBody}

@Controller
class MyServiceController @Autowired()(myService: MyService) {

  @RequestMapping(path = Array("/test"), method = Array(RequestMethod.GET), produces = Array(MediaType.TEXT_PLAIN_VALUE))
  @ResponseBody
  def handleRequest(): String = {
    "Hello from a Scala controller! " + myService.getMessage
  }

}