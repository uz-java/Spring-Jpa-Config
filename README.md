
### datasource config
```
spring.datasource.url=jdbc:postgresql://localhost:5432/ProjectName
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver=org.postgresql.Driver

```

### hibernate config
```
hibernate.dialect=org.hibernate.dialect.PostgreSQL82Dialect
hibernate.show_sql = true
hibernate.format_sql = true
hibernate.hbm2ddl.auto = update
hibernate.cache.use_second_level_cache=false
hibernate.cache.use_query_cache=false
        
```
