# PixelCore  

用于对接PixelWeb，以及其附属插件的前置  
内置HttpServer，请在配置文件中修改相关配置
  
附属插件：
- [PixelLogin](https://github.com/Calcium-Ion/PixelLoginPlugin)

  
指令：  
重启插件/pcore reload

配置文件
```yaml
# 您的PixelWeb的token，清不要暴露您的token，否则后果很严重！！
token: yourtoken
# 登录服务的端口，需要保证端口未被占用！
http:
  port: 8081
```