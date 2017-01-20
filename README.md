# SpringBoot-Mybatis-Docker
Sample Project about SpringBoot &amp; Mybatis &amp; Docker

http://spring.io/guides/gs/spring-boot-docker/

http://start.spring.io/

在Eclipse中利用Maven的Spring boot+Docker模板搭建项目框架，实现步骤：
1.下载Eclipse的Maven插件。
2.在Eclipse中选择SpringBoot-Docker模板。
技术分享
 
3.创建SpringBoot-Docker项目
 技术分享
　　基于该模板创建SpringBoot项目后，目录结构如上图，编译后会生成Dockerfile文件及<项目名>.jar文件。
（如何maven导出jar：右击项目，选择Run as --》Maven build --> goals填写package，运行即可；）

创建好项目自动生成的Dockerfile:
/docker/Dockerfile:

FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD docker-spring-boot-1.0.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
解释下这个配置文件：
VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp。改步骤是可选的，如果涉及到文件系统的应用就很有必要了。/tmp目录用来持久化到 Docker 数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
项目的 jar 文件作为 “app.jar” 添加到容器的
ENTRYPOINT 执行项目 app.jar。为了缩短 Tomcat 启动时间，添加一个系统属性指向 “/dev/urandom” 作为 Entropy Source

4.在Docker环境下生成Docker Image镜像文件
　　由于已经有了Dockerfile文件及本项目的.jar文件，可以将其拷贝到虚拟机Docker环境中，并运行例如：docker build -t <example-service> . 指令，将Dockerfile描述编译成镜像文件，并进入本地Docker registries仓库中。
 
5.运行Docker Image文件，形成Container实例，与宿主机建立端口映射。
　　运行例如：docker run -p 48080:8080 -d <example-service> 指令，启动该项目的Container实例
