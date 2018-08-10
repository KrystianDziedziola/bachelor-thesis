rs.initiate(
    {
        _id: "mongo-config",
        configsvr: true,
        members: [
            {_id: 0, host: "mongo-config:27018"}
        ]
    }
);

exit;

