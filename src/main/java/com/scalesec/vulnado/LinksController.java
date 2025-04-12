package com.scalesec.vulnado;

import java.io.IOException;

@RestController
public class LinksController {
  @RequestMapping(value = "/links", produces = "application/json", method = RequestMethod.GET)
  List<String> links(@RequestParam String url) throws IOException{
    return LinkLister.getLinks(url);
  }
  @RequestMapping(value = "/links-v2", produces = "application/json", method = RequestMethod.GET)
  @RequestMapping(value = "/links-v2", produces = "application/json", method = RequestMethod.GET)
    return LinkLister.getLinksV2(url);
  }
}
}