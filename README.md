# spring-boot-graphql

Thanks https://github.com/graphql-java/graphql-java-tools for the GraphQL demo.

* Group: com.blakefaris.graphql
* Java Version: 1.8
 
## Run the Service

To run locally (default profile is `local`) open a command prompt in the project directory and run:
```bash
./gradlew bootRun
```

Then go to http://localhost:8080/graphiql to view GraphiQl.

### Run With Profile

#### NonProd
```bash
./gradlew bootRun -Dspring.profiles.active=nonprod
```

 
## Rest Docs
This project uses [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/html5/).
 
### Generating Documentation
 
This project uses test driven documentation generation using (Spring REST Docs)[http://docs.spring.io/spring-restdocs/docs/current/reference/html5/#introduction]

Running test ```ApiDocumentationTest``` will generate ```build/docs/generated-snippets```
```src/docs/index.adoc``` is the entry point for the generated documents.  This file includes the generated docs.

### Viewing Generated Documentation
You will need to run using the jar.
```
$ ./gradlew clean build
$ java -jar ./build/libs/graphqul-0.1.0-dev.1.uncommitted+.16b23b1.jar
```
NOTE: the actual name of the jar will differ.  After running using the jar go to the following URL:
```
http://localhost:8080/docs/index.html
```

### Overriding Snippets
 * Default snippets can be found here: https://github.com/spring-projects/spring-restdocs/tree/v2.0.0.RELEASE/spring-restdocs-core/src/main/resources/org/springframework/restdocs/templates/asciidoctor
 * And overridden in this project by adding to `test/resources/org/springframework/restdocs/tempaltes/asciidoctor`



*Used [SPRING INITIALIZR](https://start.spring.io/) to generate project.*

*Generated on 2018-04-17T18:47:16.273*
