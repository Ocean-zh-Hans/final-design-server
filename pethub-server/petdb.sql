-- 如果有 petdb 库则删除
DROP
    DATABASE IF EXISTS `petdb`;

-- 创建 petdb 库
CREATE
    DATABASE `petdb`;

USE
    `petdb`;

-- 创建管理员账户表
CREATE TABLE `admin_account`
(
    `admin_id`   INT(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
    `username`   VARCHAR(20) NOT NULL COMMENT '用户名',
    `password`   VARCHAR(20) NOT NULL COMMENT '用户密码，MD5加密',
    `owner`      VARCHAR(20) COMMENT '拥有者',
    `permission` INT(2) NOT NULL DEFAULT 2 COMMENT '权限',
    `created_at` DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at` DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`admin_id`),
    UNIQUE KEY `regulator_username_unique` (`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

INSERT INTO `admin_account`(`admin_id`, `username`, `password`, `owner`, `permission`, `created_at`,
                            `updated_at`)
VALUES (1, 'admin', 'admin', 'Boss', 99, NOW(), NOW());

-- 创建用户账户表
CREATE TABLE `user_account`
(
    `user_id`    INT(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`   VARCHAR(20) NOT NULL COMMENT '用户名',
    `password`   VARCHAR(20) NOT NULL COMMENT '用户密码，MD5加密',
    `question`   VARCHAR(100) COMMENT '密保问题',
    `answer`     VARCHAR(100) COMMENT '密保答案',
    `created_at` DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at` DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `user_username_unique` (`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- 创建用户信息表
CREATE TABLE `user_info`
(
    `user_id`        INT(11) NOT NULL COMMENT '用户id',
    `user_nick`      VARCHAR(20) NOT NULL COMMENT '昵称',
    `user_gender`    ENUM('保密', '男', '女') COMMENT '性别',
    `user_birthday`  DATE COMMENT '出生日期',
    `user_location`  VARCHAR(20) COMMENT '所在地',
    `user_avatar`    VARCHAR(100) COMMENT '头像',
    `user_signature` TEXT COMMENT '个性签名',
    `created_at`     DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at`     DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
ALTER TABLE `user_info`
    ADD CONSTRAINT `fk_info_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建私聊信息表
CREATE TABLE `user_chat`
(
    `chat_id`      INT(11) NOT NULL AUTO_INCREMENT COMMENT '聊天ID',
    `initiator_id` INT(11) COMMENT '发起人ID',
    `user_id`      INT(11) COMMENT '用户id',
    `chat_content` TEXT     NOT NULL COMMENT '聊天内容',
    `created_at`   DATETIME NOT NULL COMMENT '创建时间',
    `updated_at`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`chat_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
ALTER TABLE `user_chat`
    ADD CONSTRAINT `fk_chat_initiator_id`
        FOREIGN KEY (`initiator_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE `user_chat`
    ADD CONSTRAINT `fk_chat_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建宠物物种表
CREATE TABLE `pet_specie`
(
    `specie_id`     INT(11) NOT NULL AUTO_INCREMENT COMMENT '物种id',
    `specie_name`   VARCHAR(10) NOT NULL COMMENT '物种名称',
    `specie_male`   VARCHAR(5)  NOT NULL COMMENT '父系性别',
    `specie_female` VARCHAR(5)  NOT NULL COMMENT '母系性别',
    `created_at`     DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at`     DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`specie_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- 创建宠物品种表
CREATE TABLE `pet_variety`
(
    `variety_id`   INT(11) NOT NULL AUTO_INCREMENT COMMENT '品种id',
    `specie_id`   INT(11) NOT NULL COMMENT '物种id',
    `variety_name` VARCHAR(20) NOT NULL COMMENT '品种名称',
    `created_at`   DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at`   DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`variety_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

ALTER TABLE `pet_variety`
    ADD CONSTRAINT `fk_variety_species_id`
        FOREIGN KEY (`specie_id`)
            REFERENCES `pet_specie` (`specie_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建宠物信息表
CREATE TABLE `pet_info`
(
    `pet_id`         INT(11) NOT NULL AUTO_INCREMENT COMMENT '宠物id',
    `user_id`        INT(11) NOT NULL COMMENT '用户id',
    `pet_name`       VARCHAR(20) NOT NULL COMMENT '宠物名字',
    `variety_id`     INT(11) COMMENT '品种id',
    `pet_sex`        VARCHAR(5) COMMENT '宠物性别',
    `pet_birthday`   DATE COMMENT '宠物生日',
    `is_neutered`    INT(1) COMMENT '是否绝育',
    `vaccine_status` VARCHAR(20) COMMENT '疫苗接种情况',
    `created_at`     DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at`     DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`pet_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `pet_info`
    ADD CONSTRAINT `fk_pet_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `pet_info`
    ADD CONSTRAINT `fk_pet_variety_id`
        FOREIGN KEY (`variety_id`)
            REFERENCES `pet_variety` (`variety_id`)
            ON UPDATE CASCADE;

-- 创建宠物动态表
CREATE TABLE `pet_post`
(
    `post_id`      INT(11) NOT NULL AUTO_INCREMENT COMMENT '动态id',
    `user_id`      INT(11) NOT NULL COMMENT '用户id',
    `post_content` TEXT(20) NOT NULL COMMENT '动态内容',
    `post_images`  VARCHAR(200) COMMENT '动态图片集',
    `post_view`    INT(10) DEFAULT 0 COMMENT '查看人数',
    `post_like`    INT(10) DEFAULT 0 COMMENT '点赞人数',
    `created_at`   DATETIME NOT NULL COMMENT '创建时间',
    `updated_at`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`post_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `pet_post`
    ADD CONSTRAINT `fk_posts_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建宠秀评论表
CREATE TABLE `post_comment`
(
    `comment_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `post_id` INT(11) COMMENT '动态id',
    `user_id` INT(11) COMMENT '用户ID',
    `chat_content` TEXT COMMENT '聊天内容',
    `created_at`   DATETIME NOT NULL COMMENT '创建时间',
    `updated_at`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`comment_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `post_comment`
    ADD CONSTRAINT `fk_comment_post_id`
        FOREIGN KEY (`post_id`)
            REFERENCES `pet_post` (`post_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE `post_comment`
    ADD CONSTRAINT `fk_comment_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建电影表
CREATE TABLE `pet_movie`
(
    `movie_id`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '电影id',
    `movie_title`    VARCHAR(20) NOT NULL COMMENT '电影标题',
    `movie_director` VARCHAR(20) COMMENT '电影导演',
    `movie_rating`   DECIMAL(3, 1) COMMENT '电影评分',
    `release_date`   DATE COMMENT '发布日期',
    `movie_desc`     TEXT COMMENT '电影简介',
    `movie_url`      VARCHAR(20) NOT NULL COMMENT '电影url',
    `move_poster`    VARCHAR(20) COMMENT '电影海报',
    `shelf_status`   INT(1) NOT NULL DEFAULT 1 COMMENT '上架状态',
    `created_at`     DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at`     DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`movie_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;

-- 创建影评表
CREATE TABLE `review`
(
    `review_id`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '评价id',
    `user_id`         INT(11) NOT NULL COMMENT '用户id',
    `movie_id`        INT(11) NOT NULL COMMENT '电影id',
    `reviews_content` TEXT COMMENT '影评内容',
    `user_rating`     INT(2) COMMENT '用户评分',
    `created_at`      DATETIME NOT NULL COMMENT '创建时间',
    `updated_at`      DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`review_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `review`
    ADD CONSTRAINT `fk_reviews_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON UPDATE CASCADE;
ALTER TABLE `review`
    ADD CONSTRAINT `fk_reviews_movie_id`
        FOREIGN KEY (`movie_id`)
            REFERENCES `pet_movie` (`movie_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建宠物知识表
CREATE TABLE `pet_knowledge`
(
    `kno_id`       INT(11) NOT NULL AUTO_INCREMENT COMMENT '知识id',
    `kno_title`    VARCHAR(100) NOT NULL COMMENT '知识标题',
    `kno_category` VARCHAR(50)  NOT NULL COMMENT '知识分类',
    `specie_id`   INT(11) COMMENT '物种id',
    `variety_id`   INT(11) COMMENT '品种id',
    `kno_content`  TEXT COMMENT '知识内容',
    `kno_images`   VARCHAR(100) COMMENT '配图',
    `created_at`   DATETIME     NOT NULL COMMENT '创建时间',
    `updated_at`   DATETIME     NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`kno_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `pet_knowledge`
    ADD CONSTRAINT `fk_knowledge_variety_id`
        FOREIGN KEY (`variety_id`)
            REFERENCES `pet_variety` (`variety_id`)
            ON UPDATE CASCADE;
ALTER TABLE `pet_knowledge`
    ADD CONSTRAINT `fk_knowledge_species_id`
        FOREIGN KEY (`specie_id`)
            REFERENCES `pet_specie` (`specie_id`)
            ON UPDATE CASCADE;

-- 创建养宠经验表
CREATE TABLE `pet_exps`
(
    `exp_id`      INT(11) NOT NULL AUTO_INCREMENT COMMENT '知识id',
    `user_id`     INT(11) COMMENT '用户ID',
    `variety_id`  INT(11) COMMENT '品种ID',
    `exp_title`   VARCHAR(20) NOT NULL COMMENT '知识标题',
    `exp_content` TEXT        NOT NULL COMMENT '知识内容',
    `exp_mark`    VARCHAR(20) COMMENT '标记',
    `exp_images`  VARCHAR(200) COMMENT '经验图片',
    `exp_view`    INT(10) DEFAULT 0 COMMENT '查看人数',
    `exp_like`    INT(10) DEFAULT 0 COMMENT '认同人数',
    `exp_deslike` INT(10) DEFAULT 0 COMMENT '否认人数',
    `created_at`  DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at`  DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`exp_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `pet_exps`
    ADD CONSTRAINT `fk_exp_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE `pet_exps`
    ADD CONSTRAINT `fk_exp_variety_id`
        FOREIGN KEY (`variety_id`)
            REFERENCES `pet_variety` (`variety_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建经验评论表
CREATE TABLE `exp_comment`
(
    `comment_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `exp_id` INT(11) COMMENT '知识id',
    `user_id` INT(11) COMMENT '用户ID',
    `chat_content` TEXT COMMENT '聊天内容',
    `created_at`   DATETIME NOT NULL COMMENT '创建时间',
    `updated_at`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`comment_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `exp_comment`
    ADD CONSTRAINT `fk_comment2_exp_id`
        FOREIGN KEY (`exp_id`)
            REFERENCES `pet_exps` (`exp_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE `exp_comment`
    ADD CONSTRAINT `fk_comment2_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;


-- 创建丢失宠物表
CREATE TABLE `pet_miss`
(
    `miss_id`      INT(11) NOT NULL AUTO_INCREMENT COMMENT '丢失id',
    `user_id`     INT(11) COMMENT '用户ID',
    `miss_image`  VARCHAR(100) COMMENT '经验图片',
    `miss_content`   TEXT NOT NULL COMMENT '寻宠正文',
    `miss_location` VARCHAR(50)        NOT NULL COMMENT '丢失位置',
    `is_found`    INT(1) DEFAULT 0 COMMENT '是否找到',
    `miss_view`    INT(10) DEFAULT 0 COMMENT '围观人数',
    `created_at`  DATETIME    NOT NULL COMMENT '创建时间',
    `updated_at`  DATETIME    NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`miss_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `pet_miss`
    ADD CONSTRAINT `fk_miss_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

-- 创建寻宠评论表
CREATE TABLE `pet_thread`
(
    `thread_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
    `miss_id` INT(11) COMMENT '发起人ID',
    `user_id` INT(11) COMMENT '用户ID',
    `thread_content` TEXT COMMENT '聊天内容',
    `thread_location` VARCHAR(50)        NOT NULL COMMENT '丢失位置',
    `is_takenin`    INT(1) DEFAULT 0 COMMENT '是否收留',
    `created_at`   DATETIME NOT NULL COMMENT '创建时间',
    `updated_at`   DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`thread_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8;
ALTER TABLE `pet_thread`
    ADD CONSTRAINT `fk_thread_miss_id`
        FOREIGN KEY (`miss_id`)
            REFERENCES `pet_miss` (`miss_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;
ALTER TABLE `exp_comment`
    ADD CONSTRAINT `fk_thread_user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `user_account` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;