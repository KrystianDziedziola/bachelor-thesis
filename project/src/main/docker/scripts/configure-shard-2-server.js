rs.initiate(
    {
        _id: "mongo-shard-2",
        members: [
            {_id: 0, host: "mongo-shard-2:27022"}
        ]
    }
);

exit;