create table regionsByUsers (
    user_id int8 not null references user,
    region_id int8 not null references region,
    primary key (channel_id, subscriber_id)
)