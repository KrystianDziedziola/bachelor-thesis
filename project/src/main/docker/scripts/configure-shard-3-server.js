rs.initiate(
    {
        _id: "mongo-shard-3",
        members: [
            {_id: 0, host: "mongo-shard-3:27023"}
        ]
    }
);

exit;