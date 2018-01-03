package com.me.tking.controller
import com.me.tking.controller.BaseController
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, ResponseBody, RestController}

@RestController
@RequestMapping(path = Array("/api/v1/scala"))
class ScalaController {
  @GetMapping(path = Array("/hello"))
  def helloWord(): String = {
    "Hello scala!"
  }
}
