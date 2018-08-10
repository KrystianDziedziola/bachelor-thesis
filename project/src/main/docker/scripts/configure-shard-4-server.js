rs.initiate(
    {
        _id: "mongo-shard-4",
        members: [
            {_id: 0, host: "mongo-shard-4:27024"}
        ]
    }
);

exit;