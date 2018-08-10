sh.addShard("mongo-shard-1/mongo-shard-1:27021");
sh.addShard("mongo-shard-2/mongo-shard-2:27022");
sh.addShard("mongo-shard-3/mongo-shard-3:27023");
sh.addShard("mongo-shard-4/mongo-shard-4:27024");

sh.enableSharding("inz");
sh.shardCollection("inz.products", {_id: "hashed"});
sh.shardCollection("inz.customers", {_id: "hashed"});

exit;