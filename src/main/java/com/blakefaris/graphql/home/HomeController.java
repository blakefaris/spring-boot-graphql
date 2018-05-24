package com.blakefaris.graphql.home;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    private final HomeResourceAssembler homeResourceAssembler;

    public HomeController(HomeResourceAssembler homeRootResourceAssembler) {
        this.homeResourceAssembler = homeRootResourceAssembler;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public Resource<String> getRoot() {
        return homeResourceAssembler.toResource("Root endpoint, links for supported endpoints in _links.");
    }
}
