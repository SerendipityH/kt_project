1.tomcat插件启动,没有成功也没有报错
![iamge](https://github.com/SerendipityH/kt_project/blob/master/floder/tomcat1.jpg)
原因是在xml配置文件配置了dubbo发布服务，但是没有关闭linux的防火墙，导致访问失败
解决：
执行关闭命令： systemctl stop firewalld.service
执行开机禁用防火墙自启命令  ： systemctl disable firewalld.service
