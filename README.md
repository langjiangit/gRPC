## 说明文档

### 使用的工具

> 编译器环境

- `JDK 1.8`
- `Maven 3.6.3`

#### 父项目grpc的pom文件说明

> maven依赖

```xml
<dependencies>
    <!--SpringBoot依赖-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
    <!--引入grpc所有依赖-->
    <dependency>
        <groupId>com.google.protobuf</groupId>
        <artifactId>protobuf-java</artifactId>
        <version>3.5.1</version>
    </dependency>
    <dependency>
        <groupId>io.grpc</groupId>
        <artifactId>grpc-all</artifactId>
        <version>1.12.0</version>
    </dependency>
</dependencies>
```

> 配置插件（必须添加，这样maven才能生成java代码）

```xml
<!--配置插件-->
    <build>
        <extensions>
            <extension>
                <groupId>kr.motd.maven</groupId>
                <artifactId>os-maven-plugin</artifactId>
                <version>1.4.1.Final</version>
            </extension>
        </extensions>
        <plugins>
            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.5.0</version>
                <configuration>
                    <protocArtifact>com.google.protobuf:protoc:3.0.0:exe:${os.detected.classifier}</protocArtifact>
                    <pluginId>grpc-java</pluginId>
                    <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.0.0:exe:${os.detected.classifier}</pluginArtifact>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>compile-custom</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

#### 服务端模块grpc-server和客户端模块grpc-client的pom文件说明

> 两个模块所用到的依赖是相同的，如下

```xml
<dependencies>
    <!--hutool工具箱，这个在项目中大量用到-->
    <dependency>
        <groupId>cn.hutool</groupId>
        <artifactId>hutool-all</artifactId>
        <version>5.3.10</version>
    </dependency>
    <!--lombok-->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>fastjson</artifactId>
        <version>1.2.58</version>
    </dependency>
</dependencies>
```

#### protobuf插件

gRPC使用的是protobuf协议，为了让IDEA支持该协议，需要添加protobuf插件，插件有很多，google官方也提供相应的工具，这里使用其中一款工具如下，在IDEA插件库下载即可

![image-20200814100843639](https://cdn.jsdelivr.net/gh/uestc-toy/blog_file/img/image-20200814100843639.png)

### 项目构成

> 如图所示，我将该项目设计为两个模块，一个gRPC客户端,，一个gRPC服务端
>
> ，他们可分别单独运行。

![image-20200814100947116](https://cdn.jsdelivr.net/gh/uestc-toy/blog_file/img/image-20200814100947116.png)

### 功能设计

> 我这里设计实现gRPC的其中两种服务类型。

1. 简单RPC。即一般的rpc调用，一个请求对象对应一个返回对象。
2. 服务端流式RPC。一个请求对象，服务端可以传回多个结果对象，即长连接。

在proto文件夹中的`service.proto`文件可以看到我对这两种RPC方式的声明

```protobuf
//定义rpc服务接口：共两个服务
service IndexService {
  //简单 RPC ， 客户端使用存根发送请求到服务器并等待响应返回，就像平常的函数调用一样。
  rpc simpleRequest(com.tosang.grpc.entity.HelloRequest) returns (com.tosang.grpc.entity.IndexResponse);
  //服务器端流式 RPC ， 客户端发送请求到服务器，拿到一个流去读取返回的消息序列。 客户端读取返回的流，直到里面没有任何消息。注意增加了stream关键字
  rpc streamRequest(com.tosang.grpc.entity.HelloRequest) returns (stream com.tosang.grpc.entity.IndexResponse);
}
```

### 如何使用

> 明确目标：我要发送的数据为resources目录下的test.json数据，该数据如下，共4个对象

```json
[{
    "_id" : "5f2a72a59e7ea916a8b341da",
    "证券代码" : "000001  ",
    "ISIN代码" : "CNE000000040",
    "中文证券名称（短）" : "平安银行                                    ",
    "ST(连续一年亏损)" : false,
    "*ST(连续两年亏损)" : false,
    "英文证券名称" : "PAB                                     ",
    "基础证券代码" : "000001  ",
    "证券类别" : "1",
    "最后交易日期" : "null",
    "上市日期" : "19910606",
    "除权" : "null",
    "除息" : "null",
    "价格档位" : "0.0100",
    "涨跌幅限制类型" : "1",
    "涨幅上限价格" : "0.100",
    "跌幅下限价格" : "0.100"
},
  {
    "_id" : "5f2a72a59e7ea916a8b341db",
    "证券代码" : "000002  ",
    "ISIN代码" : "CNE0000000T2",
    "中文证券名称（短）" : "万  科Ａ                                   ",
    "ST(连续一年亏损)" : false,
    "*ST(连续两年亏损)" : false,
    "英文证券名称" : "VANKE-A                                 ",
    "基础证券代码" : "000002  ",
    "证券类别" : "1",
    "最后交易日期" : "null",
    "上市日期" : "19910403",
    "除权" : "null",
    "除息" : "null",
    "价格档位" : "0.0100",
    "涨跌幅限制类型" : "1",
    "涨幅上限价格" : "0.100",
    "跌幅下限价格" : "0.100"
  },
  {
    "_id" : "5f2a72a59e7ea916a8b341dd",
    "证券代码" : "000005  ",
    "ISIN代码" : "CNE0000001L7",
    "中文证券名称（短）" : "世纪星源                                    ",
    "ST(连续一年亏损)" : false,
    "*ST(连续两年亏损)" : false,
    "英文证券名称" : "FOUNTAIN                                ",
    "基础证券代码" : "000005  ",
    "证券类别" : "1",
    "最后交易日期" : "null",
    "上市日期" : "19940103",
    "除权" : "null",
    "除息" : "null",
    "价格档位" : "0.0100",
    "涨跌幅限制类型" : "1",
    "涨幅上限价格" : "0.100",
    "跌幅下限价格" : "0.100"
  },
  {
    "_id" : "5f2a72a59e7ea916a8b341de",
    "证券代码" : "000006  ",
    "ISIN代码" : "CNE000000164",
    "中文证券名称（短）" : "深振业Ａ                                    ",
    "ST(连续一年亏损)" : false,
    "*ST(连续两年亏损)" : false,
    "英文证券名称" : "ZHENYE                                  ",
    "基础证券代码" : "000006  ",
    "证券类别" : "1",
    "最后交易日期" : "null",
    "上市日期" : "19920426",
    "除权" : "null",
    "除息" : "null",
    "价格档位" : "0.0100",
    "涨跌幅限制类型" : "1",
    "涨幅上限价格" : "0.100",
    "跌幅下限价格" : "0.100"
  }
]
```

#### 将该项目导入IDEA

#### 启动服务端

点击grpc-server模块的`BootStrap`类右键启用它即可。该模块设计的监听端口为`50051`

#### 启动客户端

客户端我设计了一个简单的控制层（端口为`8080`），也就是可以在浏览器查看结果；要启动客户端只需要点击grpc-client模块下的`MainApplication`类右键启动它即可。此时一旦浏览器发送请求，客户端便会发送RPC请求到服务端请求数据

#### 体验

当服务端和客户端都启动完成后，我们开始模拟gRPC两种服务类型。

> 1.体验简单RPC。在浏览器输入http://localhost:8080/simpleRequest/helllo

可以看到浏览器显示的数据，该请求会一次性返回所有数据（即4个），此时控制台也会打印相应提示

![image-20200814103811228](https://cdn.jsdelivr.net/gh/uestc-toy/blog_file/img/image-20200814103811228.png)

> 2.体验流式RPC。在浏览器输入http://localhost:8080/streamRequest/helllo

由于我在服务端设计的是每5秒发送一个对象，所以你会在客户端控制台看到json文件中的对象5秒发送一个出来，当所有数据发送完毕后，会显示在浏览器中。

![image-20200814103734742](https://cdn.jsdelivr.net/gh/uestc-toy/blog_file/img/image-20200814103734742.png)

### 注意事项

gRPC的跨平台是通过`IDL 文件定义服务`实现的，也就是通过protobuf的代码生成工具生成protobuf指定的接口，在java中生成的也就是一堆class文件（在target目录下），此时，这些接口已经被写死了。

这样会有个问题，比如在java语言中，序列化和反序列化只能是protobuf与javabean之间的转换，不能将protobuf数据转json，我测试了fastjson和Hutool的json转换工具都会报异常，所以客户端收到的数据回显出现也并不是json格式，当然这也有解决办法，一个是自己将服务端发回的数据自行封装到json对象中，也就是你需要实现某些json相关的序列化接口。

另外我昨天在github找到一个项目叫做：[protostuff](https://github.com/protostuff/protostuff)貌似实现了protobuf转json，可以试试。

### 连接MongoDB发送数据

- **客户端模块`grpc-client`无需修改**
- **为了能够实现从MongoDB读取数据并通过gRPC发送给客户端，需要对`grpc-server`作出修改，具体改动如下。**

> POM文件添加SpringData关于mongodb的支持

```xml
<!--用Springboot data操作mongodb-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
```

> 在`application.yml`文件指定对MongoDB的连接配置

```yml
spring:
  data:
    mongodb: #连接配置，我的mongodb没有设密码
      host: 192.168.2.106 #这里改成你自己的数据库ip
      port: 27017 #默认端口
      database: admin #数据库名称
server:
  port: 8090
```

#### 项目`grpc-server`新的构成

模块的改动较大，新的结构如下图

![image-20200814201146513](https://cdn.jsdelivr.net/gh/uestc-toy/blog_file/img/image-20200814201146513.png)

#### 说明

1. 对`MongoDB`的对象关系映射是通过注解方式实现的，具体看`IndexObject`中的注解

2. 数据库中某些数据字段的值是`null`，而`Protobuf3`不允许设置`Protobuf`对象的值为`null`，会抛出异常，我暂时没想到好的解决办法，于是采取曲线救国的办法，使用三目运算符将`null`转字符串"`null`"。见`IndexServiceImpl`的`streamRequest`方法

3. 由于`SpringBoot`默认只有一个`main`方法，但该方法是静态方法不支持依赖注入。我采用的解决办法是让`BootStrap`实现`CommandLineRunner`接口，该接口可以让`SpringBoot`的`main`方法启动时同时启动`BootStrap`的`run`方法，实现`rpc`服务器的启动。

   

#### 运行

点击`grpc-server`模块的`ServerMainApplication`右键运行即可，再次在浏览器输入http://localhost:8080/streamRequest/helllo，可以看到客户端控制台在提醒接收的数据

![image-20200814202600408](https://cdn.jsdelivr.net/gh/uestc-toy/blog_file/img/image-20200814202600408.png)





