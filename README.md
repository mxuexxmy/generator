# ID生成工具

一个支持多种ID生成模式的Web应用。

## 功能特性

- **模式1：递增ID生成** - 基于起始ID累计增加生成
- **模式2：随机ID生成** - 支持指定长度、个数和字符类型
- **模式3：常见ID生成** - UUID、雪花算法等

## 技术栈

- Spring Boot 2.7.5
- Java 8
- Thymeleaf
- Bootstrap

## 本地运行

```bash
# 使用Maven运行
mvn spring-boot:run

# 或打包后运行
mvn clean package
java -jar target/generator-0.0.1-SNAPSHOT.jar
```

访问：http://localhost:9099

## Docker运行

### 方式1：使用Docker命令

```bash
# 构建镜像
docker build -t id-generator:latest .

# 运行容器
docker run -d -p 9099:9099 --name id-generator id-generator:latest
```

### 方式2：使用Docker Compose

```bash
# 构建并启动
docker-compose up -d

# 查看日志
docker-compose logs -f

# 停止
docker-compose down
```

访问：http://localhost:9099

## API接口

### 递增ID生成
```
POST /generate/incremental
参数：startId (long), count (int)
```

### 随机ID生成
```
POST /generate/random
参数：length (int), count (int), type (string: numeric/alphabetic/alphanumeric)
```

### 常见ID生成
```
POST /generate/common
参数：idType (string: UUID/UUID_WITHOUT_DASH/SNOWFLAKE/SNOWFLAKE_STRING), count (int)
```

## 注意事项

- 所有大数字ID以字符串形式返回，避免JavaScript精度丢失
- 默认端口：9099
- 可通过环境变量 `JAVA_OPTS` 调整JVM参数
