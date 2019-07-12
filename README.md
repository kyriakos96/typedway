# Typedway
(Work In Progress) DB agnostic Version Control using Flyway and Slick

This attempt to organise DB Migration makes heavy use of the 
[slick-migration-api](https://github.com/nafg/slick-migration-api)


## Run
Start a mysql docker image for playing around
```bash
docker run --name typedway-mysql -e MYSQL_ROOT_PASSWORD=mypass -d mysql:5.7
```

Schema creation needs to be automated but for the time being run the following to create the schema manually
```bash
create schema test_schema;
```

Then to run the migration run the following:
 
```bash
sbt "run --environment local"
```

This is still a playground..

## Updates Coming soon
