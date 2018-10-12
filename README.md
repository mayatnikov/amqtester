git@gitlab.vsk.ru:mvn/amqtester.git

build:
gradle build

как запустить кластер:
- развернуть activemq на 3-х серверах

на каждом сервере:
- скопировать конфигурацию кластера в текущую
        cp examples/conf/activemq-dynamic-network-broker1.xml conf/activemq.xml
  в настройках откорректировать имя брокера, например:
  <broker xmlns="http://activemq.apache.org/schema/core" brokerName="dynamic-broker2"  dataDirectory="${activemq.data}">

-  задать корректный hostname
        hostnamectl set-hostname host1

- hostname должен быть resolve по DNS или быть прописан в /etc/hosts
192.168.0.101 host3
192.168.0.102 host1
192.168.0.103 host2


- на каждом сервере остановить firewalld или открыть порты 61616, примеры команд:
        systemctl disable firewalld
        systemctl stop firewalld
        или
        firewall-cmd --zone=public  --permanent --add-port=61616/tcp --description="activemq openwire connection"

- запустить activemq в консольном режиме с выводом на экран:
              bin/activemq console

При запуске более одного сервера должны появиться сообщения об установлении кластерного обмена очередями:
 INFO | Establishing network connection from vm://dynamic-broker2 to tcp://host3:61616
 INFO | Connector vm://dynamic-broker2 started
 INFO | Network connection between vm://dynamic-broker2#0 and tcp://host3/192.168.17.77:61616@35410 (dynamic-broker3) has been established.


На клиентах в кластерном варианте целесообразно использовать протокол failover
примеры возможных вариантов соединения описаны здесь: http://activemq.apache.org/failover-transport-reference.html

например:
failover:(tcp://192.168.66.26:61616,tcp://192.168.66.22:61616)?randomize=false
failover:(tcp://192.168.66.26:61616,tcp://192.168.66.22:61616)?randomize=false&priorityBackup=true&priorityURIs=tcp://192.168.66.22:61616,tcp://192.168.66.26:61616




run examples:

java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="tcp://192.168.17.75:61616" --my.consumer=false --server.port=8887

java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="failover:(tcp://192.168.17.74:61616,tcp://192.168.17.75:61616,tcp://192.168.17.77:61616)" --my.consumer=false --server.port=8887
java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="failover:(tcp://192.168.0.101:61616,tcp://192.168.0.102:61616,tcp://192.168.0.103:61616)" --my.consumer=false --server.port=8887
java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="failover:(tcp://192.168.0.101:61616,tcp://192.168.0.102:61616,tcp://192.168.0.103:61616)" --my.producer=false --server.port=8885


java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="failover:(tcp://192.168.66.26:61616,tcp://192.168.66.22:61616)?randomize=false&priorityBackup=true&priorityURIs=tcp://192.168.66.22:61616,tcp://192.168.66.26:61616" --my.consumer=false --server.port=8885

java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="failover:(tcp://192.168.17.75:61616,tcp://192.168.17.74:61616,tcp://192.168.17.77:61616)" --my.producer=false --server.port=8889


java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="failover:(tcp://192.168.17.74:61616,tcp://192.168.17.75:61616,tcp://192.168.17.77:61616)?randomize=false&priorityBackup=true&priorityURIs=tcp://192.168.17.74:61616,tcp://192.168.17.75:61616" --my.consumer=false --server.port=8887

java -jar build/libs/amq-camel-test-0.0.1.jar --spring.activemq.broker-url="failover:(tcp://192.168.17.74:61616,tcp://192.168.17.75:61616,tcp://192.168.17.77:61616)?randomize=false&priorityBackup=true&priorityURIs=tcp://192.168.17.74:61616,tcp://192.168.17.75:61616" --my.producer=false --server.port=8889

