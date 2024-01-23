//SETTINGS
//BUTTONS
Point button_accept = Point.get(945,717); // подтвердить
Point button_OK = Point.get(1118,738); // ok
Point button_OnlyMine = Point.get(711,364); //ГАЛОЧКА [ТОЛЬКО МОИ ЗАПРОСЫ]
//Оно вообще работает?
Point button_prev = Point.get(250,975);// назад 

//BUY BUTTON
// Point buy = Point.get(1705,0); // купить 
//X for BuyButton
int buyButton_x = 1705;
//Y for BuyButton
int[] buyButton_y = {470, 584, 698, 812};

//STICKERS
Point left = Point.get(1222,446); // Верхний левый угол [2] наклейки
Point right = Point.get(1263,495); // Нижний правый угол [2] наклейки

Point leftTop = Point.get();
Point rightBot = Point.get();

//LOTS
int heightItem = 114; //ВЫСОТА СЛОТА
int visibleItem = 4; //КОЛ-ВО СЛОТОВ
int update_speed = 8000; // СКОРОСТЬ ОБНОВЛЕНИЯ ЗАПРОСОВ

//TIME
long countTime = Time.getMillis();


// 0 - каждый 30 кадр, 1 - каждый 15 кадр, 2 - все кадры
startScreenCapture(2);

//MAIN
while (!EXIT) {
    for (int i = 0; i < visibleItem; i++){
        //TODO: разобраться че тут происходит и надоо ли оно в таком виде
        leftTop.x = left.x;
        rightBot.x = right.x;

        leftTop.y = left.y + (i * heightItem);
        rightBot.y = right.y + (i * heightItem);
        int toBuy = getContoursCount(leftTop, rightBot);

        if (toBuy > 2 && getColor(buyButton_x, buyButton_y[i]) < 11600000){
            click(buyButton_x, buyButton_y[i]);
            sleep(10);

            click(button_accept);
            sleep(250);

            if(getColor(button_OK) == 9467982){
                click(button_OK);
            }
            sleep(50);
            //work?
            // click(button_prev);
            //310
        }
    }

    if ((Time.getMillis() - countTime) > update_speed) {
        //TODO: проверка на галочку "Только мои запросы"
        if (getColor(711,383) == 6578005){
            sleep(100);
            click(button_OnlyMine);
            sleep(100);
            //200
        }

        click(button_OnlyMine);
        sleep(50);
        click(button_OnlyMine); 
        //100
        countTime = Time.getMillis();
    }
    
}