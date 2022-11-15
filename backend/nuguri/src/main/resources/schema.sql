create table if not exists alarm (
                       alarm_id bigint not null auto_increment,
                       created_date datetime(6),
                       last_modified_date datetime(6),
                       content varchar(255),
                       hobby_id bigint,
                       is_read bit,
                       participant_id bigint,
                       participant_image varchar(255),
                       title varchar(255),
                       member_id bigint,
                       primary key (alarm_id)
) engine=InnoDB;

create table if not exists base_address (
                              local_id bigint not null auto_increment,
                              dong varchar(255),
                              dongcode varchar(255),
                              gugun varchar(255),
                              lat varchar(255),
                              lng varchar(255),
                              sido varchar(255),
                              primary key (local_id)
) engine=InnoDB;

create table if not exists category (
                          category_id bigint not null auto_increment,
                          created_date datetime(6),
                          last_modified_date datetime(6),
                          category_name varchar(255),
                          parent_id bigint,
                          primary key (category_id)
) engine=InnoDB;

create table if not exists deal (
                      deal_id bigint not null auto_increment,
                      created_date datetime(6),
                      last_modified_date datetime(6),
                      deal_image varchar(255),
                      description varchar(255),
                      hit integer not null,
                      is_deal bit not null,
                      price integer not null,
                      title varchar(255),
                      local_id bigint,
                      category_id bigint,
                      member_id bigint,
                      primary key (deal_id)
) engine=InnoDB;

create table if not exists deal_favorite (
                               deal_favorite_id bigint not null auto_increment,
                               created_date datetime(6),
                               last_modified_date datetime(6),
                               is_favorite bit not null,
                               deal_id bigint,
                               member_id bigint,
                               primary key (deal_favorite_id)
) engine=InnoDB;

create table if not exists deal_history (
                              deal_history_id bigint not null auto_increment,
                              created_date datetime(6),
                              last_modified_date datetime(6),
                              deal_status varchar(255),
                              promise_location varchar(255),
                              promise_time datetime(6),
                              deal_id bigint,
                              member_id bigint,
                              primary key (deal_history_id)
) engine=InnoDB;

create table if not exists group_purchase (
                                group_purchase_id bigint not null auto_increment,
                                created_date datetime(6),
                                last_modified_date datetime(6),
                                description varchar(255),
                                end_date datetime(6),
                                goal_number integer not null,
                                group_purchase_image varchar(255),
                                price integer not null,
                                product_url varchar(255),
                                reserved_number integer not null,
                                title varchar(255),
                                local_id bigint,
                                category_id bigint,
                                member_id bigint,
                                primary key (group_purchase_id)
) engine=InnoDB;

create table if not exists group_purchase_favorite (
                                         group_purchase_favorite_id bigint not null auto_increment,
                                         created_date datetime(6),
                                         last_modified_date datetime(6),
                                         is_favorite bit not null,
                                         group_purchase_id bigint,
                                         member_id bigint,
                                         primary key (group_purchase_favorite_id)
) engine=InnoDB;

create table if not exists group_purchase_history (
                                        group_purchase_history_id bigint not null auto_increment,
                                        created_date datetime(6),
                                        last_modified_date datetime(6),
                                        is_cancelled bit not null,
                                        group_purchase_id bigint,
                                        member_id bigint,
                                        primary key (group_purchase_history_id)
) engine=InnoDB;

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
                       member_id bigint,
                       primary key (hobby_id)
) engine=InnoDB;

create table if not exists hobby_favorite (
                                hobby_favorite_id bigint not null auto_increment,
                                is_favorite bit not null,
                                hobby_id bigint,
                                member_id bigint,
                                primary key (hobby_favorite_id)
) engine=InnoDB;

create table if not exists hobby_history (
                               hobby_history_id bigint not null auto_increment,
                               created_date datetime(6),
                               last_modified_date datetime(6),
                               approve_status varchar(255),
                               is_promoter bit not null,
                               hobby_id bigint,
                               member_id bigint,
                               primary key (hobby_history_id)
) engine=InnoDB;

create table if not exists member (
                        member_id bigint not null auto_increment,
                        created_date datetime(6),
                        last_modified_date datetime(6),
                        age integer,
                        description varchar(255),
                        email varchar(255),
                        name varchar(255),
                        nickname varchar(255),
                        password varchar(255) not null,
                        profile_image varchar(255),
                        sex char(1),
                        temperature double precision,
                        local_id bigint,
                        primary key (member_id)
) engine=InnoDB;