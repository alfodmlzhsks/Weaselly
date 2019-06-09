package weaselly.gtec.com.weaselly;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;

public class CheckPermission {
    String[] myPermission = null;
    Activity context = null;
    String[] deniedPermission = null;
    /*
    ACCESS_FINE_LOCATION
    ACCESS_COARSE_LOCATION
    CAMERA
    WRITE_EXETRNAL_STORAGE
    READ_EXETERNAL_STORAGE

     */

    CheckPermission(Activity context, String... str) {
        this.context = context;
        myPermission = str;
    }

    public void checkMyPermission() {
        ArrayList<String> list = new ArrayList<>();
        //사용자의 OS 빌드 버전이 마시멜로우(23) 이상인지 판별
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //요청한 권한들이 사용자의 단말 권한에서 허가 여부를 판단한다.
            for(int i=0;i<myPermission.length;i++){
                if(context.checkSelfPermission(myPermission[i])==PackageManager.PERMISSION_DENIED){
                    context.requestPermissions(new String[]{myPermission[i]},1000);

                }
            }


        }
    }

}
