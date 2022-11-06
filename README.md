[![GitHub license](https://img.shields.io/github/license/rockychen1221/GlobalRegion)](https://github.com/rockychen1221/GlobalRegion/blob/master/LICENSE)
[![Fork me on Gitee](https://gitee.com/rockychen121/GlobalRegion/widgets/widget_5.svg)](https://gitee.com/rockychen121/GlobalRegion)

# GlobalRegion 简介

世界各国是指世界上各个国家，截至2019年，世界上共有233个国家和地区，其中国家有195个，地区有38个。

数据较全，体验较好，支持快速接入，根据上级查找附属直接下级，支持检索以及默认值初始化赋值（本example默认：中国/湖南/湘潭/湘乡市）

支持中英文国际化，通过[pinyin4j-example](https://github.com/rockychen1221/pinyin4j-example)获取地区数据国际化

级联格式依次为：国家（地区）/省份（直辖市）/市（区）/县，效果如图

![image-area](img/global_area.png)

## 本实例依赖以下插件或库，可根据实际使用进行调整修改

* jQuery
* BootStrap 3
* BootStrap Select

> 考虑到同一个页面会有多个地区应用场景，比如故乡和工作地，故Ajax请求发送设置为同步，可根据需要调整

## 如何运行

* ~~Step 1. 在您的数据库中执行`sql`目录中的脚本，`d_area`为地区层级，`sys_lang`为国际化，更换应用数据库连接信息~~
* Step 1. 使用`mvn spring-boot:run`,启动后浏览器访问`http://localhost:8080`

## 如何接入使用

* Step 1. 自定义地区下拉元素页面布局，根据实际页面比例预留长度，声明下拉元素ID（需在Step 3 初始化传入，如使用默认ID:fromArea，即可不传）
* Step 2. 在您自己的页面引入`area.js`
* Step 3. 调用`Area.initFromArea();` ,初始化即可接入使用

## Update...

* 加入h2内存数据库，去除mysql依赖，
* 添加单元测试
* 优化部分代码
* 同步Gitee

## 如果对您有帮助节省了您的时间，请Star支持，数据欢迎补充维护

## Next

- [ ] 目前脚本查询到的一级国家/地区共有240个，哪多冒出来7个。。。需要进行核对，和外交部一致
- [ ] 封装成Vue组件
- [ ] 将地区数据做成地图图表，支持下钻

多库提交： git push origin && git push gitee

## 数据库脚本

```sql
create table person
(
    id bigint primary key auto_increment comment '主键',
    code varchar(50) comment '学号',
    name varchar(50) comment '姓名',
    -- 
    sex tinyint comment '性别',
    age smallint comment '年龄',
    political varchar(50) comment '政治面貌',
    origin varchar(50) comment '籍贯',
    professional varchar(50) comment '所属院系'
) engine=innodb charset=utf8mb4 comment '人员表';
```

springboot 判断环境
https://blog.csdn.net/qq_41633199/article/details/107366728
https://www.zhangshilong.cn/work/270539.html
https://blog.csdn.net/qq_27818541/article/details/105719962


读取maven 环境
https://blog.csdn.net/ityqing/article/details/97780578
https://www.cnblogs.com/expiator/p/9724540.html
https://blog.csdn.net/qq_27818541/article/details/105719962
https://www.likecs.com/show-204465189.html
https://blog.csdn.net/qq_41633199/article/details/107366728



?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
https://www.cnblogs.com/EasonJim/p/6906713.html
https://www.cnblogs.com/godwithus/p/9788790.html


执行代码
java -jar target\global-area-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod --girl.cupsize=M
java -jar -Dspring.profiles.active=prod global-area-0.0.1-SNAPSHOT.jar --server.address=0.0.0.0
nohup java -jar -Dspring.profiles.active=prod global-area-0.0.1-SNAPSHOT.jar & 

```
不添加  --server.address=0.0.0.0 只能本机访问，因为 server.address=127.0.0.1
redis有类似的概念
bind 127.0.0.1
备注：如果想要设置指定IP连接redis，只需要修改redis.conf文件中bind配置项即可。如果不限IP，将127.0.0.1修改成0.0.0.0即可。
https://www.cnblogs.com/jiangcong/p/15449452.html


```

springboot 启动慢
https://blog.csdn.net/wyyl1/article/details/84785237
service network restart # 保存后，重启网络：
127.0.0.1       localhost       aoedeMacBook-Pro.local
::1             localhost       aoedeMacBook-Pro.local



