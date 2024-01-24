//SETTINGS
//CONTROL BUTTONS
Point BUTTON_ACCEPT = Point.get(945,717); 
Point BUTTON_OK = Point.get(963,703);
Point BUTTON_ONLY_MINE = Point.get(711,364);

//BUY BUTTON
int BUTTON_BUY_X = 1630;
int[] BUTTON_BUY_Y = {470, 584, 698, 812};

// STICKERS CHECK AREA
Point stickerLeftTop = Point.get(1222,446);
Point stickerRightBottom = Point.get(1263,495);

Point leftTop = Point.get();
Point rightBottom = Point.get();

//LOTS
int HEIGHT_ITEM = 114; // Height lot area
int VISIBLE_ITEM = 4; // Count lots availibale for checking (guess we can 5)
int LIST_UPDATE_TIME = 8000; // Speed update list from "SOLD"

// Internal func PerfectClick
startScreenCapture(2);

//TIME
long startScriptTime = Time.getMillis();

//TEST
int purchases = 1;

//MAIN
// Review this section later. Doesn't know what means !EXIT, only guess it. 
while (!EXIT) {
    for (int i = 0; i < VISIBLE_ITEM; i++) {
    
        leftTop.x = stickerLeftTop.x;
        rightBottom.x = stickerRightBottom.x;
        leftTop.y = stickerLeftTop.y + (i * HEIGHT_ITEM);
        rightBottom.y = stickerRightBottom.y + (i * HEIGHT_ITEM);
        
        int validatePurchase = getContoursCount(leftTop, rightBottom);

        if (validatePurchase > 7 && getColor(BUTTON_BUY_X, BUTTON_BUY_Y[i]) < 10000000) {
            click(BUTTON_BUY_X, BUTTON_BUY_Y[i]);
            sleep(10);
            click(BUTTON_ACCEPT);

            log("================================================");
            log("Contours: " + validatePurchase);
            log("Purchase:" + purchases);
            log("================================================");
            purchases += 1;
            sleep(200);

            if (getColor(BUTTON_OK) == 1) {
                click(BUTTON_OK);
            }
            sleep(50);
        }
    }

    if ((Time.getMillis() - startScriptTime) > LIST_UPDATE_TIME) {
        //If we have bug with checkbox.
        if (getColor(711,383) == 6578005){
            sleep(50);
            click(BUTTON_ONLY_MINE);
            // log("CLICK FIX ERROR");
            sleep(50);
        }

        click(BUTTON_ONLY_MINE);
        sleep(50);
        click(BUTTON_ONLY_MINE); 
        
        startScriptTime = Time.getMillis();
    }
}