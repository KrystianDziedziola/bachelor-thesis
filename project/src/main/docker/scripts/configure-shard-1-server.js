rs.initiate(
    {
        _id: "mongo-shard-1",
        members: [
            {_id: 0, host: "mongo-shard-1:27021"}
        ]
    }
);

exit;