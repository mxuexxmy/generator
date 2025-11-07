# 多阶段构建：构建阶段
FROM maven:3.8.4-openjdk-8-slim AS build
WORKDIR /app

# 复制pom.xml和源代码
COPY pom.xml .
COPY src ./src

# 构建应用（跳过测试以加快构建速度）
RUN mvn clean package -DskipTests

# 运行阶段 - 使用 Eclipse Temurin (Adoptium) OpenJDK
FROM openjdk:8-alpine
WORKDIR /app

# 从构建阶段复制jar文件
COPY --from=build /app/target/*.jar app.jar

# 暴露端口
EXPOSE 9099

# 设置JVM参数（可选，可根据需要调整）
ENV JAVA_OPTS="-Xms256m -Xmx512m"

# 启动应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

