@echo off
REM Docker镜像构建脚本（Windows）

echo 开始构建Docker镜像...
docker build -t id-generator:latest .

if %errorlevel% equ 0 (
    echo 镜像构建成功！
    echo 运行命令: docker run -d -p 9099:9099 --name id-generator id-generator:latest
) else (
    echo 镜像构建失败！
    exit /b 1
)

