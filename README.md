# Spring Boot Demo

## 🚀 Giới thiệu

Dự án này demo cách tích hợp các công nghệ:

- **Checkstyle**
- **Redis** + **RedisInsight**
- **Elasticsearch** + **Kibana**

---

## 1️⃣ Checkstyle

### a. Tải file cấu hình Checkstyle

Tải file `google_checks.xml` từ link:

- [Google Checkstyle](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)

Sau đó, đặt file vào thư mục `config/checkstyle/` trong project.

### b. Cấu hình plugin Maven

Thêm dependency vào `pom.xml`:

```xml
<dependency>
  <groupId>com.puppycrawl.tools</groupId>
  <artifactId>checkstyle</artifactId>
  <version>10.21.3</version>
</dependency>
```

Thêm plugin vào `pom.xml`:

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-checkstyle-plugin</artifactId>
  <version>3.6.0</version>
  <executions>
    <execution>
      <id>validate</id>
      <phase>validate</phase>
      <goals>
        <goal>check</goal>
      </goals>
    </execution>
  </executions>
  <configuration>
    <configLocation>config/checkstyle/google_checks.xml</configLocation>
    <encoding>UTF-8</encoding>
    <consoleOutput>true</consoleOutput>
    <failOnViolation>true</failOnViolation>
  </configuration>
</plugin>
```

### c. Cấu hình trong IntelliJ IDEA

1. Vào **File → Settings → Plugins**.
2. Tìm và cài đặt plugin **Checkstyle-IDEA**.
3. Sau khi cài đặt, vào **File → Settings → Tools → Checkstyle**.
4. Nhấn `+` để thêm file `google_checks.xml` từ thư mục `config/checkstyle/`.
5. Chọn **Whole Project** để Checkstyle áp dụng cho toàn bộ dự án.

---

## 2️⃣ Redis + RedisInsight

### a. Docker

#### i. Pull Image

Chạy lệnh sau để tải Redis và RedisInsight:

```sh
docker pull redis:7.42
docker pull redislabs/redisinsight:2.66
```

#### ii. Chạy Container

```sh
docker run --name my-redis -d -p 6379:6379 redis:7.42
docker run -d --name redis-insight -p 8001:8001 redislabs/redisinsight:2.66
```

### b. Cấu hình vào Spring Boot

#### i. Thêm Dependency

Thêm vào `pom.xml`:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

#### ii. Cấu hình Bean Redis

```java
@Bean
public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory();
}

@Bean
public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    return template;
```
## 3️⃣ Redis + RedisInsight
###  a. Docker
####    i. Pull image
```sh
docker pull elasticsearch:8.16.5
docker pull kibana:8.16.5
```
####    ii. Chạy container
```sh
docker network create elastic
docker run -d --name elasticsearch --net elastic -p 9200:9200 -e "discovery.type=single-node" -e "xpack.security.enabled=false" elasticsearch:8.16.5
docker run -d --name kibana --net elastic -p 5601:5601 -e "ELASTICSEARCH_HOSTS=http://elasticsearch:9200" kibana:8.16.5
```
###  b. Cấu hình vào Spring boot
####    i. Dependency
```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```
