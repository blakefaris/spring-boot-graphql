package com.blakefaris.graphql.docs;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"stub"})
public class ApiDocumentationTest {

    protected MockMvc mockMvc;
    private static final LinksSnippet selfLinkSnippet = links(linkWithRel("self").description("Link to the current resource."));

    @Autowired
    private WebApplicationContext context;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/docs/generated-snippets");

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void getHome() throws Exception {

        List<FieldDescriptor> responseFieldDescriptors = new ArrayList<>();
        responseFieldDescriptors.add(fieldWithPath("content").type(JsonFieldType.STRING).description("Description of response."));
        responseFieldDescriptors.add(subsectionWithPath("_links").type(JsonFieldType.OBJECT).description("<<home-links, Links>> associated with the resource. Uses https://en.wikipedia.org/wiki/Hypertext_Application_Language[HAL] format."));

        LinksSnippet linksSnippet = selfLinkSnippet.and(
                linkWithRel("graphQlUi").description("Link to a graphical interactive in-browser GraphQL IDE"),
                linkWithRel("apiDocs").description("Link to the API Documents for this service."),
                linkWithRel("info").description("Link to the info for this service, such as which version is deployed.")
        );

        ResultActions resultActions = this.mockMvc.perform(get("/")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        resultActions.andDo(MockMvcResultHandlers.print());
        resultActions.andDo(
                MockMvcRestDocumentation.document("home",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        linksSnippet,
                        responseFields(responseFieldDescriptors)
                ));

    }

}
