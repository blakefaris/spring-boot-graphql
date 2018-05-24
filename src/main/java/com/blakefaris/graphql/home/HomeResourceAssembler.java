package com.blakefaris.graphql.home;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class HomeResourceAssembler extends ResourceAssemblerSupport<String, Resource> {

    public HomeResourceAssembler() {
        super(HomeController.class, Resource.class);
    }

    @Override
    public Resource<String> toResource(String message) {

        Resource<String> resource = new Resource<>(message);

        resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(HomeController.class).getRoot()).withSelfRel());
        resource.add(ControllerLinkBuilder.linkTo(HomeController.class).slash("docs").slash("index.html").withRel("apiDocs"));
        resource.add(ControllerLinkBuilder.linkTo(HomeController.class).slash("graphiql").withRel("graphQlUi"));
        resource.add(ControllerLinkBuilder.linkTo(HomeController.class).slash("actuator").slash("info").withRel("info"));

        return resource;
    }
}
