INSERT INTO category(category_id, name, created_at) VALUES
                                           (1,'핸드폰',NOW()),
                                           (2,'의류',NOW()),
                                           (3,'반려동물',NOW()),
                                           (4,'서적',NOW()),
                                           (5,'기타',NOW());

INSERT INTO user(user_name, password,rate) VALUES
                                               ('김민수','1111',0.0),
                                               ('이지은','2222',0.0),
                                               ('김지수','3333',0.0),
                                               ('이지훈','4444',0.0),
                                               ('','qwe',0.0),
                                               ('admin', 'admin', 0.0);

INSERT INTO item(user_id, name, price, category_id, image_path, description, created_at) VALUES
    (1,'아이폰15 블루',990000,1,'/img/iphone.jpg','친구 아이폰 15라 싸게 팔아요. 상태 SS입니다.',NOW()),
    (1,'양털 후리스 후드 2종',15000,2,'/img/hoodie.png','새 옷. 옷 정리 중입니다.',NOW()),
    (2,'마법천자문 1권 ~53권',100000,4,'/img/magicbook.jpg','마법천자문 1~53권',NOW()),
    (3,'레드 카라티',4000,2,'/img/redshirt.png','레드 컬러의 반팔 카라티입니다.',NOW()),
    (1,'애견담요',5000,3,'/img/petblanket.png','분리불안훈련용 매트입니다',NOW()),
    (4,'전자레인지 판매합니다',30000,5,'/img/microwave.jpg','새거 사서 사용은 3번 정도 했고~ 두 대라서 한 대는 정리합니다~',NOW()),
    (1,'노스페이스 고어택스 바람막이',35000,2,'/img/northface.jpg','추가 문의사항은 문의해주세요',NOW()),
    (1,'미생 만화책 전권',18000,4,'/img/misaengbook.jpg','책 상태 훼손 전혀 없습니다.',NOW()),
    (3,'바비브라운 레드 립스틱',10000,5,'/img/lipstick.jpg','선물 받았는데, 색깔이 안 어울려서 팔아요.',NOW()),
    (1,'강아지 고양이 밥그릇',3000,3,'/img/petbowl.jpg','고양이 임시보호할때 사용하고 안 써서 저렴하게 올립니다.',NOW()),
    (1,'파세코에어컨',70000,5,'/img/airconditioner.jpg','이사갈 집에 시스템에어컨이 설치가 되어있는 집이라 27일 오전에 가져가실 분만 연락 기다립니다.',NOW()),
    (4,'스벅 아아 2잔 5000원',5000,5,'/img/starbucks.jpg','빠른 입금하실 수 있는 분!',NOW());

INSERT INTO comment(user_id, reviewer_name, content, created_at, rating) VALUES
                                                                   (1,'이지훈','따뜻한 마음 감사해요.',TIMESTAMP '2024-03-14 10:00:00', 3),
                                                                   (1,'이지은','감사합니다^^',TIMESTAMP '2024-05-16 07:00:00', 2),
                                                                   (2,'김민수','쿨거래 감사합니다',TIMESTAMP '2024-01-03 03:00:00', 1),
                                                                   (2,'김지수','다음에 또 재구매 할게요',TIMESTAMP '2024-02-14 18:00:00', 4),
                                                                   (3,'이지은','판매자님이 정말 친절해요',TIMESTAMP '2024-05-17 16:00:00',5);
