# My template for Spring Boot Web Apps

My aim is to create template which can be reusable for Spring Boot web projects. 
It will contain configuration for all libraries that are most commonly used.

- [Liquibase](https://www.liquibase.org/)
- [Lombok](https://projectlombok.org/)
- [H2](https://www.h2database.com/)
- [Spock](https://spockframework.org/)
- more will be added when required...

Of course there are Spring Boot dependencies:

- Web
- JPA
- more will be added when required...

## Usage

To use this template you have to:

- copy project
- change origin - `git remote set-url origin {{ YOUR-URL }}`
- change `rootProject.name` in [settings.gradle](/settings.gradle)
- change name of starter class [TemplateForSpringBootApplication.java](/src/main/java/pl/cezarysanecki/templateforspringboot/TemplateForSpringBootApplication.java)
- change name of main package
  - in [main](/src/main/java/pl/cezarysanecki/templateforspringboot)
  - in [test](/src/test/groovy/pl/cezarysanecki/templateforspringboot)
- squash all commits to one
  - reset all commits - `git reset --soft $(git rev-list --max-parents=0 HEAD)`
  - commit all changes as one initial commit - `git commit --amend -m "init commit"`
- push to repo - `git push --force`

## Liquibase

Liquibase will be configured in XML files. The main one will be `db.changelog-master.xml`. To do this
we have to add property in [`application.yaml`](/src/main/resources/application.yaml).

``` yaml
spring:
    liquibase:
        change-log: classpath:db/changelog/db.changelog-master.xml
```

## Lombok

Just added.

## H2

For now, H2 is used as main database. It is configured to be used in memory - of course in
[`application.yaml`](/src/main/resources/application.yaml).

``` yaml
spring:
  datasource:
    url: jdbc:h2:~/template;DB_CLOSE_ON_EXIT=FALSE
    username: sa
    password:
```

## Spock

Testing with Spock is quite convenient. In this template I use Groovy 3 with compatible Spock 
[for this version](/build.gradle). Created simple test for startup with abstract class 
[`AbstractSpec`](/src/test/groovy/pl/cezarysanecki/templateforspringboot/AbstractSpec.groovy). All is set to use.