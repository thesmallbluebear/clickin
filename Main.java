//SETTINGS
//CONTROL BUTTONS
Point BUTTON_ACCEPT = Point.get(945,717); // подтвердить
Point BUTTON_OK = Point.get(1118,738); // ok
Point BUTTON_ONLY_MINE = Point.get(711,364); //ГАЛОЧКА [ТОЛЬКО МОИ ЗАПРОСЫ]
// Commented this, beacause i don't know, how it's would working.
// Point BUTTON_PREV = Point.get(250,975);// назад 

//BUY BUTTON
/* 
 * Here we has calculate on every step in cycle, where is coordinate for button.
 * I guess, better decision was use variable and array, check this later. 
 */
final int BUTTON_BUY_X = 1705;
final int[] BUTTON_BUY_Y = {470, 584, 698, 812};

// STICKERS CHECK AREA
Point stickerLeftTop = Point.get(1222, 446);
Point stickerRightBottom = Point.get(1263, 495);

//LOTS
static final byte HEIGHT_ITEM = 114; // Height lot area
static final byte VISIBLE_ITEM = 4; // Count lots availibale for checking (guess we can 5)
final int LIST_UPDATE_TIME = 8000; // Speed update list from "SOLD"

/* 
* internal function PerfectClick
* 0 - every 30 frame, 1 - every 15 frame, 2 - all frames
*/
startScreenCapture(2);

//TIME
// for update list
long startScriptTime = Time.getMillis();

//TEST
int purchases = 0;

//MAIN
// Review this section later. Doesn't know what means !EXIT, only guess it. 
while (!EXIT) {
    for (byte i = 0; i < VISIBLE_ITEM; i++) {
        stickerLeftTop.y = stickerLeftTop.y + (i * HEIGHT_ITEM);
        stickerRightBottom.y = stickerRightBottom.y + (i * HEIGHT_ITEM);

        byte validatePurchase = getContoursCount(stickerLeftTop, stickerRightBottom);

        if (validatePurchase > 2 && getColor(BUTTON_BUY_X, BUTTON_BUY_Y[i]) < 11600000) {
            click(BUTTON_BUY_X, BUTTON_BUY_Y[i]);
            sleep(10);
            click(BUTTON_ACCEPT);

            log("Contours: " + validatePurchase);
            log("Purchase:" + purchases);
            log("================================================");
            purchases += 1;
            sleep(200);

            // Exit from window "SOLD", if got sold earlier, than we buy it.
            // 9467982 - color pixel on button. Need to replace that.
            if (getColor(BUTTON_OK) == 9467982) {
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
            sleep(50);
        }

        click(BUTTON_ONLY_MINE);
        sleep(50);
        click(BUTTON_ONLY_MINE); 
        
        startScriptTime = Time.getMillis();
    }
}