package kr.co.hs.packageinstaller.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import kr.co.hs.packageinstaller.ApplicationManager;
import kr.co.hs.packageinstaller.OnInstalledPackaged;

/**
 * Created by privacydev on 2017. 8. 3..
 */

public class SampleActivity extends Activity {
    public static final String TAG = "InstallInBackground";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            final ApplicationManager am = new ApplicationManager(SampleActivity.this);
            am.setOnInstalledPackaged(new OnInstalledPackaged() {

                public void packageInstalled(String packageName, int returnCode) {
                    if (returnCode == ApplicationManager.INSTALL_SUCCEEDED) {
                        Log.d(TAG, "Install succeeded");
                    } else {
                        Log.d(TAG, "Install failed: " + returnCode);
                    }
                }
            });
            try {
                //	am.installPackage(txtApkFilePath.getText().toString()); // install package
                am.uninstallPackage("com.jiran.xkeeperMobile");
            } catch (Exception e) {
                logError(e);
            }

        } catch (Exception e) {
            logError(e);
        }
    }

    private void logError(Exception e) {
        e.printStackTrace();
        Toast.makeText(SampleActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}
