#!/bin/sh
#host_ip=ip route get 1 | awk '{print $NF;exit}'
host_ip=10.42.0.1

# Configure config server
mongo --host $host_ip --port 27018 < scripts/configure-config-server.js

# Configure shard-1 server
mongo --host $host_ip --port 27021 < scripts/configure-shard-1-server.js

# Configure shard-2 server
mongo --host $host_ip --port 27022 < scripts/configure-shard-2-server.js

## Configure shard-3 server
mongo --host $host_ip --port 27023 < scripts/configure-shard-3-server.js
#
## Configure shard-4 server
mongo --host $host_ip --port 27024 < scripts/configure-shard-4-server.js

# Configure mongos server
sleep 5
mongo --host $host_ip --port 27017 < scripts/configure-mongos-server.js