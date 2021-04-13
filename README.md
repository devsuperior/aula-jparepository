# JpaRepository: interface boladona do Spring

#### DevSuperior - Nelio Alves

[![Image](https://img.youtube.com/vi/os6hdZbCnpM/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/os6hdZbCnpM)

Siga-nos:

https://instagram.com/devsuperior.ig

https://youtube.com/devsuperior

## Trechos de código

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

```sql
INSERT INTO tb_users(name,email,salary) VALUES ('Maria','maria@gmail.com',1348.74);
INSERT INTO tb_users(name,email,salary) VALUES ('Joao Silva','joao@gmail.com',9276.62);
INSERT INTO tb_users(name,email,salary) VALUES ('Carlos Silva','carlos@gmail.com',7318.75);
INSERT INTO tb_users(name,email,salary) VALUES ('Adriana','adriana@gmail.com',10688.93);
INSERT INTO tb_users(name,email,salary) VALUES ('Karina','karina@gmail.com',7251.28);
INSERT INTO tb_users(name,email,salary) VALUES ('Carlos Marques','carlos@gmail.com',5485.79);
INSERT INTO tb_users(name,email,salary) VALUES ('Carlos Pereira','carlos@gmail.com',3519.41);
INSERT INTO tb_users(name,email,salary) VALUES ('Ana Maria','ana@gmail.com',2569.20);
INSERT INTO tb_users(name,email,salary) VALUES ('Beatriz','beatriz@gmail.com',3853.33);
INSERT INTO tb_users(name,email,salary) VALUES ('Joana','joana@gmail.com',4222.91);
INSERT INTO tb_users(name,email,salary) VALUES ('Tulio Augusto','tulio@gmail.com',9453.69);
INSERT INTO tb_users(name,email,salary) VALUES ('Augusto','augusto@gmail.com',4119.91);
INSERT INTO tb_users(name,email,salary) VALUES ('Marta','marta@gmail.com',4519.15);
INSERT INTO tb_users(name,email,salary) VALUES ('Silvio','silvio@gmail.com',5285.68);
INSERT INTO tb_users(name,email,salary) VALUES ('Washington','washington@gmail.com',6439.99);
INSERT INTO tb_users(name,email,salary) VALUES ('Teresa','teresa@gmail.com',8929.78);
INSERT INTO tb_users(name,email,salary) VALUES ('Luciano','luciano@gmail.com',3360.72);
INSERT INTO tb_users(name,email,salary) VALUES ('Fabiana','fabiana@gmail.com',2532.83);
INSERT INTO tb_users(name,email,salary) VALUES ('Fabio','fabio@gmail.com',5912.88);
INSERT INTO tb_users(name,email,salary) VALUES ('Gisele','gisele@gmail.com',7558.26);
INSERT INTO tb_users(name,email,salary) VALUES ('Larissa','larissa@gmail.com',2362.04);
INSERT INTO tb_users(name,email,salary) VALUES ('Natanael','natanael@gmail.com',6860.63);
INSERT INTO tb_users(name,email,salary) VALUES ('Meire','meire@gmail.com',3553.40);
INSERT INTO tb_users(name,email,salary) VALUES ('Ana Carolina','ana@gmail.com',1404.28);
INSERT INTO tb_users(name,email,salary) VALUES ('Filipe','filipe@gmail.com',3388.73);
```

```java
@Autowired
private UserRepository repository;

@GetMapping
public ResponseEntity<List<User>> findAll() {
    List<User> result = repository.findAll();
    return ResponseEntity.ok(result);
}

@GetMapping(value = "/page")
public ResponseEntity<Page<User>> findAll(Pageable pageable) {
    Page<User> result = repository.findAll(pageable);
    return ResponseEntity.ok(result);
}

@GetMapping(value = "/search-salary")
public ResponseEntity<Page<User>> searchBySalary(@RequestParam(defaultValue = "0") Double minSalary, @RequestParam(defaultValue = "1000000000000") Double maxSalary, Pageable pageable) {
    Page<User> result = repository.findBySalaryBetween(minSalary, maxSalary, pageable);
    return ResponseEntity.ok(result);
}

@GetMapping(value = "/search-name")
public ResponseEntity<Page<User>> searchByName(@RequestParam(defaultValue = "") String name, Pageable pageable) {
    Page<User> result = repository.findByNameContainingIgnoreCase(name, pageable);
    return ResponseEntity.ok(result);
}
```

```java
@Query("SELECT obj FROM User obj WHERE obj.salary >= :minSalary AND obj.salary <= :maxSalary")
Page<User> searchBySalary(Double minSalary, Double maxSalary, Pageable pageable);

@Query("SELECT obj FROM User obj WHERE LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%'))")
Page<User> searchByName(String name, Pageable pageable);

Page<User> findBySalaryBetween(Double minSalary, Double maxSalary, Pageable pageable);

Page<User> findByNameContainingIgnoreCase(String name, Pageable pageable);
```
