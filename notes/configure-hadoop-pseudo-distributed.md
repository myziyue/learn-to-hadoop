Hadoop伪分布式配置
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


```
# cp hadoop_home/etc/hadoop/mapred-site.xml.template hadoop_home/etc/hadoop/mapred-site.xml
# vim hadoop_home/etc/hadoop/mapred-site.xml
<configuration>
    <property>
        <name>mapred.job.tracker</name>
        <value>localhost:8021</value>
    </property>
</configuration>
```


3、设置ssh免密登陆

```
# ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
# cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
# chmod 0600 ~/.ssh/authorized_keys
```
