Hadoop单节点安装配置
=========

1、配置环境

1.1、软件包
sun jdk  或 openjdk： 1.7 

1.2、安装必须软件包：

```
# yum install ssh rsync

# rpm -ivh jdk-7u79-linux-x64.rpm
```

1.3、配置java环境

```
# vim /etc/profile
export JAVA_HOME=/usr/java/latest
export JRE_HOME=$JAVA_HOME/jre
export PATH=$PATH:$JAVA_HOME/bin
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib
```

```
# vim hadoop_home/etc/hadoop/hadoop_env.sh
export JAVA_HOME=/usr/java/latest
或者
export JAVA_HOME=/usr/lib/jvm/jre-1.7.0-openjdk.x86_64
```

2、单节点配置文件配置
```
# vim hadoop_home/etc/hadoop/core-site.xml
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```

```
# vim hadoop_home/etc/hadoop/hdfs-site.xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
</configuration>
```

3、设置ssh免密登陆

```
# ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
# cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
# chmod 0600 ~/.ssh/authorized_keys
```

4、测试环境
    1.格式化文件系统
    
```
$ bin/hdfs namenode -format
```

    2. 开启 NameNode 服务和 DataNode 服务

```
$ sbin/start-dfs.sh
```

    3. 用浏览器浏览 NameNode，默认地址如下：

http://localhost:50070/

    4. 创建HDFS所需的目录

```
$ bin/hdfs dfs -mkdir /user
$ bin/hdfs dfs -mkdir /user/<username>
```

    5. 复制输入文件到目标文件系统：

```
$ bin/hdfs dfs -put etc/hadoop input
```

    6.运行hadoop提供的案例

```
$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.7.3.jar grep input output 'dfs[a-z.]+'
```

    7. 将运算结果获取到本地
    
```
$ bin/hdfs dfs -get output output
$ cat output/*
```
    或者在目标系统浏览
```
$ bin/hdfs dfs -cat output/*
```

    8. 停止DFS服务

```
$ sbin/stop-dfs.sh
```
