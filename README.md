这是一个衍生自JPetStore的宠物商店项目：MyPetStore，保证了在原有功能基础上对前后端都做了一些修改。

**运行此项目你需要:**
- Tomcat 9+
- MySQL 8+。此项目使用的是8.1版本，因此驱动也是8.1.0。如果你使用其他版本，请在pom.xml中排除原有依赖，并书写对应版本，或者手动导入你准备好的jar包。
- Redis。本项目使用lettuce作为redis客户端，依赖已经导入。如果你需要jedis或其他客户端，请修改依赖和对应的代码。
- 支持SMTP的邮箱账号。这是为了邮箱验证码模块，如果你不需要该功能，请忽略。

**确保这些环境具备后，请执行以下步骤：**
1. 打开`resouce`文件夹。其中放置了一些`.properties`文件，你可以在这里填入你自己的配置信息。
2. 运行项目以使配置生效。
3. 执行`resouce/mypetstore-dump.sql`建立并初始化该项目需要的数据库表

**重要提示：**
确保除了Maven中的依赖被填入artifact的`WEB-INF/lib`之外，本项目的`lib`文件夹中的jar文件也需要添加。
`src/org/csu/mypetstore/constant/AppProperties.java`记录了此应用的名字、地址、端口和路径。运行时，请务必将此WebApp在Tomcat中的配置与其保持一致。如果你想要修改配置，请将`AppProperties.java`一同修改。