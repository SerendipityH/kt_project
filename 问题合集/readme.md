1.tomcat插件启动,没有成功也没有报错
![iamge](https://github.com/SerendipityH/kt_project/blob/master/%E9%97%AE%E9%A2%98%E5%90%88%E9%9B%86/photo/tomcat1.jpg)  
原因是在xml配置文件配置了dubbo发布服务，但是没有关闭linux的防火墙，导致访问失败  
解决：  
执行关闭命令： systemctl stop firewalld.service  
执行开机禁用防火墙自启命令  ： systemctl disable firewalld.service


2. 出现错误:  

本来以为是applicationContext-dao.xml这个文件出错了,后来才发现是

