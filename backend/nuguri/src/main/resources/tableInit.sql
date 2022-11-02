DROP TABLE hobby_favorite;
create table if not exists hobby_favorite(
                                hobby_favorite_id bigint not null auto_increment,
                                is_favorite bit not null,
                                hobby_id bigint,
                                member_id bigint,
                                primary key (hobby_favorite_id)
) engine=InnoDB;

DROP TABLE hobby_history;
create table if not exists hobby_history(
    hobby_history_id bigint not null auto_increment,
    created_date datetime(6),
    last_modified_date datetime(6),
    approve_status varchar(255),
    is_promoter bit not null,
    hobby_id bigint,
    member_id bigint,
    primary key (hobby_history_id)
    ) engine=InnoDB;



DROP TABLE hobby;
create table if not exists hobby (
                       hobby_id bigint not null auto_increment,
                       created_date datetime(6),
                       last_modified_date datetime(6),
                       age_limit integer not null,
                       content varchar(255),
                       cur_num integer not null,
                       end_date datetime(6),
                       fee integer not null,
                       hobby_image varchar(255),
                       is_closed bit not null,
                       max_num integer not null,
                       meeting_place varchar(255),
                       sex_limit char(1) not null,
                       title varchar(255),
                       local_id bigint,
                       category_id bigint,
                       primary key (hobby_id)
) engine=InnoDB;

DROP TABLE ALARM;
create table if not exists alarm (
                       alarm_id bigint not null auto_increment,
                       created_date datetime(6),
                       last_modified_date datetime(6),
                       content varchar(255),
                       is_read bit,
                       participant_id bigint,
                       title varchar(255),
                       participant_image varchar(255),
                       member_id bigint,
                       primary key (alarm_id)
) engine=InnoDB;


