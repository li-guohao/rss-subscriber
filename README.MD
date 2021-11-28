# 项目介绍
RSS订阅微程序，用于订阅RSS，并进行消息推送。

# 实现思路
1. 获取RSS订阅源，从后台获取，后台使用服务器Session的方式进行登陆，并且是用户名密码+手机号验证码登陆
2. 开启定时任务，定时获取RSS订阅源信息，比对数据库，是否有新内容
3. 如果有新内容，则保存到数据库，并进行消息推送（多种方式：邮件、短信），同时预留钩子

# 环境变量

邮件服务器使用SMTP协议，使用端口465

| 变量名                           | 变量含义           |
| -------------------------------- | ------------------ |
| RSS_SUBSCRIBER_SMTP_HOST         | 邮件服务器地址     |
| RSS_SUBSCRIBER_SMTP_USERNAME     | 邮件服务器发信地址 |
| RSS_SUBSCRIBER_SMTP_PASSWORD     | 邮件服务器密码     |
| RSS_SUBSCRIBER_SMTP_TARGET_EMAIL | 给谁发邮件         |
| RSS_SUBSCRIBER_APP_TOKEN         | 请求接口的TOKEN    |

# 接口

| 接口                                                         | 作用               |
| ------------------------------------------------------------ | ------------------ |
| ip:port/api/rsssub/findAll?token=[token]                 | 查询所有的订阅     |
| ip:port/api/rsssub/add?token=[token]&feed=[RSSURL]         | 添加订阅           |
| ip:port/api/rsssub/findArticeByRsid?token=[token]&rsid=[rss_sid] | 根据订阅ID查询内容 |
| ip:port/api/rsssub/scan?token=[token]                    | 请求立刻扫描一次   |

# 编译

``` 
mvn clean package -DskipTests
```

